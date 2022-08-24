package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.TreatmentType;
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
import java.util.Optional;

import static com.mo1ty.medcenterapp.prototype.DoctorsPrototypes.createDoctors;
import static com.mo1ty.medcenterapp.prototype.support.AddressPrototype.makeAddress;
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

    List<TreatmentType> treatmentTypes = new ArrayList<>(
            Arrays.asList(makeTreatmentType(1, "Clean"), makeTreatmentType(2, "Surgery")));

    Address addr = makeAddress();

    List<Doctor> doctors = createDoctors(addr, treatmentTypes);

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    /* TEST CAN ONLY BE LAUNCHED ONCE ON A NEW DB.
     * AFTER THAT STARTS FAILING.
     * FIX ASAP!
     */

    @Test
    void findById() {

        doctorRepository.save(doctors.get(0));

        Optional<Doctor> doc = doctorRepository.findById(doctors.get(0).getId());
        Doctor foundDoc = null;
        if(doc.isPresent())
            foundDoc = doc.get();
        assertNotNull(foundDoc);
        assertEquals(doctors.get(0).getFirstName(), foundDoc.getFirstName());
    }

    @Test
    void findByTreatmentTypeAndQualificationLevel() {
        for(Doctor doctor : doctors){
            doctorRepository.save(doctor);
        }

        List<Doctor> result = doctorRepository.findByTreatmentTypeAndQualificationLevel(1, 5);

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getFirstName(), doctors.get(2).getFirstName());

        result = doctorRepository.findByTreatmentTypeAndQualificationLevel(2, 2);

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getFirstName(), doctors.get(1).getFirstName());
        assertEquals(result.get(1).getFirstName(), doctors.get(3).getFirstName());

        assertThrows(RuntimeException.class, () -> {
            doctorRepository.findByTreatmentTypeAndQualificationLevel(3, 2)
            ;}
        );

        assertThrows(RuntimeException.class, () -> {
            doctorRepository.findByTreatmentTypeAndQualificationLevel(1, 12)
            ;}
        );
    }

    // find out why JPA Repository does not do anything and does not work with database
}