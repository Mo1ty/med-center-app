package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.LoyaltyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyLevelRepository extends JpaRepository<LoyaltyLevel, Integer> {
}
