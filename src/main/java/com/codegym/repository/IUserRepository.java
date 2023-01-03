package com.codegym.repository;

import com.codegym.model.jwt.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
