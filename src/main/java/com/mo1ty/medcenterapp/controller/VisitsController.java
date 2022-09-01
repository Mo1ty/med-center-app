package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.VisitVO;
import com.mo1ty.medcenterapp.service.interfaces.VisitsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitsController {

    @Autowired
    VisitsService visitsService;

    @GetMapping("")
    public List<VisitVO> getAllVisits(){
        return visitsService.findAll();
    }

    @GetMapping("/{visitId}")
    public VisitVO getVisit(@PathVariable int visitId){
        return visitsService.findById(visitId);
    }

    @PostMapping("")
    public VisitVO addVisit(@RequestBody VisitVO visitVO){
        visitsService.createVisit(visitVO);
        return visitVO;
    }

    @PutMapping("")
    public VisitVO updateVisit(@RequestBody VisitVO visitVO){
        visitsService.updateVisit(visitVO);
        return visitVO;
    }

    @DeleteMapping("/{visitId}")
    public void deleteVisit(@PathVariable int visitId){
        visitsService.deleteVisit(visitId);
    }
}
