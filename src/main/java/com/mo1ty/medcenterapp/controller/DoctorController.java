package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
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
    ModelMapper modelMapper;

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
        return doctorService.findById(doctorId);
    }

    @PostMapping("")
    public DoctorVO addDoctor(@RequestBody DoctorVO doctor){
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("")
    public DoctorVO updateDoctor(@RequestBody DoctorVO doctor){
        DoctorVO type = doctorService.findById(doctor.getId());

        if(type == null){
            throw new DataNotPresentException("Requested doctor does not exist!");
        }

        return doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/{doctorId}")
    public DoctorVO deleteDoctor(@PathVariable int doctorId){

        // Will not execute if any visit has this doctor, fix later

        DoctorVO doc = doctorService.findById(doctorId);

        if(doc == null){
            throw new DataNotPresentException("Requested doctor does not exist!");
        }

        doctorService.deleteClient(doctorId);

        return doc;
    }

    @GetMapping("/{doctorId}/treatments")
    public List<TreatmentVO> getTreatments(@PathVariable int doctorId){
        return doctorService.getTreatments(doctorId);
    }

    @PutMapping("/{doctorId}/{treatmentId}")
    public List<TreatmentVO> addTreatment(@PathVariable("doctorId") int doctorId, @PathVariable("treatmentId") int treatmentId){
        return doctorService.addTreatment(doctorId, treatmentId);
    }

    @DeleteMapping("/{doctorId}/{treatmentId}")
    public List<TreatmentVO> removeTreatment(@PathVariable("doctorId") int doctorId, @PathVariable("treatmentId") int treatmentId){
        return doctorService.removeTreatment(doctorId, treatmentId);
    }
}
