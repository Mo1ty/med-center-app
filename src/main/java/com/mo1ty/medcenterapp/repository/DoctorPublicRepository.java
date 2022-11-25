package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.publ.DoctorPublic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorPublicRepository extends JpaRepository<DoctorPublic, Integer> {

    @Query("SELECT d FROM DoctorPublic d WHERE d.specialityName = ?1")
    List<DoctorPublic> findAllBySpec(String specialityName);

}
