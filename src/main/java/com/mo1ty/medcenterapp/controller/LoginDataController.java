package com.mo1ty.medcenterapp.controller;


import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.entity.LoginData;
import com.mo1ty.medcenterapp.repository.LoginDataRepository;
import com.mo1ty.medcenterapp.service.interfaces.LoginDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class LoginDataController {

    LoginDataRepository loginDataRepository;
    PasswordEncoder passwordEncoder;
    LoginDataService loginDataService;

    @Autowired
    public LoginDataController(LoginDataRepository loginDataRepository, PasswordEncoder passwordEncoder, LoginDataService loginDataService) {
        this.loginDataRepository = loginDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginDataService = loginDataService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Contact contactData) {
        return loginDataService.registerUser(contactData);
    }

    @GetMapping("/login")
    public LoginData getLoginDetails(Authentication authentication) {
        List<LoginData> loginData = loginDataRepository.findByEmail(authentication.getName());
        if (loginData.size() > 0) {
            return loginData.get(0);
        } else {
            return null;
        }
    }

}
