package com.example.microservices.users;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class UserRoles {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
