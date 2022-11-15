package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.Speciality;
import com.mo1ty.medcenterapp.entity.view.external.DoctorExternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorExternalRepository extends JpaRepository<DoctorExternal, Integer> {

    @Query("SELECT d FROM DoctorExternal d WHERE d.specialityName = ?1")
    List<DoctorExternal> findAllBySpec(String speciality);

}
