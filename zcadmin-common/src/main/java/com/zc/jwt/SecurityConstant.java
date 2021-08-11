package com.zc.jwt;

/**
 * @author ZhangC
 * @create 2021-08-10-14:39
 */
public interface SecurityConstant {
    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = "myJWTSignKey";

    /**
     * token请求头
     */
    String HEADER = "accessToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * JWT过期时间, 毫秒
     */
    Long EXPIRATION_TIME = (long) (30 * 60 * 1000);
}
