package com.example.microservices.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<UserRoles,Long> {
}
