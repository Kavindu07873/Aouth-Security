package com.OauthProject.OAuth.Controller;

import com.OauthProject.OAuth.entity.UserEntity;
import com.OauthProject.OAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping(value="/user")
    public List<UserEntity> listUser(){
        return userService.findAll();
    }

    @PostMapping(value = "/user")
    public UserEntity saveUser(@RequestBody UserEntity user){
        return userService.save(user);
    }

    @PutMapping("/user/{id}")
    public UserEntity updateUser(@RequestBody UserEntity user){
        return userService.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "User Deleted Successfully!";
    }

}
