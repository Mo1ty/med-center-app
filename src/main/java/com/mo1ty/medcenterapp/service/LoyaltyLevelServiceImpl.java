package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.LoyaltyLevel;
import com.mo1ty.medcenterapp.entity.publ.DoctorPublic;
import com.mo1ty.medcenterapp.repository.LoyaltyLevelRepository;
import com.mo1ty.medcenterapp.service.interfaces.LoyaltyLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LoyaltyLevelServiceImpl implements LoyaltyLevelService {

    LoyaltyLevelRepository loyaltyLevelRepository;

    @Autowired
    public LoyaltyLevelServiceImpl(LoyaltyLevelRepository loyaltyLevelRepository) {
        this.loyaltyLevelRepository = loyaltyLevelRepository;
    }

    @Override
    public LoyaltyLevel findById(int id) {
        List<LoyaltyLevel> loyaltyLevelList = loyaltyLevelRepository.findAllById(Collections.singletonList(id));
        if(loyaltyLevelList.size() == 1) {
            return loyaltyLevelList.get(0);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    @Override
    public List<LoyaltyLevel> findAll() {
        return loyaltyLevelRepository.findAll();
    }
}
