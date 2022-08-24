package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.repository.interfaces.VisitsRepository;
import com.mo1ty.medcenterapp.service.interfaces.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitsServiceImpl implements VisitsService {

    @Autowired
    VisitsRepository visitsRepository;

    @Override
    public void createOrUpdateVisit(Visit visit) {
        visitsRepository.save(visit);
    }

    @Override
    public List<Visit> findAll() {

        List<Visit> result = visitsRepository.findAll();

        if (result.size() == 0){
            // add DataNotFoundException later
            throw new RuntimeException();
        }

        return result;
    }

    @Override
    public Visit findById(int visitId) {
        Optional<Visit> result = visitsRepository.findById(visitId);

        if(result.isPresent()){
            return result.get();
        }
        else{
            // add DataNotFoundException later
            throw new RuntimeException();
        }
    }

    @Override
    public List<Visit> findAllByClientId(int clientId) {

        List<Visit> result = visitsRepository.findAllByClientId(clientId);

        if (result.size() == 0){
            // add DataNotFoundException later
            throw new RuntimeException();
        }

        return result;
    }

    @Override
    public List<Visit> findAllByDoctorId(int doctorId) {

        List<Visit> result = visitsRepository.findAllByDoctorId(doctorId);

        if (result.size() == 0){
            // add DataNotFoundException later
            throw new RuntimeException();
        }

        return result;
    }

    // Add deleteByEntity method later if required
    @Override
    public void deleteVisit(int visitId) {
        visitsRepository.deleteById(visitId);
    }
}
