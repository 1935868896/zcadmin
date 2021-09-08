package com.zc.entity;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author ZhangC
 * @create 2021-09-08-16:06
 */

public class JwtAuthentication  extends UsernamePasswordAuthenticationToken {
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public JwtAuthentication(UsernamePasswordAuthenticationToken token) {
        super(token.getPrincipal(),token.getCredentials(),token.getAuthorities());
    }

    public JwtAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
