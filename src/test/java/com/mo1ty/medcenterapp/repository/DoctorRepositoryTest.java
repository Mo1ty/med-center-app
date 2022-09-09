package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.InternalLogin;
import com.mo1ty.medcenterapp.entity.Treatment;
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

import static com.mo1ty.medcenterapp.entity.prototype.AddressPrototype.makeAllAddresses;
import static com.mo1ty.medcenterapp.entity.prototype.DoctorPrototype.makeAllDoctors;
import static com.mo1ty.medcenterapp.entity.prototype.InternalLoginPrototype.makeAllLogins;
import static com.mo1ty.medcenterapp.entity.prototype.TreatmentPrototype.makeAllTreatments;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DoctorRepositoryTest {

    AddressRepository addressRepository;
    InternalLoginRepository internalLoginRepository;
    DoctorRepository doctorRepository;
    TreatmentRepository treatmentRepository;

    List<Address> addressList;
    List<String> emails;
    List<InternalLogin> internalLoginList;
    List<Doctor> doctorList;
    List<Treatment> treatmentList;

    @Autowired
    public DoctorRepositoryTest(AddressRepository addressRepository, InternalLoginRepository internalLoginRepository,
                                DoctorRepository doctorRepository, TreatmentRepository treatmentRepository) {
        this.addressRepository = addressRepository;
        this.internalLoginRepository = internalLoginRepository;
        this.doctorRepository = doctorRepository;
        this.treatmentRepository = treatmentRepository;
    }

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
    void findByTreatmentIdTest(){
        List<Doctor> czechDoc = doctorRepository.findByTreatmentId(treatmentList.get(0).getTreatmentId());
        Doctor germanDoc = doctorRepository.findByTreatmentId(treatmentList.get(1).getTreatmentId()).get(0);
        List<Doctor> emptyDoc = doctorRepository.findByTreatmentId(0);

        assertNotNull(czechDoc);
        assertEquals(1, czechDoc.size());

        assertEquals(doctorList.get(1).getAddress(), germanDoc.getAddress());
        assertNotEquals("Jan", germanDoc.getFirstName());

        assertEquals(0, emptyDoc.size());
    }

}
