package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Treatment;

import java.util.List;

public interface TreatmentService {

    void createNewClient(Treatment treatment);

    List<Treatment> findAll();

    Treatment findById(int treatmentId);

    void updateClient(Treatment treatment, int treatmentId);

    void deleteClient(int treatmentId);

}
