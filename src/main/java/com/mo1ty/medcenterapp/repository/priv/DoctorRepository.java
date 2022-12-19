package com.mo1ty.medcenterapp.repository.priv;

import com.mo1ty.medcenterapp.entity.internal.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
