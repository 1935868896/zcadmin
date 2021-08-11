package com.zc.modules.security.filter;

import com.zc.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ZhangC
 * @create 2021-08-10-14:44
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TokenFilter extends OncePerRequestFilter /*GenericFilterBean*/ {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = httpServletRequest.getHeader("token");
        if(token!=null) {
            String usernameFromToken = jwtUtil.getUsernameFromToken(token);
            //此处可以通过redis缓存解决每次都要请求数据库的问题
            UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);
            Boolean aBoolean = jwtUtil.validateToken(token, userDetails);
            if(aBoolean) {
                //此处当jwt有效的时候,我们将user实体类和用户的权限传入,放置到上下文当中
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
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
