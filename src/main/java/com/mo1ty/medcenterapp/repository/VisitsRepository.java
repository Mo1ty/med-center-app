package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.publ.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface VisitsRepository extends JpaRepository<Visit, Integer> {

    @Query("SELECT v FROM Visit v WHERE v.client.clientId = ?1")
    List<Visit> findAllByClientVisited(int clientVisited);

    @Query("SELECT v FROM Visit v WHERE v.doctor.doctorId = ?1")
    List<Visit> findAllByDoctorAccepted(int doctorAccepted);

    @Query("SELECT v FROM Visit v WHERE v.datetime > ?1")
    List<Visit> findAllByDate(Timestamp date);

    @Query("SELECT v FROM Visit v WHERE v.datetime > ?1 and v.client.clientId = ?2")
    List<Visit> findAllByDateAndClientId(Timestamp date, int clientVisited);

    @Query("SELECT v FROM Visit v WHERE v.datetime < ?1 and v.client.clientId = ?2")
    List<Visit> findAllBeforeDateAndClientId(Timestamp date, int clientVisited);

    @Query("SELECT v FROM Visit v WHERE v.datetime > ?1 and v.doctor.doctorId = ?2")
    List<Visit> findAllByDateAndDoctorId(Timestamp date, int doctorAccepted);

}
