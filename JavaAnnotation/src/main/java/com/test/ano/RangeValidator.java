package com.test.ano;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, Integer> {

    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // 或者根据需求返回false
        }
        if(value > Range.MIN && value < Range.MAX){
            System.out.println("验证错误");
        }
        return value > Range.MIN && value < Range.MAX;
    }
}
