package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.DoctorVO;

import java.util.List;

public interface DoctorService {

    DoctorVO createDoctor(DoctorVO doctorVO);

    DoctorVO updateDoctor(DoctorVO doctorVO);

    List<Doctor> findAll();

    List<DoctorVO> findByTreatmentId(int treatmentId);

    DoctorVO findById(int doctorId);

    void deleteClient(int doctorId);

}
