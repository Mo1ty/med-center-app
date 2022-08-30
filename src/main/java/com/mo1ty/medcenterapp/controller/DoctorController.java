package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.controller.exception.InvalidValuesInputException;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.TreatmentType;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    TreatmentTypeService treatmentTypeService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/treatment-type/{treatmentTypeId}/qualification-level/{qualificationLevel}")
    public List<Doctor> getDoctorByTreatmentTypeAndQualificationLevel(@PathVariable int treatmentTypeId, @PathVariable int qualificationLevel){
        TreatmentType treatmentType = treatmentTypeService.findById(treatmentTypeId);
        if(treatmentType == null){
            throw new InvalidValuesInputException("Treatment with this id was not found!");
        }

        return doctorService.findByTreatmentTypeAndQualificationLevel(treatmentType, qualificationLevel);
    }

    @GetMapping("")
    public List<DoctorVO> getAllDoctors(){

        List<Doctor> doctors = doctorService.findAll();
        List<DoctorVO> doctorVOList = new ArrayList<>();

        for(Doctor doctor : doctors){
            doctorVOList.add(modelMapper.map(doctor, DoctorVO.class));
        }

        return doctorVOList;
    }

    @GetMapping("/{doctorId}")
    public DoctorVO getDoctor(@PathVariable int doctorId){
        Doctor doc = doctorService.findById(doctorId);

        if(doc == null){
            throw new DataNotFoundException("Doctor with id " + doctorId + " was not found!");
        }

        return modelMapper.map(doc, DoctorVO.class);
    }

    @PostMapping("")
    public Doctor addDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("")
    public Doctor updateDoctor(@RequestBody Doctor doctor){
        Doctor type = doctorService.findById(doctor.getId());

        if(type == null){
            throw new DataNotPresentException("Requested doctor does not exist!");
        }

        return doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/{doctorId}")
    public Doctor deleteDoctor(@PathVariable int doctorId){

        // Will not execute if any visit has this doctor

        Doctor doc = doctorService.findById(doctorId);

        if(doc == null){
            throw new DataNotPresentException("Requested doctor does not exist!");
        }

        doctorService.deleteClient(doctorId);

        return doc;
    }

}
