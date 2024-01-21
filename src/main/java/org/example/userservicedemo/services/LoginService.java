package org.example.userservicedemo.services;

import org.example.userservicedemo.exceptions.IncorrectPasswordException;
import org.example.userservicedemo.exceptions.UserNotFoundException;
import org.example.userservicedemo.models.User;
import org.example.userservicedemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String>  authenticateLogin(String username, String password){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Invalid username!");
        }
        User user = optionalUser.get();
        if(user.getPassword().equals(password)){
            return new ResponseEntity<>("Login successful!", HttpStatus.OK);
        }
        else{
            throw new IncorrectPasswordException("Invalid password!");
        }
    }
}
