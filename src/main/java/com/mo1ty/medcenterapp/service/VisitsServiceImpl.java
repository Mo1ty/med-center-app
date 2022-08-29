package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
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
    public Visit createVisit(Visit visit) {
        return visitsRepository.save(visit);
    }

    @Override
    public Visit updateVisit(Visit visit) {
        Optional<Visit> result = visitsRepository.findById(visit.getVisitId());

        if(result.isPresent()){
            visitsRepository.save(visit);
            return visit;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Visit> findAll() {

        List<Visit> result = visitsRepository.findAll();

        if (result.size() == 0){
            throw new DataNotFoundException("No visits are present!");
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
            throw new DataNotFoundException("Doctor with this id was not found!");
        }
    }

    @Override
    public List<Visit> findAllByClientId(int clientId) {

        List<Visit> result = visitsRepository.findAllByClientVisited(clientId);

        if (result.size() == 0){
            throw new DataNotFoundException("This client has not visited med-center yet!");
        }

        return result;
    }

    @Override
    public List<Visit> findAllByDoctorId(int doctorId) {

        List<Visit> result = visitsRepository.findAllByDoctorAccepted(doctorId);

        if (result.size() == 0){
            throw new DataNotFoundException("This doctor has not accepted any visits yet!");
        }

        return result;
    }

    // Add deleteByEntity method later if required
    @Override
    public void deleteVisit(int visitId) {
        visitsRepository.deleteById(visitId);
    }
}
