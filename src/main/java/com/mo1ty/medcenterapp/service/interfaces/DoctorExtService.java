package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Speciality;
import com.mo1ty.medcenterapp.entity.view.external.DoctorExternal;

import java.util.List;


public interface DoctorExtService {

    DoctorExternal findById(int id);

    List<DoctorExternal> findAll();

    List<DoctorExternal> findAllBySpec(String specName);

}
