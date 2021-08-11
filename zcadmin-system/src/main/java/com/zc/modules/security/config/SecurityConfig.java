package com.zc.modules.security.config;

/**
 * @author ZhangC
 * @create 2021-08-02-17:14
 */

import com.zc.annoation.Anonymous;
import com.zc.jwt.JwtUtil;
import com.zc.modules.security.filter.TokenFilter;
import com.zc.utils.RequestMethodUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final WebApplicationContext applicationContext;
    /**
     * 对swagger进行放行
     * https://stackoverflow.com/questions/37671125/how-to-configure-spring-security-to-allow-swagger-url-to-be-accessed-without-aut
     */
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/doc.html",
            "/tool/gen/genCode/**",
//            "/project/bookInfo/**"
            // other public endpoints of your API may be appended to this array
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        Map<String, Set<String>> anonymousUrls = getAnonymousUrls();
        http
                .csrf().disable()      //关闭防跨域,不关闭的话post请求无法进来
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET, anonymousUrls.get("GET").toArray(new String[0])).permitAll()
                .antMatchers(HttpMethod.POST, anonymousUrls.get("POST").toArray(new String[0])).permitAll()
                .antMatchers(HttpMethod.PATCH, anonymousUrls.get("PATCH").toArray(new String[0])).permitAll()
                .antMatchers(HttpMethod.DELETE, anonymousUrls.get("DELETE").toArray(new String[0])).permitAll()
                .antMatchers(HttpMethod.PUT, anonymousUrls.get("PUT").toArray(new String[0])).permitAll()
                .antMatchers(anonymousUrls.get("ALL").toArray(new String[0])).permitAll()

//                .antMatchers("/**").permitAll()
                //去掉表单登录方式和登录界面
                .anyRequest().authenticated()
        ;
        http.addFilterBefore(new TokenFilter(authenticationManagerBuilder, jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方式
        return new BCryptPasswordEncoder();
    }

    public Map<String, Set<String>> getAnonymousUrls() {
        Map<String, Set<String>> anonymousUrls = new HashMap<>(6);
        anonymousUrls.put("GET", new HashSet<>());
        anonymousUrls.put("PUT", new HashSet<>());
        anonymousUrls.put("POST", new HashSet<>());
        anonymousUrls.put("PATCH", new HashSet<>());
        anonymousUrls.put("DELETE", new HashSet<>());
        anonymousUrls.put("ALL", new HashSet<>());
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.forEach((k, v) -> {
            if (v.hasMethodAnnotation(Anonymous.class)) {

                ArrayList<RequestMethod> requestMethods = new ArrayList<>(k.getMethodsCondition().getMethods());
                Set<String> strings = anonymousUrls.get(RequestMethodUtil.find(requestMethods.size() == 0 ? "All" : requestMethods.get(0).name()));
                strings.addAll(k.getPatternsCondition().getPatterns());
            }
        });

        return anonymousUrls;
    }

}
