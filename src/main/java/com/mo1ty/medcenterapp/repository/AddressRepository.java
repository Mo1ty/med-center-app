package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
