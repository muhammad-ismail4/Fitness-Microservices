package com.fitness.userservice.service;

import com.fitness.userservice.dto.CreateRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(CreateRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exist");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser);
    }

    public UserResponse getUserProfile(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(user);

    }

    public List<UserResponse> getAllUsers(){

        return userRepository.findAll().stream()
                .map(UserResponse:: new)
                .collect(Collectors.toList());

    }


    public UserResponse updateUser(String userId, CreateRequest updatedUser){

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());

        User savedUser = userRepository.save(existingUser);

       return new UserResponse(savedUser);


    }

    public void deleteUser(String userId){
        if(!userRepository.existsById(userId)){
            throw new RuntimeException(" User not found with id " + userId);
        }
        userRepository.deleteById(userId);
    }



 }
