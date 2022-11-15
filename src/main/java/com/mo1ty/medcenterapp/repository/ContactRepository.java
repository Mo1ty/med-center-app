package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
