package com.zc.annoation;

import java.lang.annotation.*;

/**
 * @author ZhangC
 * @create 2021-08-11-9:20
 */
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
}
