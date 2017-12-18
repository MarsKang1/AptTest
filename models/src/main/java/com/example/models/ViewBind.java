package com.example.models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kangjiahang on 17/12/18.
 * Retention 代表什么时候生效 CLASS编译时 RUNNING运行时  SOUSE源码资源
 * Target 代表注解位置 FIELD代表属性
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface ViewBind {
    int value() default 0;
}
