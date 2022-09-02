package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.repository.DoctorRepository;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.repository.VisitsRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
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
        List<Treatment> treatmentsPresent = areTreatmentsPresent(doctorVO.getAllTreatmentsIds());
        Optional<Address> addr = addressRepository.findById(doctorVO.getAddressId());

        if(treatmentsPresent!=null && addr.isPresent()){
            Doctor doc = modelMapper.map(doctorVO, Doctor.class);//mapDoc(doctorVO, treatmentsPresent, null);
            doctorRepository.save(doc);
            return modelMapper.map(doctorRepository.findById(doc.getId()).orElse(null), DoctorVO.class);
        }
        else{
            throw new InvalidInputException("Either address, or treatments, or visits were not in the database." +
                    "Check the data and try again");
        }
    }

    @Override
    public DoctorVO updateDoctor(DoctorVO doctorVO) {
        Optional<Doctor> result = doctorRepository.findById(doctorVO.getId());
        List<Treatment> treatmentsPresent = areTreatmentsPresent(doctorVO.getAllTreatmentsIds());
        List<Visit> visitsPresent = areVisitsPresent(doctorVO.getAllVisitsIds());
        Optional<Address> addr = addressRepository.findById(doctorVO.getAddressId());


        if(result.isPresent() && treatmentsPresent!=null
                && visitsPresent!=null && addr.isPresent()){
            Doctor doc = modelMapper.map(doctorVO, Doctor.class);//mapDoc(doctorVO, treatmentsPresent, visitsPresent);
            doctorRepository.save(doc);
            return modelMapper.map(doctorRepository.findById(doc.getId()).orElse(null), DoctorVO.class);
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
    public List<DoctorVO> findByTreatmentId(int treatmentId) {
        return doctorRepository.findByTreatmentId(treatmentId)
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorVO.class))
                .collect(Collectors.toList());
        // Consider adding exception check
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

    public List<Treatment> areTreatmentsPresent(List<Integer> treatments){
        List<Treatment> result = treatmentRepository.findAllById(treatments);
        if(result.size() == treatments.size() && treatments.size() != 0){
            return result;
        }
        return null;
    }

    public List<Visit> areVisitsPresent(List<Integer> visits){
        List<Visit> result = visitsRepository.findAllById(visits);
        if(result.size() == visits.size()){
            return result;
        }
        return null;
    }
}
