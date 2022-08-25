package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VisitsRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    TreatmentTypeRepository treatmentTypeRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    InternalLoginRepository internalLoginRepository;
    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    VisitsRepository visitsRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllByClientVisited() {
        // implement later
    }

    @Test
    void findAllByDoctorAccepted() {
        // implement later
    }
}