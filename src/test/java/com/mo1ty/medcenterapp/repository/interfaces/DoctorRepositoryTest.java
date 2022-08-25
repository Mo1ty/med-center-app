package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.InternalLogin;
import com.mo1ty.medcenterapp.entity.TreatmentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static com.mo1ty.medcenterapp.prototype.DoctorsPrototypes.createDoctors;
import static com.mo1ty.medcenterapp.prototype.support.AddressPrototype.makeAddress;
import static com.mo1ty.medcenterapp.prototype.support.InternalLoginPrototype.createInternalLogin;
import static com.mo1ty.medcenterapp.prototype.support.TreatmentTypePrototype.makeTreatmentType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    TreatmentTypeRepository treatmentTypeRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    InternalLoginRepository internalLoginRepository;


    List<String> emails;

    List<InternalLogin> internalLogins;

    List<TreatmentType> treatmentTypes;

    Address addr;

    List<Doctor> doctors;

    TreatmentType fakeTreatmentType;

    @BeforeEach
    void setUp() {
        emails = new ArrayList<>(Arrays.asList("1@email.com", "2@email.com",
                "3@email.com", "4@email.com"));

        internalLogins = new ArrayList<>();
        for(String mail : emails){
            internalLogins.add(createInternalLogin(mail));
        }

        treatmentTypes = new ArrayList<>(
                Arrays.asList(makeTreatmentType("Clean"), makeTreatmentType("Surgery")));

        addr = makeAddress();

        doctors = createDoctors(addr, emails, treatmentTypes);

        fakeTreatmentType = makeTreatmentType("Fake");
    }

    @AfterEach
    void tearDown() {
        doctorRepository.deleteAll();
        internalLoginRepository.deleteAll();
        addressRepository.deleteAll();
        treatmentTypeRepository.deleteAll();
    }

    @Test
    void findById(){
        internalLoginRepository.saveAll(internalLogins);
        treatmentTypeRepository.saveAll(treatmentTypes);
        addressRepository.save(addr);
        doctorRepository.saveAll(doctors);

        List<Doctor> doc = doctorRepository.findAll();

        assertEquals(doc.size(), doctors.size());
    }

    @Test
    void findByTreatmentTypeAndQualificationLevel() {
        internalLoginRepository.saveAll(internalLogins);
        treatmentTypeRepository.saveAll(treatmentTypes);
        addressRepository.save(addr);
        doctorRepository.saveAll(doctors);

        List<Doctor> result = doctorRepository.findAll();

        assertNotNull(result);
        assertEquals(result.size(), 4);
        //assertEquals(result.get(0).getFirstName(), doctors.get(2).getFirstName());

        result = doctorRepository.findByTreatmentTypeAndQualificationLevel
                (treatmentTypes.get(1), 2);

        assertNotNull(result);
        System.out.println(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getFirstName(), doctors.get(1).getFirstName());
        assertEquals(result.get(1).getFirstName(), doctors.get(3).getFirstName());

        assertThrows(RuntimeException.class, () -> {
            doctorRepository.findByTreatmentTypeAndQualificationLevel(fakeTreatmentType, 2)
            ;}
        );

        result = doctorRepository.findByTreatmentTypeAndQualificationLevel(treatmentTypes.get(0), 12);

        System.out.println(result);
        System.out.println(result.size());
        for(Doctor doc : result){
            System.out.println(doc);
            System.out.println(doc.getFirstName() + " " + doc.getTreatmentType() + " " + doc.getQualificationLevel());
        }
    }
}