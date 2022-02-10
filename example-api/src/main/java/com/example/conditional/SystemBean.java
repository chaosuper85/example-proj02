package com.example.conditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhuchao
 * @date 2022/2/9 3:01 下午
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemBean {
    private String systemName;
    private String systemCode;
}
