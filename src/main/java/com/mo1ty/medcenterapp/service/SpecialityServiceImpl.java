package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Speciality;
import com.mo1ty.medcenterapp.repository.SpecialityRepository;
import com.mo1ty.medcenterapp.service.interfaces.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository){
        this.specialityRepository = specialityRepository;
    }

    @Override
    public List<Speciality> getAllSpecialities() {
        return null;
    }

}
