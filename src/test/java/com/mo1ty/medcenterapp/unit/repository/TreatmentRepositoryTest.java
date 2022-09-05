package com.mo1ty.medcenterapp.unit.repository;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.InternalLogin;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.repository.DoctorRepository;
import com.mo1ty.medcenterapp.repository.InternalLoginRepository;
import com.mo1ty.medcenterapp.repository.TreatmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mo1ty.medcenterapp.prototype.AddressPrototype.makeAllAddresses;
import static com.mo1ty.medcenterapp.prototype.DoctorPrototype.makeAllDoctors;
import static com.mo1ty.medcenterapp.prototype.InternalLoginPrototype.makeAllLogins;
import static com.mo1ty.medcenterapp.prototype.TreatmentPrototype.makeAllTreatments;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TreatmentRepositoryTest {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    InternalLoginRepository internalLoginRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    TreatmentRepository treatmentRepository;

    List<Address> addressList;
    List<String> emails;
    List<InternalLogin> internalLoginList;
    List<Doctor> doctorList;
    List<Treatment> treatmentList;

    @BeforeEach
    void setUp(){
        addressList = makeAllAddresses();
        emails = new ArrayList<>(Arrays.asList(
                "jan.novotny@email.cz",
                "hans.muller@email.de",
                "emmanuel.garnier@email.fr"));
        internalLoginList = makeAllLogins(emails);
        doctorList = makeAllDoctors(emails, addressList);
        treatmentList = makeAllTreatments(doctorList);

        addressRepository.saveAll(addressList);
        internalLoginRepository.saveAll(internalLoginList);
        doctorRepository.saveAll(doctorList);
        treatmentRepository.saveAll(treatmentList);
    }

    @AfterEach
    void tearDown(){
        treatmentRepository.deleteAll();
        doctorRepository.deleteAll();
        addressRepository.deleteAll();
        internalLoginRepository.deleteAll();
    }

    @Test
    public void findByTreatmentIdTest(){
        List<Treatment> czech = treatmentRepository.findByTreatmentName("TREATMENT_CZECH");
        Treatment french = treatmentRepository.findByTreatmentName("TREATMENT_FRENCH").get(0);
        List<Treatment> fakeTreatments = treatmentRepository.findByTreatmentName("FAKE_NAME");

        assertNotNull(czech);
        assertEquals(1, czech.size());
        assertEquals(400, czech.get(0).getPrice());

        assertNotNull(french);
        assertNotEquals(700, french.getPrice());
        assertNotEquals(400, french.getPrice());
        assertNotNull(french.getDoctors());

        assertEquals(0, fakeTreatments.size());

    }

}
