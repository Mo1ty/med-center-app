package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {
}
