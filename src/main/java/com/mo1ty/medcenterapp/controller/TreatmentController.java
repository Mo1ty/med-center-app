package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    @Autowired
    TreatmentService treatmentService;

    @GetMapping("")
    public List<TreatmentVO> getAllTreatments(){
        return treatmentService.findAll();
    }

    @GetMapping("/{treatmentId}")
    public TreatmentVO getTreatment(@PathVariable int treatmentId){
        return treatmentService.findById(treatmentId);
    }

    @GetMapping("/name={treatmentName}")
    public List<TreatmentVO> getTreatmentByName(@PathVariable String treatmentName){
        return treatmentService.findByName(treatmentName);
    }

    @PostMapping("")
    public TreatmentVO addTreatment(@RequestBody TreatmentVO treatment){
        treatmentService.createTreatment(treatment);
        return treatment;
    }

    @PutMapping("")
    public TreatmentVO updateTreatment(@RequestBody TreatmentVO treatment){
        treatmentService.updateTreatment(treatment);
        return treatment;
    }

    @DeleteMapping("/{treatmentId}")
    public void deleteTreatment(@PathVariable int treatmentId){
        // Will not execute if any visit has this treatment, fix later
        treatmentService.deleteTreatment(treatmentId);
    }

}
