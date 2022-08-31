package com.example.microservices.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserTable, Long> {

    public UserTable findById(long id);

    public Optional<UserTable> findByName(String name);
}
