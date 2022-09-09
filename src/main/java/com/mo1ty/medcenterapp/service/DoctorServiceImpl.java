package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.service.controller.error.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.service.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.repository.DoctorRepository;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;
    private TreatmentRepository treatmentRepository;
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, TreatmentRepository treatmentRepository,
                             AddressRepository addressRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.treatmentRepository = treatmentRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorVO createDoctor(DoctorVO doctorVO) {
        Optional<Address> addr = addressRepository.findById(doctorVO.getAddressId());

        if(addr.isPresent()){
            Doctor doc = new Doctor();
            doc.setAddress(addr.get());
            modelMapper.map(doctorVO, doc);
            doctorRepository.save(doc);
            return modelMapper.map(doctorRepository.findById(doc.getId()), DoctorVO.class);
        }
        else{
            throw new InvalidInputException("Address is not valid." +
                    "Check the data and try again");
        }
    }

    @Override
    public DoctorVO updateDoctor(DoctorVO doctorVO) {
        Optional<Doctor> result = doctorRepository.findById(doctorVO.getId());
        Optional<Address> addr = addressRepository.findById(doctorVO.getAddressId());

        if(result.isPresent() && addr.isPresent()){
            Doctor doc = result.get();
            Address address = addressRepository.getReferenceById(doctorVO.getAddressId());
            doc.setAddress(address);
            modelMapper.map(doctorVO, doc);
            doctorRepository.save(doc);
            return modelMapper.map(doctorRepository.findById(doc.getId()), DoctorVO.class);
        }
        else{
            throw new InvalidInputException("Either address or doctor entity are not valid." +
                    "Check the data and try again");
        }
    }

    @Override
    public List<DoctorVO> findAll() {

        List<Doctor> doctors = doctorRepository.findAll();

        if (doctors.size() == 0){
            throw new DataNotFoundException("No doctors were found in the table!");
        }

        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorVO.class))
                .collect(Collectors.toList());
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
    public void deleteDoctor(int doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<TreatmentVO> addTreatment(int doctorId, int treatmentId){
        // Get the entities using ID from the DB.
        Optional<Doctor> docResult = doctorRepository.findById(doctorId);
        Optional<Treatment> treatmentResult = treatmentRepository.findById(treatmentId);

        if(docResult.isPresent() && treatmentResult.isPresent()){
            // If both are found, get and edit the list
            Doctor doctor = docResult.get();
            Treatment treatment = treatmentResult.get();
            List<Treatment> treatments = doctor.getAllTreatments();
            treatments.add(treatment);
            doctor.setAllTreatments(treatments);

            // Save changes and return an edited version as VOs
            doctorRepository.save(doctor);
            return doctor.getAllTreatments()
                    .stream()
                    .map(treatment_entity -> modelMapper.map(treatment_entity, TreatmentVO.class))
                    .collect(Collectors.toList());
        }
        else{
            throw new InvalidInputException("Either doctor or treatment are invalid. Check your inputs!");
        }
    }

    @Override
    public List<TreatmentVO> removeTreatment(int doctorId, int treatmentId){
        // Get the entities using ID from the DB.
        Optional<Doctor> docResult = doctorRepository.findById(doctorId);
        Optional<Treatment> treatmentResult = treatmentRepository.findById(treatmentId);

        if(docResult.isPresent() && treatmentResult.isPresent()){
            // If both are found, get and edit the list
            Doctor doctor = docResult.get();
            Treatment treatment = treatmentResult.get();
            List<Treatment> treatments = doctor.getAllTreatments();
            treatments.remove(treatment);
            doctor.setAllTreatments(treatments);

            // Save changes and return an edited version as VOs
            doctorRepository.save(doctor);
            return doctor.getAllTreatments()
                    .stream()
                    .map(treatment_entity -> modelMapper.map(treatment_entity, TreatmentVO.class))
                    .collect(Collectors.toList());
        }
        else{
            throw new InvalidInputException("Either doctor or treatment are invalid. Check your inputs!");
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
