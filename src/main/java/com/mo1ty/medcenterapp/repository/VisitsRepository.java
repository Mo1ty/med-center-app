package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitsRepository extends JpaRepository<Visit, Integer> {

    @Query("SELECT v FROM Visit v WHERE v.clientVisited.clientId = ?1")
    List<Visit> findAllByClientVisited(int clientVisited);

    @Query("SELECT v FROM Visit v WHERE v.doctorAccepted.id = ?1")
    List<Visit> findAllByDoctorAccepted(int doctorAccepted);

    @Query("SELECT v FROM Visit v WHERE v.datetime > ?1")
    List<Visit> findAllByDate(Date date);

    @Query("SELECT v FROM Visit v WHERE v.datetime > ?1 and v.clientVisited.clientId = ?2")
    List<Visit> findAllByDateAndClientId(Date date, int clientVisited);

    @Query("SELECT v FROM Visit v WHERE v.datetime > ?1 and v.doctorAccepted.id = ?2")
    List<Visit> findAllByDateAndDoctorId(Date date, int doctorAccepted);
}
