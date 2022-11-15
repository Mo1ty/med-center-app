package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.entity.Speciality;
import com.mo1ty.medcenterapp.entity.view.external.DoctorExternal;
import com.mo1ty.medcenterapp.repository.ContactRepository;
import com.mo1ty.medcenterapp.repository.DoctorExternalRepository;
import com.mo1ty.medcenterapp.repository.SpecialityRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorExtServiceImpl implements DoctorExtService {

    DoctorExternalRepository doctorExternalRepository;
    ContactRepository contactRepository;
    SpecialityRepository specialityRepository;

    @Autowired
    public DoctorExtServiceImpl(DoctorExternalRepository doctorExternalRepository, ContactRepository contactRepository,
                                SpecialityRepository specialityRepository) {
        this.doctorExternalRepository = doctorExternalRepository;
        this.contactRepository = contactRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public DoctorExternal findById(int id) {
        List<DoctorExternal> docExtList = doctorExternalRepository.findAllById(Collections.singletonList(id));
        if(docExtList.size() == 1) {
            return docExtList.get(0);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    @Override
    public List<DoctorExternal> findAll() {
        return doctorExternalRepository.findAll();
    }

    @Override
    public List<DoctorExternal> findAllBySpec(String specName) {
        return doctorExternalRepository.findAllBySpec(specName);
    }
}
