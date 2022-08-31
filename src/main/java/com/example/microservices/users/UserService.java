package com.example.microservices.users;


import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    public List<UserDto> getAllUser() throws InterruptedException;

    public UserDto getById(Long id);

    public UserTable addOrUpdateUser(UserDto userDto);

    public String removeUser(Long id);


}
