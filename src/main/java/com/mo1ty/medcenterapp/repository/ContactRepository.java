package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query("SELECT c FROM Contact c WHERE c.loginData.id = ?1")
    List<Contact> findAllByLoginId(int idNum);
}
