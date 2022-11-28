package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.entity.LoyaltyLevel;
import com.mo1ty.medcenterapp.service.interfaces.LoyaltyLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loyalty")
public class LoyaltyLevelController {

    LoyaltyLevelService loyaltyLevelService;

    @Autowired
    public LoyaltyLevelController(LoyaltyLevelService loyaltyLevelService) {
        this.loyaltyLevelService = loyaltyLevelService;
    }

    @GetMapping("/{id}")
    public LoyaltyLevel findById(@PathVariable int id) {
        return this.loyaltyLevelService.findById(id);
    }

    @GetMapping
    public List<LoyaltyLevel> findAll() {
        return loyaltyLevelService.findAll();
    }

}
