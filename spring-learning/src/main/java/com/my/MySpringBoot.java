package com.my;

import com.my.bean.Config;
import com.my.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties
 * https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.build-systems.starters
 */
@SpringBootApplication
public class MySpringBoot {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MySpringBoot.class, args);
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
        User user = context.getBean("user2", User.class);
        System.out.println(user.getName() + ": " + user.getAge());

        String[] beanNames = context.getBeanNamesForType(User.class);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        String[] configBeanNames = context.getBeanNamesForType(Config.class);
        for (String beanName : configBeanNames) {
            System.out.println(beanName);
        }
//        Config config = context.getBean("config", Config.class);
        Config config = context.getBean("my.config-com.my.bean.Config", Config.class);
        System.out.println(config.getHost() + ": " + config.getPort());
    }
}