package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    // Custom search used to check if this doctor can do concrete service
    @Query("SELECT t FROM doctors t WHERE t.service_type_id = ?1 AND t.qualification_level >= ?2")
    List<Doctor> findByServiceTypeAndQualiLevel(int serviceTypeId, int qualificationLevel);

}
