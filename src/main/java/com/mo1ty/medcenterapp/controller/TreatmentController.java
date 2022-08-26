package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.entity.TreatmentType;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    // Fix NullPointerException ASAP
    TreatmentService treatmentService;

    @GetMapping("")
    public List<Treatment> getAllTreatments(){
        List<Treatment> treatments = treatmentService.findAll();

        return treatments;
    }

    @GetMapping("/{treatmentId}")
    public Treatment getTreatment(@PathVariable int treatmentId){
        Treatment treatment = treatmentService.findById(treatmentId);

        if(treatment == null){
            throw new DataNotFoundException("Address with id " + treatmentId + " was not found!");
        }

        return treatment;
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

        // Will not execute if any client/doctor has an address

        Treatment type = treatmentService.findById(treatmentId);

        if(type == null){
            throw new DataNotFoundException("Requested tratment was not found!");
        }

        treatmentService.deleteTreatment(treatmentId);

        return type;
    }

}
