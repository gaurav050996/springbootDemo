package com.example.microservices.AppEnv;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevEnv implements AppEnv{
    @Override
    public String name() {
        return "Dev_env";
    }
}
