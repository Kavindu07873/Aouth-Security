package com.OauthProject.OAuth.service;

import com.OauthProject.OAuth.entity.UserEntity;
import com.OauthProject.OAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserDetailsServiceImpl implements UserDetailsService  ,UserService{

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity =null;

        try {
            userEntity = userRepository.findByUserName(username);
            return new User(userEntity.getUserName() , userEntity.getPassword() , new ArrayList<>());
        }catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("User" + username   + " Was not found");
        }

    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public UserEntity save(UserEntity user) {
        System.out.println(user);
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user);
        return userRepository.save(user);    }

    @Override
    public void delete(Long id) {

    }


}
