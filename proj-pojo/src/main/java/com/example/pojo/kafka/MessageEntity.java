package com.example.pojo.kafka;

import java.util.Date;
import lombok.Data;

/**
 * @author zhuchao
 * @date 2022/2/3 6:21 下午
 */
@Data
public class MessageEntity {


    private Long id;

    private String msg;

    private Date sendTime;
}
