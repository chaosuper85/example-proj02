package com.example.validate;

/**
 * @author zhuchao
 * @date 2022/2/9 8:01 下午
 */

import com.example.annotation.FlagValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 标志位校验器
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {
    private String[] values;

    /**
     * 初始化
     *
     * @param flagValidator 注解上设置的值
     */
    @Override
    public void initialize(FlagValidator flagValidator) {
        this.values = flagValidator.value();
    }

    /**
     * 校验
     *
     * @param value  被校验的值，即输入
     * @param constraintValidatorContext 校验上下文
     * @return 返回true证明校验通过，false校验失败
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        // 当value为null，校验失败
        if (value == null) {
            return false;
        }
        //遍历校验
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(String.valueOf(value))) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
