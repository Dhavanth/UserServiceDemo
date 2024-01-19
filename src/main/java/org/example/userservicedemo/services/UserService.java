package org.example.userservicedemo.services;

import org.example.userservicedemo.exceptions.UserNotFoundException;
import org.example.userservicedemo.models.Address;
import org.example.userservicedemo.models.Geolocation;
import org.example.userservicedemo.models.Name;
import org.example.userservicedemo.models.User;
import org.example.userservicedemo.repositories.AddressRepository;
import org.example.userservicedemo.repositories.GeoLocationRepository;
import org.example.userservicedemo.repositories.NameRepository;
import org.example.userservicedemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private NameRepository nameRepository;
    private AddressRepository addressRepository;
    private GeoLocationRepository geoLocationRepository;


    @Autowired
    public UserService(UserRepository userRepository,
                       NameRepository nameRepository,
                       AddressRepository addressRepository,
                       GeoLocationRepository geoLocationRepository) {
        this.userRepository = userRepository;
        this.nameRepository = nameRepository;
        this.addressRepository = addressRepository;
        this.geoLocationRepository = geoLocationRepository;

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
        //SETTING NAME ATTRIBUTE
        Name givenName = user.getName();
        Optional<Name> optionalName = nameRepository.findByFirstNameAndLastName(givenName.getFirstName(), givenName.getLastName());
        if(optionalName.isEmpty()){
            Name savedName = nameRepository.save(givenName);
            user.setName(savedName);
        }else{
            user.setName(optionalName.get());
        }

        //SETTING ADDRESS ATTRIBUTE
        Address givenAddress = user.getAddress();
        Geolocation givenGeolocation = givenAddress.getGeolocation();
        Optional<Geolocation> optionalGeolocation = geoLocationRepository.findByLatitudeAndLongitude(givenGeolocation.getLatitude(), givenGeolocation.getLongitude());
        if(optionalGeolocation.isEmpty()) {
            Geolocation savedGeolocation = geoLocationRepository.save(givenGeolocation);
            givenAddress.setGeolocation(savedGeolocation);
        }else{
            givenAddress.setGeolocation(optionalGeolocation.get());
        }
        user.setAddress(addressRepository.save(givenAddress));
        User savedUser = userRepository.save(user);
        savedUser.setUsername(user.getUsername());
        savedUser.setPassword(user.getPassword());
        savedUser.setEmail(user.getEmail());
        savedUser.setPhoneNumber(user.getPhoneNumber());

        return savedUser;
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
        if(user.getUsername() != null){
            existingUser.setUsername(user.getUsername());
        }
        if(user.getPhoneNumber() != null){
            existingUser.setPhoneNumber(user.getPhoneNumber());
        }
        if(user.getName() != null){
            Name savedName = nameRepository.save(user.getName());
            existingUser.setName(savedName);
        }
        if(user.getAddress() != null){
            Geolocation savedGeolocation = geoLocationRepository.save(user.getAddress().getGeolocation());
            user.getAddress().setGeolocation(savedGeolocation);
            Address savedAddress = addressRepository.save(user.getAddress());
            existingUser.setAddress(savedAddress);
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

        Name savedname = nameRepository.save(user.getName());
        existingUser.setName(savedname);

        Geolocation savedGeolocation = geoLocationRepository.save(user.getAddress().getGeolocation());
        user.getAddress().setGeolocation(savedGeolocation);
        Address savedAddress = addressRepository.save(user.getAddress());
        existingUser.setAddress(savedAddress);

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
