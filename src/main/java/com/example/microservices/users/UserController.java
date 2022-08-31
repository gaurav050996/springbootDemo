package com.example.microservices.users;


import com.example.microservices.exception.ErrorMessage;
import com.example.microservices.exception.MicroServiceException;
//import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@Validated
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationRunner applicationRunner;

    @Autowired
    RestTemplate restTemplate;


    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUser() throws InterruptedException {
        try {
            return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);

    }

    @PostMapping(value = "add-update")
    public ResponseEntity<?> addUpdateUser(@Valid @RequestBody UserDto userDto){

        UserTable userTable = userService.addOrUpdateUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userTable);
    }

    @DeleteMapping(value = "remove/{id}")
    public ResponseEntity<String> removeUser(@PathVariable(name = "id") Long id){
        String res = userService.removeUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping(name = "call Api using rest template", value = "/callAPI")
    public ResponseEntity<?> callApi() throws URISyntaxException {
        ResponseEntity<?> plainResponse = restTemplate.exchange(RequestEntity.get(new URI("http://localhost:8080/user/all")).build(), List.class);
        log.info("Received plain res from /all api");
        return new ResponseEntity<>(plainResponse.getBody(),HttpStatus.OK);
    }

    @PostMapping(value = "/add-rest")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) throws URISyntaxException {
        ResponseEntity<Boolean> booleanResponse = restTemplate.postForEntity(new URI("http://localhost:8080/user/add-update"),userDto,Boolean.class);
        log.info("User added rest uri");
        log.info("{}",booleanResponse.getBody());
        return new ResponseEntity<>(booleanResponse.getBody(),HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-res/{id}")
    public ResponseEntity<?> removeUserRes(@PathVariable(name = "id") Long id) throws URISyntaxException {
        try {
            ResponseEntity<?> booleanResponse = restTemplate.exchange(RequestEntity.delete(new URI("http://localhost:8080/user/remove/" + id)).build(), String.class);
        }catch (Exception e){
            throw new MicroServiceException(e.getMessage());
        }
//        return new ResponseEntity<>(booleanResponse.getBody(),HttpStatus.OK);
        return null;
    }


}
