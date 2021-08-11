package com.zc.utils;

import com.google.common.collect.Sets;

import java.util.HashSet;

/**
 * @author ZhangC
 * @create 2021-08-11-10:20
 */
public class RequestMethodUtil {

    public static String find(String type) {
        HashSet<String> methods = Sets.newHashSet("GET", "POST", "PATCH", "PUT", "DELETE");
       if (methods.contains(type)){
           return type;
       }
        return "ALL";
    }
}
