package org.example.userservicedemo.services;

import org.example.userservicedemo.exceptions.UserNotFoundException;
import org.example.userservicedemo.models.User;
import org.example.userservicedemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        return optionalUser.get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id: " + id + " not found!");
        }
        User existingUser = optionalUser.get();
        if(user.getEmail() != null){
            existingUser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null){
            existingUser.setPassword(user.getPassword());
        }
        if(user.getName() != null){
            existingUser.setName(user.getName());
        }
        if(user.getAddress() != null){
            existingUser.setAddress(user.getAddress());
        }
        if(user.getPhoneNumber() != null){
            existingUser.setPhoneNumber(user.getPhoneNumber());
        }
        return userRepository.save(existingUser);
    }

    public User replaceUser(Long id, User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id: " + id + " not found!");
        }
        User existingUser = optionalUser.get();
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());
        existingUser.setAddress(user.getAddress());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(existingUser);
    }

    public boolean deleteUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id: " + id + " not found!");
        }
        userRepository.deleteById(id);
        return true;
    }


}
