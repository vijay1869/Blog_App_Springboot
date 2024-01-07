package com.blog.BlogAppApis.controllers;

import com.blog.BlogAppApis.payloads.ApiResponse;
import com.blog.BlogAppApis.payloads.UserDto;
import com.blog.BlogAppApis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //post-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //put-update user
    @PutMapping("/{UserId}")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto,@PathVariable("UserId") Integer uid){
        UserDto updatedUserDto=this.userService.updateUser(userDto,uid);
        return ResponseEntity.ok(updatedUserDto);//why do we need to reemove new for ok
    }
    //delete -delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){//? if as we dont know type?Any type?
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted Succesfully",true),HttpStatus.OK);
    }
    //get-user get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userid}")//URI variable(I-Identifier)
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userid){
        return ResponseEntity.ok(this.userService.getUserById(userid));
    }

}
