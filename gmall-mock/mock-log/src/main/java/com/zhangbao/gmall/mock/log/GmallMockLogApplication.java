package com.zhangbao.gmall.mock.log;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GmallMockLogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GmallMockLogApplication.class, args);
        MockTask mockTask = context.getBean(MockTask.class);

        mockTask.mainTask();
    }
}
