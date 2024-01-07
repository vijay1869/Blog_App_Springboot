package com.blog.BlogAppApis.services.Impl;

import com.blog.BlogAppApis.entities.User;
import com.blog.BlogAppApis.exceptions.ResourceNotFoundException;
import com.blog.BlogAppApis.payloads.UserDto;
import com.blog.BlogAppApis.respositories.UserRepo;
import com.blog.BlogAppApis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        //why userId?-to check existence
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser=this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        return userToDto(user);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
//        List<UserDto> userDtos=new ArrayList<>();
//        for(User user:users){
//            userDtos.add(userToDto(user));
//        }
       // return users.stream().map(user -> this.userToDto(user)).toList();
       //return userDtos;
        return users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        //this.userRepo.deleteById(userId);
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setAbout(userDto.getAbout());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());

        return user;
    }

    private UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setAbout(user.getAbout());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }


}
