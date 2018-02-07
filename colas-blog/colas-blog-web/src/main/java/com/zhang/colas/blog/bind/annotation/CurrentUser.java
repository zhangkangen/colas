package com.zhang.colas.blog.bind.annotation;

import com.zhang.colas.blog.utils.Constants;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    String value() default Constants.CURRENT_USER;
}
