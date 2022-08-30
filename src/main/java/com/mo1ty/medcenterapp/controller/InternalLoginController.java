package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.InternalLogin;
import com.mo1ty.medcenterapp.service.interfaces.InternalLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal-logins")
public class InternalLoginController {

    @Autowired
    InternalLoginService internalLoginService;

    @GetMapping("")
    public List<InternalLogin> getAllInternalLogins(){
        return internalLoginService.findAll();
    }

    @GetMapping("/{internalLoginId}")
    public InternalLogin getInternalLogin(@PathVariable int internalLoginId){
        InternalLogin internalLogin = internalLoginService.findById(internalLoginId);

        if(internalLogin == null){
            throw new DataNotFoundException("Internal login with id " + internalLoginId + " was not found!");
        }

        return internalLogin;
    }

    @PostMapping("")
    public InternalLogin addInternalLogin(@RequestBody InternalLogin internalLogin){
        return internalLoginService.createInternalLogin(internalLogin);
    }

    @PutMapping("")
    public InternalLogin updateInternalLogin(@RequestBody InternalLogin internalLogin){
        InternalLogin intLogin = internalLoginService.findById(internalLogin.getInternalId());

        if(intLogin == null){
            throw new DataNotPresentException("Requested internal login does not exist! Consider adding a new entity instead.");
        }

        return internalLoginService.updateInternalLogin(internalLogin);
    }

    @DeleteMapping("/{internalLoginId}")
    public InternalLogin deleteInternalLogin(@PathVariable int internalLoginId){

        // Will not execute if any doctor has this login

        InternalLogin intLog = internalLoginService.findById(internalLoginId);

        if(intLog == null){
            throw new DataNotPresentException("Requested internal login is not present in the database!");
        }

        internalLoginService.deleteInternalLogin(internalLoginId);

        return intLog;
    }

}
