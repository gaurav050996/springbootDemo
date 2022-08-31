package com.example.microservices.users;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phoneNo;
    @NotEmpty
    private String password;
}

