package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.publ.DoctorPublic;

import java.util.List;


public interface DoctorExternalService {

    DoctorPublic findById(int id);

    List<DoctorPublic> findAll();

    List<DoctorPublic> findAllBySpec(int speciality);

}
