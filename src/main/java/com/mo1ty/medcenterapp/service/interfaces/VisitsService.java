package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Visit;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitsService {

    void createOrUpdateVisit(Visit visit);

    List<Visit> findAll();

    Visit findById(int visitId);

    List<Visit> findAllByClientId(int clientId);

    List<Visit> findAllByDoctorId(int doctorId);

    void deleteVisit(int visitId);

}
