package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.TreatmentType;
import com.mo1ty.medcenterapp.service.interfaces.TreatmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatments/types")
public class TreatmentTypeController {

    @Autowired
    TreatmentTypeService treatmentTypeService;

    @GetMapping("")
    public List<TreatmentType> getAllTreatmentTypes(){
        return treatmentTypeService.findAll();
    }

    @GetMapping("/{treatmentTypeId}")
    public TreatmentType getTreatmentType(@PathVariable int treatmentTypeId){
        TreatmentType treatmentType = treatmentTypeService.findById(treatmentTypeId);

        if(treatmentType == null){
            throw new DataNotFoundException("Address with id " + treatmentTypeId + " was not found!");
        }

        return treatmentType;
    }

    @PostMapping("")
    public TreatmentType addTreatmentType(@RequestBody TreatmentType treatmentType){
        return treatmentTypeService.createTreatmentType(treatmentType);
    }

    @PutMapping("")
    public TreatmentType updateTreatmentType(@RequestBody TreatmentType treatmentType){
        TreatmentType type = treatmentTypeService.findById(treatmentType.getTreatmentTypeId());

        if(type == null){
            throw new DataNotFoundException("Requested treatmentType was not found!");
        }

        return treatmentTypeService.updateTreatmentType(treatmentType);
    }

    @DeleteMapping("/{treatmentTypeId}")
    public TreatmentType deleteTreatmentType(@PathVariable int treatmentTypeId){

        // Will not execute if any client/doctor has an address

        TreatmentType type = treatmentTypeService.findById(treatmentTypeId);

        if(type == null){
            throw new DataNotFoundException("Requested address was not found!");
        }

        treatmentTypeService.deleteTreatmentType(treatmentTypeId);

        return type;
    }


}
