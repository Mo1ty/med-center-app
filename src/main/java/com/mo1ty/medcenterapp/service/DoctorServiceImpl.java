package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.repository.DoctorRepository;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.repository.VisitsRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    VisitsRepository visitsRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public DoctorVO createDoctor(DoctorVO doctorVO) {
        Optional<Address> addr = addressRepository.findById(doctorVO.getAddressId());

        if(addr.isPresent()){
            Doctor doc = new Doctor();
            modelMapper.map(doctorVO, doc);
            doc.setAddress(addr.get());
            doctorRepository.save(doc);
            return modelMapper.map(doctorRepository.findById(doc.getId()), DoctorVO.class);
        }
        else{
            throw new InvalidInputException("Either address, or treatments, or visits were not in the database." +
                    "Check the data and try again");
        }
    }

    @Override
    public DoctorVO updateDoctor(DoctorVO doctorVO) {
        Optional<Doctor> result = doctorRepository.findById(doctorVO.getId());

        if(result.isPresent()){
            Doctor doc = result.get();
            modelMapper.map(doctorVO, doc);
            doctorRepository.save(doc);
            return modelMapper.map(doctorRepository.findById(doc.getId()), DoctorVO.class);
        }
        else{
            throw new InvalidInputException("Either address, or treatments, or visits were not in the database." +
                    "Check the data and try again");
        }
    }

    @Override
    public List<Doctor> findAll() {

        List<Doctor> result = doctorRepository.findAll();

        if (result.size() == 0){
            throw new DataNotFoundException("No doctors were found in the table!");
        }

        return result;
    }

    @Override
    public DoctorVO findById(int doctorId) {

        Optional<Doctor> result = doctorRepository.findById(doctorId);

        if(result.isPresent()){
            return modelMapper.map(result.get(), DoctorVO.class);
        }
        else{
            throw new DataNotFoundException("Doctor with this id was not found!");
        }

    }

    @Override
    public void deleteClient(int doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<TreatmentVO> addTreatment(int doctorId, int treatmentId){
        Optional<Doctor> docResult = doctorRepository.findById(doctorId);
        Optional<Treatment> treatmentResult = treatmentRepository.findById(treatmentId);

        if(docResult.isPresent() && treatmentResult.isPresent()){
            Doctor doctor = docResult.get();
            Treatment treatment = treatmentResult.get();
            List<Treatment> treatments = doctor.getAllTreatments();
            treatments.add(treatment);
            doctor.setAllTreatments(treatments);
            doctorRepository.save(doctor);
            return doctor.getAllTreatments()
                    .stream()
                    .map(treatment_entity -> modelMapper.map(treatment_entity, TreatmentVO.class))
                    .collect(Collectors.toList());
        }
        else{
            throw new InvalidInputException("Invalid input into database. Check your inputs!");
        }
    }

    @Override
    public List<TreatmentVO> removeTreatment(int doctorId, int treatmentId){
        Optional<Doctor> docResult = doctorRepository.findById(doctorId);
        Optional<Treatment> treatmentResult = treatmentRepository.findById(treatmentId);

        if(docResult.isPresent() && treatmentResult.isPresent()){
            Doctor doctor = docResult.get();
            Treatment treatment = treatmentResult.get();
            List<Treatment> treatments = doctor.getAllTreatments();
            treatments.remove(treatment);
            doctor.setAllTreatments(treatments);
            doctorRepository.save(doctor);
            return doctor.getAllTreatments()
                    .stream()
                    .map(treatment_entity -> modelMapper.map(treatment_entity, TreatmentVO.class))
                    .collect(Collectors.toList());
        }
        else{
            throw new InvalidInputException("Invalid input into database. Check your inputs!");
        }
    }

    @Override
    public List<TreatmentVO> getTreatments(int doctorId){
        Optional<Doctor> result = doctorRepository.findById(doctorId);
        if(result.isPresent()){
            return result.get().getAllTreatments()
                    .stream()
                    .map(treatment_element -> modelMapper.map(treatment_element, TreatmentVO.class))
                    .collect(Collectors.toList());
        }
        else{
            throw new DataNotFoundException("Doctor with this id was not found!");
        }
    }
}
