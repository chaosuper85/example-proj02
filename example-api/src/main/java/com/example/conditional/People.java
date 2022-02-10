package com.example.conditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhuchao
 * @date 2022/2/9 4:17 下午
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class People {
    private String name;
    private Integer age;
    private City city;
}