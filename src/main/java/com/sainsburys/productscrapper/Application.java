package com.sainsburys.productscrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan("com.sainsburys")
@EnableAutoConfiguration
@PropertySources({@PropertySource(value = "classpath:application.properties")})
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}