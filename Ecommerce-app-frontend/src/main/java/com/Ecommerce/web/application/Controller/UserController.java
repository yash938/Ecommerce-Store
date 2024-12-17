package com.Ecommerce.web.application.Controller;

import com.Ecommerce.web.application.Dto.DeleteUser;
import com.Ecommerce.web.application.Dto.PaegableResponse;
import com.Ecommerce.web.application.Dto.UserDto;

import com.Ecommerce.web.application.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id){
        UserDto userDto1 = userService.updateUser(id,userDto);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUser> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        DeleteUser userIsDeleted = new DeleteUser("User is deleted", LocalDate.now(), HttpStatus.OK);
        return new ResponseEntity<>(userIsDeleted,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<PaegableResponse<UserDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ) {
        PaegableResponse<UserDto> response = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
