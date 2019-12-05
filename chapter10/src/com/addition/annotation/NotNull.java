package com.addition.annotation;

import java.lang.annotation.*;

@Documented // 该注解纳入到Java开发手册
@Target(ElementType.FIELD) // 该注解的作用目标是字段（属性）
@Retention(RetentionPolicy.RUNTIME) // 该注解保留至运行阶段，这样能够通过反射机制调用
//定义了一个注解，在interface前面加上符号“@”，表示这是个注解
public @interface NotNull {}
