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

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public List<TreatmentVO> getAllTreatments(){
        List<Treatment> treatments = treatmentService.findAll();
        List<TreatmentVO> treatmentVOList = new ArrayList<>();

        for(Treatment treatment : treatments){
            treatmentVOList.add(modelMapper.map(treatment, TreatmentVO.class));
        }

        return treatmentVOList;
    }

    @GetMapping("/{treatmentId}")
    public TreatmentVO getTreatment(@PathVariable int treatmentId){
        Treatment treatment = treatmentService.findById(treatmentId);

        if(treatment == null){
            throw new DataNotFoundException("Treatment with id " + treatmentId + " was not found!");
        }

        return modelMapper.map(treatment, TreatmentVO.class);
    }

    @GetMapping("/name={treatmentName}")
    public List<Treatment> getTreatmentByName(@PathVariable String treatmentName){

        return treatmentService.findByName(treatmentName);
    }

    @PostMapping("")
    public Treatment addTreatment(@RequestBody Treatment treatment){
        return treatmentService.createTreatment(treatment);
    }

    @PutMapping("")
    public Treatment updateTreatment(@RequestBody Treatment treatment){
        Treatment type = treatmentService.findById(treatment.getTreatmentId());

        if(type == null){
            throw new DataNotPresentException("Requested treatment does not exist! Consider adding a new entity instead.");
        }

        return treatmentService.updateTreatment(treatment);
    }

    @DeleteMapping("/{treatmentId}")
    public Treatment deleteTreatment(@PathVariable int treatmentId){

        // Will not execute if any visit has this treatment

        Treatment type = treatmentService.findById(treatmentId);

        if(type == null){
            throw new DataNotPresentException("Requested treatment does not exist!");
        }

        treatmentService.deleteTreatment(treatmentId);

        return type;
    }

}
