package com.mo1ty.medcenterapp.service.controller;

import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorVO> getAllDoctors(){
        return doctorService.findAll();
    }

    @GetMapping("/{doctorId}")
    public DoctorVO getDoctor(@PathVariable int doctorId){
        return doctorService.findById(doctorId);
    }

    @PostMapping
    public DoctorVO addDoctor(@RequestBody DoctorVO doctor){
        return doctorService.createDoctor(doctor);
    }

    @PutMapping
    public DoctorVO updateDoctor(@RequestBody DoctorVO doctor){
        return doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/{doctorId}")
    public DoctorVO deleteDoctor(@PathVariable int doctorId){
        DoctorVO doc = doctorService.findById(doctorId);
        doctorService.deleteDoctor(doctorId);
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
