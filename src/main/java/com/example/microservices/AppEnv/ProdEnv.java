package com.example.microservices.AppEnv;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdEnv implements AppEnv{
    @Override
    public String name() {
        return "Prod_Env";
    }
}
