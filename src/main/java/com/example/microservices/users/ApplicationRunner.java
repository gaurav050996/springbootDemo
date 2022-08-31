package com.example.microservices.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class ApplicationRunner implements CommandLineRunner {



    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        log.info("getting all data @: " + new Date());
//        userService.getAllUser();
//        log.info("getting all data @: " + new Date());
//        userService.getAllUser();
//        log.info("getting all data @: " + new Date());
//        userService.getAllUser();
//        log.info("getting all data @: " + new Date());
//        userService.getAllUser();
//        log.info("getting all data @: " + new Date());
//        userService.getAllUser();
    }
}
