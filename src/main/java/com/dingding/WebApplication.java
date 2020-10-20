package com.dingding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zxb
 * @date 2019/9/27 9:53
 */
@SpringBootApplication(exclude = {org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration.class})
public class WebApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
