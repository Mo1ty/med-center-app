package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.entity.view.external.DoctorExternal;
import com.mo1ty.medcenterapp.repository.DoctorExternalRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorExternalController {

    DoctorExtService doctorExtService;

    @Autowired
    public DoctorExternalController(DoctorExtService doctorExtService) {
        this.doctorExtService = doctorExtService;
    }

    @GetMapping("/{id}")
    DoctorExternal findById(@PathVariable int id) {
        return doctorExtService.findById(id);
    }

    @GetMapping
    List<DoctorExternal> findAll() {
        return doctorExtService.findAll();
    }

    @GetMapping("/speciality")
    List<DoctorExternal> findAllBySpec(@PathVariable String speciality) {
        return doctorExtService.findAllBySpec(speciality);
    }

}
