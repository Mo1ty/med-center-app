package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Speciality;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.entity.publ.DoctorPublic;
import com.mo1ty.medcenterapp.repository.ContactRepository;
import com.mo1ty.medcenterapp.repository.DoctorPublicRepository;
import com.mo1ty.medcenterapp.repository.SpecialityRepository;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorExternalServiceImpl implements DoctorExternalService {

    DoctorPublicRepository doctorPublicRepository;
    ContactRepository contactRepository;
    SpecialityRepository specialityRepository;
    TreatmentRepository treatmentRepository;

    @Autowired
    public DoctorExternalServiceImpl(DoctorPublicRepository doctorPublicRepository, ContactRepository contactRepository,
                                     SpecialityRepository specialityRepository, TreatmentRepository treatmentRepository) {
        this.doctorPublicRepository = doctorPublicRepository;
        this.contactRepository = contactRepository;
        this.specialityRepository = specialityRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public DoctorPublic findById(int id) {
        List<DoctorPublic> docExtList = doctorPublicRepository.findAllById(Collections.singletonList(id));
        if(docExtList.size() == 1) {
            return docExtList.get(0);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    @Override
    public List<DoctorPublic> findAll() {
        return doctorPublicRepository.findAll();
    }

    @Override
    public List<DoctorPublic> findAllBySpec(int treatmentId) {

        List<Treatment> treatments = treatmentRepository.findAllById(Collections.singletonList(treatmentId));

        List<Speciality> specialities = null;

        if(treatments.size() == 1) {
            specialities = specialityRepository.findAllById(Collections.singletonList(treatments.get(0).getSpeciality().getId()));
        }

        if(specialities.size() == 1) {
            Speciality speciality = specialities.get(0);

            return doctorPublicRepository.findAllBySpec(speciality.getName());
        }
        throw new DataNotFoundException("The data was not found in database!");
    }
}
