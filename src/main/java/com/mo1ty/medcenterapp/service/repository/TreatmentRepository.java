package com.mo1ty.medcenterapp.service.repository;

import com.mo1ty.medcenterapp.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    List<Treatment> findByTreatmentName(String treatmentName);
}
