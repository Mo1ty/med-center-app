package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.LoyaltyLevel;

import java.util.List;

public interface LoyaltyLevelService {

    LoyaltyLevel findById(int id);

    List<LoyaltyLevel> findAll();

}
