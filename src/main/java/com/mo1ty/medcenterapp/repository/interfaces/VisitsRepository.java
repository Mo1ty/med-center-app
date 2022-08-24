package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitsRepository extends JpaRepository<Visit, Integer> {

    // Custom methods to find every visit done by one patient or doctor.

    @Query("SELECT t FROM Visits t WHERE t.client_id = ?1")
    List<Visit> findAllByClientId(int clientId);

    @Query("SELECT t FROM Visits t WHERE t.doctor_id = ?1")
    List<Visit> findAllByDoctorId(int doctorId);
}
