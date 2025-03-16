package com.Project3.Backend.Controllers;


import com.Project3.Backend.Models.User;
import com.Project3.Backend.Repositories.UserRepository;
import com.Project3.Backend.Services.AuthService;
import com.Project3.Backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

@RequestMapping("/api/auth")
public class AuthControllr {



    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;


 
    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public User register(@RequestBody User user){
        return  authService.register(user);
    }



    @PostMapping("/login")
    public Object login(@RequestBody User user){

        Map<String, Object> object = new HashMap<>();
        object.put("token", authService.verify(user));
        //object.put("user", userService.getCurrentUser(user.getName()));
        object.put("user", userService.getCurrentUserByEmail(user.getEmail()));
        return object;



    }
}
