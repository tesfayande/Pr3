package com.Project3.Backend.Controllers;


import com.Project3.Backend.Models.User;
import com.Project3.Backend.Repositories.UserRepository;
import com.Project3.Backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/users")

public class UsersController {


    @Autowired
    UserService userService;



    @Autowired
    UserRepository userRepository;



    @GetMapping
    public List<User> allUsers() {
        return userService.getAllUsers();

    }

    @GetMapping("/me")
    public User authenticatedUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return  userRepository.findByEmail(authentication.getName());
        } else {
            return null;
        }

    }




    @PostMapping("/add")
    public ResponseEntity<User> saveMessage(@RequestBody User user){

        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);


    }


    @GetMapping("{id}")
    // localhost:8080/api/users/1
    public ResponseEntity<User> getUserById(@PathVariable("id") int userId)
    {
        return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("/update/{id}")
    // localhost:8080/api/users/update/1
    public ResponseEntity<User> updateUser(@PathVariable("id") int id,@RequestBody User user)
    {
        return new ResponseEntity<User>(userService.updateUser(user,id),HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("/update-email/{id}")
    // localhost:8080/api/users/update-email/1
    public ResponseEntity<User> updateUserEmail(@PathVariable("id") int id,@RequestBody User user)
    {
        return new ResponseEntity<User>(userService.updateUserEmail(user,id),HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("/update-password/{id}")
    // localhost:8080/api/users/update-password/1
    public ResponseEntity<User> updateUserPassWord(@PathVariable("id") int id, @RequestBody User user)
    {
        return new ResponseEntity<User>(userService.updateUserPassWord(user,id),HttpStatus.OK);
    }


    //Delete Rest Api
    @DeleteMapping("/delete/{id}")
    // localhost:8080/api/users/delete/1
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id)
    {

       userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted Successfully.",HttpStatus.OK);
    }






}
