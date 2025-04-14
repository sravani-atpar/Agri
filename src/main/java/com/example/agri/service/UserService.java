package com.example.agri.service;
import com.example.agri.Entity.User;
import com.example.agri.Repository.UserRepository;
//import org.apache.catalina.Group;
//import org.apache.catalina.Role;
//import org.apache.catalina.User;
//import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
//    public static User findById() {
//        return findById(null);
//    }

//    @Override
    public  User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
       User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(User.Role.valueOf(role));
//        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public  List<User> findAll(){

        return userRepository.findAll();

    }
    public User saveUser( User user) {


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }
    public User updatePassword(Long id , String password ) {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null) {
            existingUser.setPassword(password);
            return userRepository.save(existingUser);
        }
        return null;

    }

    public User updateUser(User user, Long id) {
        User existingUser =findById(id );
        if(user.getUsername() != null){
            existingUser.setUsername(user.getUsername());
        }
      if(user.getFirstName() != null){
          existingUser.setFirstName(user.getFirstName());
      }
        if(user.getLastName() != null){
            existingUser.setLastName(user.getLastName());
        }
      if(user.getEmail() != null){
          existingUser.setEmail(user.getEmail());
      }

       if(user.getRole() != null){
           existingUser.setRole(user.getRole());
       }
       if(user.getAddress() != null){
           existingUser.setAddress(user.getAddress());
       }

//        existingUser.setPassword(user.getPassword());
//        existingUser.setRole(user.getRole());
//
        return userRepository.save(existingUser);
    }

    public void deleteUser( Long id) {
        User existingUser =findById(id);
        userRepository.delete(existingUser);
    }
}
