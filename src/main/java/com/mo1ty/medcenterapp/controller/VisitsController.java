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
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public List<VisitVO> getAllVisits(){
        List<Visit> visits = visitsService.findAll();
        List<VisitVO> visitVOList = new ArrayList<>();

        for(Visit visit : visits){
            visitVOList.add(modelMapper.map(visit, VisitVO.class));
        }

        return visitVOList;
    }

    @GetMapping("/{visitId}")
    public VisitVO getVisit(@PathVariable int visitId){
        Visit visit = visitsService.findById(visitId);

        if(visit == null){
            throw new DataNotFoundException("Visit with id " + visitId + " was not found!");
        }

        return modelMapper.map(visit, VisitVO.class);
    }

    @PostMapping("")
    public Visit addVisit(@RequestBody Visit visit){
        return visitsService.createVisit(visit);
    }

    @PutMapping("")
    public Visit updateVisit(@RequestBody Visit visit){
        Visit vis = visitsService.findById(visit.getVisitId());

        if(vis == null){
            throw new DataNotPresentException("Requested visit does not exist! Consider adding a new entity instead.");
        }

        return visitsService.updateVisit(visit);
    }

    @DeleteMapping("/{visitId}")
    public Visit deleteVisit(@PathVariable int visitId){

        Visit visit = visitsService.findById(visitId);

        if(visit == null){
            throw new DataNotPresentException("Requested visit does not exist!");
        }

        visitsService.deleteVisit(visitId);

        return visit;
    }
}
