package com.codegym.service;

import com.codegym.model.jwt.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<AppUser>, UserDetailsService {
    AppUser getUserByUsername(String username);

//    AppUser findByUsername(String username);
}
