package com.example.microservices.users;


import com.example.microservices.exception.MicroServiceException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;


//    @Cacheable("users")
//    @CacheEvict(value = "users",allEntries = true)
//    @CachePut(cacheNames = "users")
    @Override
    public List<UserDto> getAllUser() throws InterruptedException {
//        Thread.sleep(1000);
        log.info("Getting all user");
        List<UserTable> userList = Lists.newArrayList(userRepo.findAll());
        if( userList.isEmpty()) {
            throw new MicroServiceException("NO USER PRESENT");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<UserDto> userDtoList = new ArrayList<>();
        userList.forEach(user -> {
            userDtoList.add(mapper.convertValue(user, UserDto.class));
        });
        return userDtoList ;
    }

    @Override
    public UserDto getById(Long id) {
        Optional<UserTable> user = userRepo.findById(id);
        if(!user.isPresent()) {
            throw new MicroServiceException("NO USER PRESENT FOR THIS ID: " +id);
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user.get(),userDto);
        return userDto;
    }

    //    @CachePut(cacheNames = "users")
    @Override
    public UserTable addOrUpdateUser(UserDto userDto) {
        log.info("Adding or updating user");
        Optional<UserTable> ifExists = null;
        if(userDto.getId() != null) {
            ifExists = userRepo.findById(userDto.getId());
        }
        UserTable user = new UserTable();
        user.setName(userDto.getName());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActive(true);
        if(Objects.nonNull(ifExists)){
            user.setId(ifExists.get().getId());
        }
        return userRepo.save(user);
    }


    @Override
    public String removeUser(Long id) {
        log.info("Removing user");
        try {
            userRepo.deleteById(id);
        }catch (Exception e){
            throw new MicroServiceException("No user found with this id");
        }

        return "User Deleted";
    }
}
