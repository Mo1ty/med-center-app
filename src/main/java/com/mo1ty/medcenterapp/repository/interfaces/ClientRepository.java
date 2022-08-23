package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
