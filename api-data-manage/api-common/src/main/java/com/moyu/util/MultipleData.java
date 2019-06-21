package com.moyu.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Auther: wishm
 * @Date: 2019/6/20 10:42
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RUNTIME)
@Documented
public @interface MultipleData {
    String value()default "master";
}
