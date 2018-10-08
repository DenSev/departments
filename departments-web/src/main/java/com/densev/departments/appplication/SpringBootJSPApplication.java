package com.densev.departments.appplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created on: 10/05/18
 */
@SpringBootApplication
public class SpringBootJSPApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootJSPApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJSPApplication.class, args);
    }
}
