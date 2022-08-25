package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.TreatmentType;
import com.mo1ty.medcenterapp.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.repository.interfaces.TreatmentTypeRepository;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentTypeServiceImpl implements TreatmentTypeService {

    @Autowired
    TreatmentTypeRepository treatmentTypeRepository;

    @Override
    public void createOrUpdateTreatmentType(TreatmentType treatmentType) {
        treatmentTypeRepository.save(treatmentType);
    }

    @Override
    public List<TreatmentType> findAll() {

        List<TreatmentType> result = treatmentTypeRepository.findAll();

        if (result.size() == 0){
            throw new DataNotFoundException("No treatment types are present!");
        }

        return result;
    }

    @Override
    public TreatmentType findById(int treatmentTypeId) {
        Optional<TreatmentType> result = treatmentTypeRepository.findById(treatmentTypeId);

        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new DataNotFoundException("Treatment type with this id was not found!");
        }
    }

    @Override
    public void deleteClient(int treatmentTypeId) {
        treatmentTypeRepository.deleteById(treatmentTypeId);
    }
}
