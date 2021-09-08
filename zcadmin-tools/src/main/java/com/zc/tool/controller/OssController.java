package com.zc.tool.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.zc.entity.ResultResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZhangC
 * @create 2021-06-24-10:26
 */
@RestController
@RequestMapping("/zcadmin/oss")
@Api(tags = "三方服务")
@Slf4j
public class OssController {
    @Value("${oss.accessId}")
    String accessId;
    @Value("${oss.accessKey}")
    String accessKey;
    @Value("${oss.endpoint}")
    String endpoint;
    @Value("${oss.bucket}")
    String bucket;

    @GetMapping
    protected ResultResponse getPolicy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> respMap = new LinkedHashMap<String, String>();


        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        String dir = request.getParameter("pathPre"); // 用户上传文件时指定的前缀。
        String fileName=request.getParameter("fileName");
//        https://zc-go-fit.oss-cn-beijing.aliyuncs.com/test1630381920935panda.jpg

        log.info("文件上传:{}",host+"/"+dir+"/"+fileName);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        try {
            long expireTime = 90;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap.put("accessId", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
//             respMap.put("expire", formatISO8601Date(expiration));

        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
            return ResultResponse.error();
        } finally {
            ossClient.shutdown();
        }

        return ResultResponse.success(respMap);
    }

}
