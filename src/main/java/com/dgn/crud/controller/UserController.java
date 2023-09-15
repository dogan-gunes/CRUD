package com.dgn.crud.controller;

import com.dgn.crud.dto.UserCreateRequest;
import com.dgn.crud.dto.UserDto;
import com.dgn.crud.dto.UserUpdateDto;
import com.dgn.crud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateRequest userCreateRequest) {

        return new ResponseEntity<>(userService.createUser(userCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateDto userUpdateDto,@PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(userUpdateDto,id), HttpStatus.OK);
    }

}
