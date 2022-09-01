package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;

import java.util.List;

public interface TreatmentService {

    List<TreatmentVO> findByName(String treatmentName);

    void createTreatment(TreatmentVO treatmentVO);

    void updateTreatment(TreatmentVO treatmentVO);

    List<TreatmentVO> findAll();

    TreatmentVO findById(int treatmentId);

    void deleteTreatment(int treatmentId);

}
