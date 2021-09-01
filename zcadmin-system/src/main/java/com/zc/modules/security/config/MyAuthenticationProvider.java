package com.zc.modules.security.config;

import com.zc.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ZhangC
 * @create 2021-08-31-15:16
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    HttpSession session;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String code = req.getParameter("code");
        String phone = req.getParameter("phone");
        String verifyType = req.getParameter("verifyType");
        if ("phoneVerify".equals(verifyType)){
            //手机验证: 手机号和验证码登录
            String store_verifyCode = RedisUtil.StringOps.get("sms:login:" + phone);
            if (code==null||store_verifyCode==null||code!=store_verifyCode){
                throw new AuthenticationServiceException("验证码错误");
            }
        }else if ("thirdVerify".equals(verifyType)){
            //三方验证: 三方不需要验证 => 根据三方找到关联id 获取用户名即可

        }else {
            if (authentication.getCredentials() == null) {
                this.logger.debug("Failed to authenticate since no credentials provided");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            } else {
                String presentedPassword = authentication.getCredentials().toString();
                if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
                    this.logger.debug("Failed to authenticate since password does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }
            }
        }

    }
}
