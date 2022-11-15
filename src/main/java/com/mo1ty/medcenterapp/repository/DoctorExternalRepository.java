package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.view.external.DoctorExternal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorExternalRepository extends JpaRepository<DoctorExternal, Integer> {
}
