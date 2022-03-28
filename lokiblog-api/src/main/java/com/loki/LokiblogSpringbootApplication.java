package com.loki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.loki.mapper")
public class LokiblogSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LokiblogSpringbootApplication.class, args);
    }

}
