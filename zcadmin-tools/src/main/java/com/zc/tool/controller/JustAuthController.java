package com.zc.tool.controller;

import com.xkcoding.http.config.HttpConfig;
import io.swagger.annotations.Api;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author ZhangC
 * @create 2021-08-26-17:03
 */


@RestController
@RequestMapping("/oauth")
@Configuration
@Api(tags = "三方服务")
public class JustAuthController {
    @Value("${oauth.github.clientId}")
    String githubClientId;
    @Value("${oauth.github.clientSecret}")
    String githubClientSecret;
    @Value("${oauth.github.redirectUri}")
    String githubRedirectUri;

    @Value("${oauth.gitee.clientId}")
    String giteeClientId;
    @Value("${oauth.gitee.clientSecret}")
    String giteeClientSecret;
    @Value("${oauth.gitee.redirectUri}")
    String giteeRedirectUri;


    @GetMapping("/gitee/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getGiteeAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @GetMapping("/gitee/callback")
    public Object giteeLogin(AuthCallback callback) {
        AuthRequest authRequest = getGiteeAuthRequest();
        return authRequest.login(callback);
    }



    @GetMapping("/github/render")
    public void renderGithubAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getGithubAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @GetMapping("/github/callback")
    public Object login(AuthCallback callback) {
        AuthRequest authRequest = getGithubAuthRequest();
        return authRequest.login(callback);
    }

    private AuthRequest getGithubAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId(githubClientId)
                .clientSecret(githubClientSecret)
                .redirectUri(githubRedirectUri)
                .httpConfig(HttpConfig.builder()
                        // Http 请求超时时间
                        .timeout(15000)
                        // host 和 port 请修改为开发环境的参数
                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)))
                        .build())
                .build());
    }

    private AuthRequest getGiteeAuthRequest() {
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId(giteeClientId)
                .clientSecret(giteeClientSecret)
                .redirectUri(giteeRedirectUri)
                .build());
    }



}
