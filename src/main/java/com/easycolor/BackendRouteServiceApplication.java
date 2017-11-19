package com.easycolor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan(value = {"com.easycolor"})
public class BackendRouteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendRouteServiceApplication.class, args);
    }

}
