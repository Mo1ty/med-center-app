package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.view.external.DoctorExternal;
import com.mo1ty.medcenterapp.repository.ContactRepository;
import com.mo1ty.medcenterapp.repository.DoctorRepository;
import com.mo1ty.medcenterapp.repository.SpecialityRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorExternalServiceImpl implements DoctorExternalService {

    DoctorRepository doctorRepository;
    ContactRepository contactRepository;
    SpecialityRepository specialityRepository;

    @Autowired
    public DoctorExternalServiceImpl(DoctorRepository doctorRepository, ContactRepository contactRepository,
                                     SpecialityRepository specialityRepository) {
        this.doctorRepository = doctorRepository;
        this.contactRepository = contactRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public DoctorExternal findById(int id) {
        List<DoctorExternal> docExtList = doctorRepository.findAllById(Collections.singletonList(id));
        if(docExtList.size() == 1) {
            return docExtList.get(0);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    @Override
    public List<DoctorExternal> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<DoctorExternal> findAllBySpec(String specName) {
        return doctorRepository.findAllBySpec(specName);
    }
}
