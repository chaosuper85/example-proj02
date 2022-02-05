package com.example.xxljob;

import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 * @date 2022/2/5 6:06 下午
 */
@Component
public class TestHandler  {

    @XxlJob(value = "testJobHandler")
    public void execute() throws Exception {
        XxlJobContext xxlJobContext = XxlJobContext.getXxlJobContext();
        String param = xxlJobContext.getJobParam();
        try {
            //自定义调用接口
            System.out.println("执行中, param:" + param);
        } catch (Exception e) {

        }
    }
}
