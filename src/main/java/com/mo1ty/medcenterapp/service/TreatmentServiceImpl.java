package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }
}
