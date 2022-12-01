package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.service.TreatmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    TreatmentServiceImpl treatmentService;

    @Autowired
    public TreatmentController(TreatmentServiceImpl treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping("/")
    List<Treatment> getAll() {
        return treatmentService.getAllTreatments();
    }

}
