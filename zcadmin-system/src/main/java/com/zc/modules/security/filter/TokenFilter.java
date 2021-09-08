package com.zc.modules.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zc.jwt.JwtUtil;
import com.zc.modules.security.entity.OnlineUserDto;
import com.zc.utils.RedisUtil;
import com.zc.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangC
 * @create 2021-08-10-14:44
 */
@RequiredArgsConstructor
@Slf4j
public class TokenFilter extends OncePerRequestFilter /*GenericFilterBean*/ {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    @Autowired
    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = httpServletRequest.getHeader("X-Token");
        String requestURI = httpServletRequest.getRequestURI();
        if (token != null) {
            if (RedisUtil.StringOps.get("jwt-black:list:"+token)!=null){
                httpServletRequest.getRequestDispatcher("/error/jwt/black").forward(httpServletRequest, httpServletResponse);

            return;
            }

            String usernameFromToken = jwtUtil.getUsernameFromToken(token);
            //此处可以通过redis缓存解决每次都要请求数据库的问题
            UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);
            Boolean aBoolean = jwtUtil.validateToken(token, userDetails);
            if (aBoolean) {
                //此处当jwt有效的时候,我们将user实体类和用户的权限传入,放置到上下文当中
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //在此处将用户写入redis
                OnlineUserDto build = OnlineUserDto.builder()
                        .username(userDetails.getUsername())
                        .nickName(jwtUtil.getNickNameFromToken(token))
                        .address(StringUtils.getAddress(httpServletRequest))
                        .ip(StringUtils.getIp(httpServletRequest))
                        .browser(StringUtils.getBrowser(httpServletRequest))
                        .token(token)
                        .loginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                        .build();
                RedisUtil.StringOps.setEx(
                        "online:user:"+token, JSONObject.toJSONString(build),3, TimeUnit.MINUTES
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
//                httpServletRequest.setAttribute("filter.error", e);
                //将异常分发到/error/exthrow控制器
//                handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse
//                        , null, new ExpiredException(""));
                if ("/system/refresh/token".equals(requestURI)){
                    httpServletRequest.setAttribute("X-Token",token);
                    httpServletRequest.getRequestDispatcher("/system/refresh/token").forward(httpServletRequest,httpServletResponse);
                }else {
                    httpServletRequest.getRequestDispatcher("/error/expire").forward(httpServletRequest, httpServletResponse);
                }
           return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.error("进入");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String token = httpServletRequest.getHeader("token");
//        if(token!=null) {
//            String usernameFromToken = jwtUtil.getUsernameFromToken(token);
//            //此处可以通过redis缓存解决每次都要请求数据库的问题
//            UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);
//            Boolean aBoolean = jwtUtil.validateToken(token, userDetails);
//            if(aBoolean) {
//                //此处当jwt有效的时候,我们将user实体类和用户的权限传入,放置到上下文当中
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//        filterChain.doFilter(httpServletRequest, servletResponse);
//    }
}
