package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.service.interfaces.VisitsService;

import java.util.List;

public class VisitsServiceImpl implements VisitsService {

    @Override
    public void createNewVisit(Visit visit) {

    }

    @Override
    public List<Visit> findAll() {
        return null;
    }

    @Override
    public Visit findById(int visitId) {
        return null;
    }

    @Override
    public List<Visit> findAllByClientId(int clientId) {
        return null;
    }

    @Override
    public List<Visit> findAllByDoctorId(int doctorId) {
        return null;
    }

    @Override
    public void updateVisit(Visit visit, int visitId) {

    }

    @Override
    public void deleteVisit(int visitId) {

    }
}
