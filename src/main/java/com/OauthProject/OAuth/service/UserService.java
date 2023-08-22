package com.OauthProject.OAuth.service;

import com.OauthProject.OAuth.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    public UserDetails loadUserByUsername(String username);

    List<UserEntity> findAll();

    UserEntity save(UserEntity user);

    void delete(Long id);
}
