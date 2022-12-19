package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.config.mapper.VisitVO;
import com.mo1ty.medcenterapp.service.interfaces.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitsController {

    private VisitsService visitsService;

    @Autowired
    public VisitsController(VisitsService visitsService){
        this.visitsService = visitsService;
    }

    @GetMapping
    public List<VisitVO> getAllVisits(){
        return visitsService.findAll();
    }

    @GetMapping("/{visitId}")
    public VisitVO getVisit(@PathVariable int visitId){
        return visitsService.findById(visitId);
    }

    @PostMapping
    public VisitVO addVisit(@RequestBody VisitVO visitVO){
        visitsService.createVisit(visitVO);
        return visitVO;
    }

    @GetMapping("/pending/{clientId}")
    public List<VisitVO> getClientsPendingVisits(@PathVariable int clientId){
        return visitsService.findAllPendingVisitsByClientId(clientId);
    }

    @GetMapping("/history/{clientId}")
    public List<VisitVO> getClientsVisitsHistory(@PathVariable int clientId){
        return visitsService.findAllPreviousVisitsByClientId(clientId);
    }

    @GetMapping("/timetable/{doctorId}")
    public List<Long> getAllOccupiedVisits(@PathVariable int doctorId){
        return visitsService.findAllOccupiedTimes(doctorId);
    }

    @PutMapping
    public VisitVO updateVisit(@RequestBody VisitVO visitVO){
        visitsService.updateVisit(visitVO);
        return visitVO;
    }

    @DeleteMapping("/{visitId}")
    public void deleteVisit(@PathVariable int visitId){
        visitsService.deleteVisit(visitId);
    }

}
