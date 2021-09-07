package com.zc.tool.service;

import com.zc.entity.EmailEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author ZhangC
 * @create 2021-08-27-10:47
 */
@Component
@Slf4j
public class EmailPost {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")  //发送人的邮箱  比如155156641XX@163.com
    private String from;

    //发送最简单的纯文本邮件
    @Async  //意思是异步调用这个方法
    public void sendMail(EmailEntity emailEntity) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from); // 发送人的邮箱
        message.setTo(emailEntity.getTo());     //接收人的邮箱
        message.setSubject(emailEntity.getTitle()); //标题
        message.setText(emailEntity.getContent()); //内容
        log.info("标题为'{}'的邮件开始发送给:{}",emailEntity.getTitle(),emailEntity.getTo());
        javaMailSender.send(message);

    }

    //发送带有附件的邮件
    @Async
    public void sendAttachmentsMail(EmailEntity emailEntity) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);    //发送人的邮箱
            helper.setTo(emailEntity.getTo());        //接收人的邮箱
            helper.setSubject(emailEntity.getTitle());//标题
            helper.setText(emailEntity.getContent(), true);//内容
            if (StringUtils.isNotBlank(emailEntity.getFileName())&&StringUtils.isNotBlank(emailEntity.getFilePath())) {
                FileSystemResource file = new FileSystemResource(new File(emailEntity.getFilePath()));
                helper.addAttachment(emailEntity.getFileName(), file);
            }
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
