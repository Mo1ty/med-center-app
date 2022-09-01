package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.mapper.VisitVO;
import com.mo1ty.medcenterapp.repository.interfaces.ClientRepository;
import com.mo1ty.medcenterapp.repository.interfaces.DoctorRepository;
import com.mo1ty.medcenterapp.repository.interfaces.TreatmentRepository;
import com.mo1ty.medcenterapp.repository.interfaces.VisitsRepository;
import com.mo1ty.medcenterapp.service.interfaces.VisitsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Add deleteByEntity method later if required
    @Override
    public void deleteVisit(int visitId) {
        visitsRepository.deleteById(visitId);
    }

    public boolean isDataExistent(VisitVO visitVO){
        boolean treatmentExists = treatmentRepository.existsById(visitVO.getTreatmentDoneId());
        boolean clientExists = clientRepository.existsById(visitVO.getClientVisitedId());
        boolean doctorExists = doctorRepository.existsById(visitVO.getDoctorAcceptedId());

        return treatmentExists && clientExists && doctorExists;
    }
}
