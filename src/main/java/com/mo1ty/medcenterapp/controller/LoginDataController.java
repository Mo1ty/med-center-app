package com.mo1ty.medcenterapp.controller;


import com.mo1ty.medcenterapp.entity.LoginData;
import com.mo1ty.medcenterapp.repository.LoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginDataController {

    LoginDataRepository loginDataRepository;

    @Autowired
    public LoginDataController(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody LoginData userData) {
        LoginData savedUser = null;
        ResponseEntity response = null;
        try {
            savedUser = loginDataRepository.save(userData);
            if(savedUser.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are registered!");
            }
        }
        catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An excepion occured! Message: " + ex.getLocalizedMessage());
        }
        return response;
    }

}
