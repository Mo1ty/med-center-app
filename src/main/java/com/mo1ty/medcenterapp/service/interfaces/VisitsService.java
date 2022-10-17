package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.mapper.VisitVO;

import java.util.Date;
import java.util.List;

public interface VisitsService {

    void createVisit(VisitVO visitVO);

    void updateVisit(VisitVO visitVO);

    List<VisitVO> findAll();

    VisitVO findById(int visitId);

    List<VisitVO> findAllByClientId(int clientId);

    List<VisitVO> findAllByDoctorId(int doctorId);

    void deleteVisit(int visitId);

    List<VisitVO> findAllPendingVisits();

    List<VisitVO> findAllPendingVisitsByClientId(int clientId);

    List<VisitVO> findAllPreviousVisitsByClientId(int clientId);

    List<Long> findAllOccupiedTimes(int doctorId);

}
