package com.example.agri.controller;

import com.example.agri.DTO.LoginResponse;
import com.example.agri.DTO.LoginUserDto;
import com.example.agri.Entity.User;
import com.example.agri.service.AuthenticationService;
import com.example.agri.service.JwtService;
import com.example.agri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

//    @PostMapping("/signup")
//    public String signup(@RequestBody User user) {
//        userService.registerUser(user.getUsername(), user.getPassword());
//        return "User registered successfully";
//    }

//    @PostMapping("/signin")
//    public String signin(@RequestBody User user) {
//        User existingUser = (User) userService.getUserByUsername(user.getUsername());
//
//        if (!existingUser.getPassword().matches(user.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//        return "User signed in successfully";
//    }



    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = authenticationService.signup(user);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
