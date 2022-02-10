package com.example.validate;


import com.example.annotation.FlagValidator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zhuchao
 * @date 2022/2/9 6:55 下午
 */
@Data
public class TestDTO {
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotNull(message = "年龄不能为空")
    @Max(value = 120, message = "最大值不能大于120")
    @Min(value = 0, message = "最小值不能低于0")
    private Integer age;
    @NotNull(message = "标识位不能为空")
    @FlagValidator(value = {"0", "1"}, message = "标志位有误")
    private Integer flag;
}