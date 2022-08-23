package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitsRepository extends JpaRepository<Visit, Integer> {

    @Query("SELECT t FROM Visits t WHERE t.client_id = ?1")
    List<Visit> findByClientId(int clientId);

    @Query("SELECT t FROM Visits t WHERE t.doctor_id = ?1")
    List<Visit> findByDoctorId(int doctorId);
}
