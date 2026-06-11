package com.Estudo.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {

    @Bean
    public String HelloWorld(){
        return "Hello World";
    }
}
