package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.mapper.VisitVO;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.DoctorRepository;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.repository.VisitsRepository;
import com.mo1ty.medcenterapp.service.interfaces.VisitsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitsServiceImpl implements VisitsService {

    @Autowired
    VisitsRepository visitsRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public void createVisit(VisitVO visitVO) {
        if(isDataExistent(visitVO)){
            visitsRepository.save(modelMapper.map(visitVO, Visit.class));
        }
        else{
            throw new InvalidInputException("Invalid data input! Check your data and try again!");
        }
    }

    @Override
    public void updateVisit(VisitVO visitVO) {
        Optional<Visit> result = visitsRepository.findById(visitVO.getVisitId());

        if(result.isPresent() && isDataExistent(visitVO)){
            visitsRepository.save(modelMapper.map(visitVO, Visit.class));
        }
        else{
            throw new InvalidInputException("Invalid data input! Check your data and try again!");
        }
    }

    @Override
    public List<VisitVO> findAll() {

        List<VisitVO> result = visitsRepository.findAll()
                .stream()
                .map(visit -> modelMapper.map(visit, VisitVO.class))
                .collect(Collectors.toList());

        if (result.size() == 0){
            throw new DataNotFoundException("No visits are present!");
        }

        return result;
    }

    @Override
    public VisitVO findById(int visitId) {
        Optional<Visit> result = visitsRepository.findById(visitId);
        if(result.isPresent()){
            return modelMapper.map(result.get(), VisitVO.class);
        }
        else{
            throw new DataNotFoundException("Doctor with this id was not found!");
        }
    }

    @Override
    public List<VisitVO> findAllByClientId(int clientId) {

        return visitsRepository.findAllByClientVisited(clientId)
                .stream()
                .map(visit -> modelMapper.map(visit, VisitVO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<VisitVO> findAllByDoctorId(int doctorId) {

        return visitsRepository.findAllByDoctorAccepted(doctorId)
                .stream()
                .map(visit -> modelMapper.map(visit, VisitVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVisit(int visitId) {
        visitsRepository.deleteById(visitId);
    }

    @Override
    public List<VisitVO> findAllPendingVisits() {
        // Get all visits that will be in a future
        return visitsRepository.findAllByDate(Date.from(Instant.now()))
                .stream()
                .map(visit -> modelMapper.map(visit, VisitVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitVO> findAllPendingVisitsByClientId(int clientId) {
        // Get all the visits that client may cancel, i.e. tomorrow or later
        return visitsRepository.findAllByDateAndClientId
                        (Date.from(Instant.now()), clientId)
                        .stream()
                        .map(visit -> modelMapper.map(visit, VisitVO.class))
                        .collect(Collectors.toList());
    }

    public List<Date> findAllOccupiedTimes(int doctorId) {
        // Get all visits that will be done by this doctor
        List<Visit> visitsByDoctor = visitsRepository.findAllByDateAndDoctorId(Date.from(Instant.now()), doctorId);

        // Get datetime from them to get the collection of occupied times
        return visitsByDoctor.stream()
                .map(Visit::getDatetime)
                .collect(Collectors.toList());
    }

    public boolean isDataExistent(VisitVO visitVO){
        boolean treatmentExists = treatmentRepository.existsById(visitVO.getTreatmentDoneId());
        boolean clientExists = clientRepository.existsById(visitVO.getClientVisitedId());
        boolean doctorExists = doctorRepository.existsById(visitVO.getDoctorAcceptedId());

        return treatmentExists && clientExists && doctorExists;
    }
}
