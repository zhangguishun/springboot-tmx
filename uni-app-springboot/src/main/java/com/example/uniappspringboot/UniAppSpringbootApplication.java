package com.example.uniappspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class UniAppSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniAppSpringbootApplication.class, args);
    }

}
