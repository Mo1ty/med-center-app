package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.TreatmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    // Custom search used to check if this doctor can do concrete service
    @Query("SELECT d FROM Doctor d WHERE d.treatmentType = ?1 and d.qualificationLevel >= ?2")
    List<Doctor> findByTreatmentTypeAndQualificationLevel(TreatmentType treatmentType, int qualificationLevel);

}
