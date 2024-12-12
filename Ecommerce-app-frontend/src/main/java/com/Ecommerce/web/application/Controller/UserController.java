package com.Ecommerce.web.application.Controller;

import com.Ecommerce.web.application.Dto.DeleteUser;
import com.Ecommerce.web.application.Model.User;
import com.Ecommerce.web.application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUsers(@RequestBody User user){
        System.out.println("user "  + user);

        User createUser = userService.createUser(user);
        System.out.println("create user "+ createUser);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){
        User updateUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUser> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        DeleteUser delete = DeleteUser.builder().message("User is deleted").status(HttpStatus.OK).deletedAt(LocalDate.now()).build();
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }


}
