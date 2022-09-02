package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.InternalLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalLoginRepository extends JpaRepository<InternalLogin, Integer> {

}
