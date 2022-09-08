package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query("SELECT distinct doctor FROM Doctor doctor JOIN doctor.allTreatments treatment WHERE treatment.treatmentId = :treatmentId")
    List<Doctor> findByTreatmentId(int treatmentId);
}
