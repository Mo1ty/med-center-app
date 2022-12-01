package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.config.mapper.VisitVO;
import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.error.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.entity.internal.Doctor;
import com.mo1ty.medcenterapp.entity.publ.Visit;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.repository.VisitsRepository;
import com.mo1ty.medcenterapp.repository.priv.DoctorRepository;
import com.mo1ty.medcenterapp.service.interfaces.VisitsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitsServiceImpl implements VisitsService {

    VisitsRepository visitsRepository;
    ModelMapper modelMapper;
    TreatmentRepository treatmentRepository;
    ClientRepository clientRepository;
    DoctorRepository doctorRepository;

    @Autowired
    public VisitsServiceImpl(VisitsRepository visitsRepository, ModelMapper modelMapper,
                             TreatmentRepository treatmentRepository, ClientRepository clientRepository, DoctorRepository doctorRepository) {
        this.visitsRepository = visitsRepository;
        this.modelMapper = modelMapper;
        this.treatmentRepository = treatmentRepository;
        this.clientRepository = clientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void createVisit(VisitVO visitVO) {
        if(isDataExistent(visitVO)){
            // Create the new entities
            Visit visit = new Visit();
            modelMapper.map(visitVO, visit);
            // Set all fields of an entity using data from VO
            visit.setClient(clientRepository.getReferenceById(visitVO.getClientVisitedId()));
            visit.setDoctor(doctorRepository.getReferenceById(visitVO.getDoctorAcceptedId()));
            visit.setTreatment(treatmentRepository.getReferenceById(visitVO.getTreatmentDoneId()));
            // Save new entity
            visitsRepository.save(visit);
        }
        else{
            throw new InvalidInputException("At least one of the entities is invalid. Check your input and try again!");
        }
    }

    @Override
    public void updateVisit(VisitVO visitVO) {
        Optional<Visit> result = visitsRepository.findById(visitVO.getVisitId());

        if(result.isPresent() && isDataExistent(visitVO)){

            // Get the new result itself and set the values
            Visit visit = result.get();
            visit.setTreatment(treatmentRepository.getReferenceById(visitVO.getTreatmentDoneId()));
            visit.setClient(clientRepository.getReferenceById(visitVO.getClientVisitedId()));
            visit.setDoctor(doctorRepository.getReferenceById(visitVO.getDoctorAcceptedId()));
            // Save the entity
            visitsRepository.save(visit);
        }
        else{
            throw new InvalidInputException("At least one of the entities is invalid. Check your input and try again!");
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
            throw new DataNotFoundException("Visit with this id was not found!");
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
        return visitsRepository.findAllByDate(Timestamp.from(Instant.now()))
                .stream()
                .map(visit -> modelMapper.map(visit, VisitVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitVO> findAllPendingVisitsByClientId(int clientId) {
        // Get all the visits that client may cancel, i.e. tomorrow or later
        return visitsRepository.findAllByDateAndClientId
                        (Timestamp.from(Instant.now()), clientId)
                .stream()
                .map(visit -> modelMapper.map(visit, VisitVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitVO> findAllPreviousVisitsByClientId(int clientId) {
        // Get all the visits that were in the past
        return visitsRepository.findAllBeforeDateAndClientId
                        (Timestamp.from(Instant.now()), clientId)
                .stream()
                .map(visit -> modelMapper.map(visit, VisitVO.class))
                .collect(Collectors.toList());
    }

    public List<Long> findAllOccupiedTimes(int doctorId) {
        // Get all visits that will be done by this doctor
        List<Visit> visitsByDoctor = visitsRepository.findAllByDateAndDoctorId(Timestamp.from(Instant.now()), doctorId);

        // Get datetime from them to get the collection of occupied times
        return visitsByDoctor.stream()
                .map(Visit -> Visit.getDatetime().getTime())
                .collect(Collectors.toList());
    }

    public boolean isDataExistent(VisitVO visitVO){
        boolean treatmentExists = treatmentRepository.existsById(visitVO.getTreatmentDoneId());
        boolean clientExists = clientRepository.existsById(visitVO.getClientVisitedId());
        boolean doctorExists = doctorRepository.existsById(visitVO.getDoctorAcceptedId());

        return treatmentExists && clientExists && doctorExists;
    }

}
