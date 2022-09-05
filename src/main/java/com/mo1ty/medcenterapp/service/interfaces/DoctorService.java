package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.mapper.VisitVO;

import java.util.List;

public interface DoctorService {

    DoctorVO createDoctor(DoctorVO doctorVO);

    DoctorVO updateDoctor(DoctorVO doctorVO);

    List<Doctor> findAll();

    DoctorVO findById(int doctorId);

    void deleteClient(int doctorId);

    List<TreatmentVO> addTreatment(int treatmentId, int doctorId);

    List<TreatmentVO> removeTreatment(int treatmentId, int doctorId);

    List<TreatmentVO> getTreatments(int doctorId);

}
