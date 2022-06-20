package com.taik.webshop.service;

import com.taik.webshop.domain.User;
import com.taik.webshop.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDTO);

    void save(User user);

    List<UserDto> getAll();

    User findByName(String name);

    void updateProfile(UserDto userDTO);
}
