package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;

import java.util.List;

public interface DoctorService {

    DoctorVO createDoctor(DoctorVO doctorVO);

    DoctorVO updateDoctor(DoctorVO doctorVO);

    List<DoctorVO> findAll();

    DoctorVO findById(int doctorId);

    void deleteDoctor(int doctorId);

    List<TreatmentVO> addTreatment(int treatmentId, int doctorId);

    List<TreatmentVO> removeTreatment(int treatmentId, int doctorId);

    List<TreatmentVO> getTreatments(int doctorId);

}
