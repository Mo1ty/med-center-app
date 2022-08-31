package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.InvalidValuesInputException;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.repository.interfaces.AddressRepository;
import com.mo1ty.medcenterapp.repository.interfaces.DoctorRepository;
import com.mo1ty.medcenterapp.repository.interfaces.TreatmentRepository;
import com.mo1ty.medcenterapp.repository.interfaces.VisitsRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
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
    public List<Doctor> findByTreatmentTypeAndQualificationLevel(Treatment treatment, int qualificationLevel) {
        /*
        List<Doctor> result =
                doctorRepository.findByTreatmentTypeAndQualificationLevel(treatmentType, qualificationLevel);

        if (result.size() == 0){
            // add DataNotFoundException later
            throw new DataNotFoundException
                    ("There are no doctors with Treatment Type: " + treatmentType.getTreatmentType()
                    + "and qualification level: " + qualificationLevel);
        }

         */
        return null;

    }

    @Override
    public DoctorVO createDoctor(DoctorVO doctorVO) {
        List<Treatment> treatmentsPresent = areTreatmentsPresent(doctorVO.getAllTreatmentsIds());


        if(treatmentsPresent!=null){
            Doctor doc = modelMapper.map(doctorVO, Doctor.class);//mapDoc(doctorVO, treatmentsPresent, null);
            doctorRepository.save(doc);
            return doctorVO;
        }
        else{
            throw new InvalidValuesInputException("Either address, or treatments, or visits were not in the database." +
                    "Check the data and try again");
        }
    }

    @Override
    public DoctorVO updateDoctor(DoctorVO doctorVO) {
        Optional<Doctor> result = doctorRepository.findById(doctorVO.getId());
        List<Treatment> treatmentsPresent = areTreatmentsPresent(doctorVO.getAllTreatmentsIds());
        List<Visit> visitsPresent = areVisitsPresent(doctorVO.getAllVisitsIds());


        if(result.isPresent() && treatmentsPresent!=null && visitsPresent!=null){
            Doctor doc = modelMapper.map(doctorVO, Doctor.class);//mapDoc(doctorVO, treatmentsPresent, visitsPresent);
            doctorRepository.save(doc);
            return doctorVO;
        }
        else{
            throw new InvalidValuesInputException("Either address, or treatments, or visits were not in the database." +
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


    /*
    public Doctor mapDoc(DoctorVO doctorVO, List<Treatment> treatments, List<Visit> visits){
        Doctor doc = modelMapper.map(doctorVO, Doctor.class);
        //doc.setAllTreatments(treatments);
        doc.setAllVisits(visits);
        return doc;
    }*/
}
