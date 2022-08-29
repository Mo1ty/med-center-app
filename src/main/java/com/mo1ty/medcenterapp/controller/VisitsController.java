package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.service.interfaces.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitsController {

    @Autowired
    VisitsService visitsService;

    @GetMapping("")
    public List<Visit> getAllVisits(){
        return visitsService.findAll();
    }

    @GetMapping("/{visitId}")
    public Visit getVisit(@PathVariable int visitId){
        Visit visit = visitsService.findById(visitId);

        if(visit == null){
            throw new DataNotFoundException("Visit with id " + visitId + " was not found!");
        }

        return visit;
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
