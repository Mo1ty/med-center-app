package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.repository.interfaces.TreatmentRepository;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    @Autowired
    TreatmentRepository treatmentRepository;

    @Override
    public Treatment findByName(String name) {

        Treatment result = treatmentRepository.findByTreatmentName(name);

        if(result == null)
            throw new DataNotFoundException("Treatment with this name was not found!");

        return result;
    }

    @Override
    public void createOrUpdateTreatment(Treatment treatment) {
        treatmentRepository.save(treatment);
    }

    @Override
    public List<Treatment> findAll() {

        List<Treatment> result = treatmentRepository.findAll();

        if (result.size() == 0){
            throw new DataNotFoundException("No treatments are in the table!");
        }

        return result;

    }

    @Override
    public Treatment findById(int treatmentId) {
        Optional<Treatment> result = treatmentRepository.findById(treatmentId);

        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new DataNotFoundException("Treatment with this id was not found!");
        }
    }

    @Override
    public void deleteTreatment(int treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }
}
