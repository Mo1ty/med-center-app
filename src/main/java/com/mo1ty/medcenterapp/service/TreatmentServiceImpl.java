package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.repository.DoctorRepository;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<TreatmentVO> findByName(String name) {
        List<TreatmentVO> treatmentVOList = new ArrayList<>();
        for (Treatment treatment : treatmentRepository.findByTreatmentName(name)){
            treatmentVOList.add(modelMapper.map(treatment, TreatmentVO.class));
        }
        if(treatmentVOList.size() == 0)
            throw new DataNotFoundException("Nothing was found.");
        return treatmentVOList;
    }

    @Override
    public void createTreatment(TreatmentVO treatmentVO) {
        if(areDoctorsPresent(treatmentVO.getDoctorsIds())){
            Treatment treatment = modelMapper.map(treatmentVO, Treatment.class);
            treatmentRepository.save(treatment);
        }
        else{
            throw new InvalidInputException("Some of the doctors are not in the database. Check your data.");
        }
    }

    @Override
    public void updateTreatment(TreatmentVO treatmentVO) {
        Optional<Treatment> result = treatmentRepository.findById(treatmentVO.getTreatmentId());

        if(result.isPresent() && areDoctorsPresent(treatmentVO.getDoctorsIds())){
            Treatment treatment = modelMapper.map(treatmentVO, Treatment.class);
            treatmentRepository.save(treatment);
        }
        else{
            throw new InvalidInputException("Some of the doctors are not in the database. Check your data.");
        }
    }

    @Override
    public List<TreatmentVO> findAll() {
        List<TreatmentVO> treatmentsVO = new ArrayList<>();
        for(Treatment treatment : treatmentRepository.findAll()){
            treatmentsVO.add(modelMapper.map(treatment, TreatmentVO.class));
        }
        return treatmentsVO;
    }

    @Override
    public TreatmentVO findById(int treatmentId) {
        Optional<Treatment> result = treatmentRepository.findById(treatmentId);
        if(result.isPresent()){
            return modelMapper.map(result.get(), TreatmentVO.class);
        }
        else{
            throw new DataNotFoundException("Treatment was not found in database.");
        }
    }

    @Override
    public void deleteTreatment(int treatmentId) {
        treatmentRepository.deleteById(treatmentId);
    }

    public boolean areDoctorsPresent(List<Integer> doctors){
        List<Doctor> result = doctorRepository.findAllById(doctors);
        return result.size() == doctors.size();
    }
}
