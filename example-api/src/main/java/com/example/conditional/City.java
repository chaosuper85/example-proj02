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
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private String cityName;
    private Integer cityCode;
}