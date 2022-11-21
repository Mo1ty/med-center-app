package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {

    // @Query("SELECT l FROM LoginData l WHERE l.email = ?1")
    List<LoginData> findByEmail(String email);

}
