package com.example.upview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class UpViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpViewApplication.class, args);
    }

}
