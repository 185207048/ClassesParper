package com.test.ano;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD}) // 可以应用于字段或方法参数
@Retention(RetentionPolicy.RUNTIME)
public @interface  Range {
    final static int MIN = 10;

    final static int  MAX = 100;

    int getMin() default MIN;
    int getMax() default MAX;

    String message() default "Value must be between 10 and 100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
