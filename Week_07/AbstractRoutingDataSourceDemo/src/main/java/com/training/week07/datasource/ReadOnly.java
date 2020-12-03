package com.training.week07.datasource;

import java.lang.annotation.*;

/**
 * 数据库处理只读标识
 * 
 * @author Billion
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadOnly {

    String name() default "";

}