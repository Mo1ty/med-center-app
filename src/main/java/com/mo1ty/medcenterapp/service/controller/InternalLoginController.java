package com.mo1ty.medcenterapp.service.controller;

import com.mo1ty.medcenterapp.entity.InternalLogin;
import com.mo1ty.medcenterapp.service.interfaces.InternalLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal-logins")
public class InternalLoginController {

    private InternalLoginService internalLoginService;

    @Autowired
    public InternalLoginController(InternalLoginService internalLoginService){
        this.internalLoginService = internalLoginService;
    }

    @GetMapping
    public List<InternalLogin> getAllInternalLogins(){
        return internalLoginService.findAll();
    }

    @GetMapping("/{internalLoginId}")
    public InternalLogin getInternalLogin(@PathVariable int internalLoginId){
        return internalLoginService.findById(internalLoginId);
    }

    @PostMapping
    public InternalLogin addInternalLogin(@RequestBody InternalLogin internalLogin){
        return internalLoginService.createInternalLogin(internalLogin);
    }

    @PutMapping
    public InternalLogin updateInternalLogin(@RequestBody InternalLogin internalLogin){
        return internalLoginService.updateInternalLogin(internalLogin);
    }

    @DeleteMapping("/{internalLoginId}")
    public InternalLogin deleteInternalLogin(@PathVariable int internalLoginId){
        InternalLogin intLog = internalLoginService.findById(internalLoginId);
        internalLoginService.deleteInternalLogin(internalLoginId);
        return intLog;
    }

}
