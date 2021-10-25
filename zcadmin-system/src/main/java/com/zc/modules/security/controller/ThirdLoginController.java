package com.zc.modules.security.controller;

import com.zc.constant.AuthThirdTypeConstants;
import com.zc.modules.system.entity.SysUserThirdAuth;
import com.zc.modules.system.service.SysUserThirdAuthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangC
 * @create 2021-10-14-15:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Configuration
@Slf4j
@Api(tags = "三方服务")
public class ThirdLoginController {
    @Value("${oauth.gitee.clientId}")
    String giteeClientId;
    @Value("${oauth.gitee.clientSecret}")
    String giteeClientSecret;
    @Value("${oauth.gitee.redirectUri}")
    String giteeRedirectUri;

    private final SysUserThirdAuthService sysUserThirdAuthService;

    @GetMapping("/gitee/callback")
    public Object giteeLogin(AuthCallback callback) {
        AuthRequest authRequest = getGiteeAuthRequest();
        LoginController.verifyType.set("thirdVerfiy");
        AuthResponse response = authRequest.login(callback);
        AuthUser authUser = (AuthUser) response.getData();
        SysUserThirdAuth tmp=SysUserThirdAuth.builder()
                .openid(authUser.getUuid().toString())
                .loginType(AuthThirdTypeConstants.gitee)
                .build();
        //三方登录 有个可以绑定的,绑定了就可以实现登录功能
        SysUserThirdAuth sysUserThirdAuth = sysUserThirdAuthService.selectOneByParam(tmp);
        if (sysUserThirdAuth==null){
        //第一种情况,从未登录过
            sysUserThirdAuthService.insertOne(tmp);
        }else {
            if (sysUserThirdAuth.getUserId()==null){
                //第二种情况,三方登录过,但是未和本身绑定

            }else {
                //第三种情况,登录过,且绑定过
            }

        }


        return response;
    }

    private AuthRequest getGiteeAuthRequest() {
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId(giteeClientId)
                .clientSecret(giteeClientSecret)
                .redirectUri(giteeRedirectUri)
                .build());
    }
}
