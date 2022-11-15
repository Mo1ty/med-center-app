package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

    @Query("SELECT s FROM Speciality s WHERE s.name = name")
    Optional<Speciality> getByName(String name);
}
