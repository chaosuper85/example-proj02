package com.example.annotation;

import com.example.validate.FlagValidatorClass;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author zhuchao
 * @date 2022/2/9 8:00 下午
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = FlagValidatorClass.class) // 绑定对应校验器
public @interface FlagValidator {
    String[] value() default {};
    String message() default "flag is not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}