package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.entity.publ.DoctorPublic;
import com.mo1ty.medcenterapp.service.interfaces.DoctorExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorExternalController {

    DoctorExternalService doctorExternalService;

    @Autowired
    public DoctorExternalController(DoctorExternalService doctorExternalService) {
        this.doctorExternalService = doctorExternalService;
    }

    @GetMapping("/{id}")
    DoctorPublic findById(@PathVariable int id) {
        return doctorExternalService.findById(id);
    }

    @GetMapping
    List<DoctorPublic> findAll() {
        return doctorExternalService.findAll();
    }

    @GetMapping("/speciality/{speciality}")
    List<DoctorPublic> findAllBySpec(@PathVariable int speciality) {
        return doctorExternalService.findAllBySpec(speciality);
    }

}
