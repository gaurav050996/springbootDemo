package com.example.microservices;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class config {

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new Bcry
//    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
