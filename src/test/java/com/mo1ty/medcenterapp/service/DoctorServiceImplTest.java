package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.TreatmentType;
import com.mo1ty.medcenterapp.repository.interfaces.AddressRepository;
import com.mo1ty.medcenterapp.repository.interfaces.DoctorRepository;
import com.mo1ty.medcenterapp.repository.interfaces.InternalLoginRepository;
import com.mo1ty.medcenterapp.repository.interfaces.TreatmentTypeRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mo1ty.medcenterapp.prototype.DoctorsPrototypes.createDoctors;
import static com.mo1ty.medcenterapp.prototype.support.AddressPrototype.makeAddress;
import static com.mo1ty.medcenterapp.prototype.support.TreatmentTypePrototype.makeTreatmentType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;



class DoctorServiceImplTest {

    private DoctorRepository doctorRepository;

    private TreatmentTypeRepository treatmentTypeRepository;

    private AddressRepository addressRepository;

    private InternalLoginRepository internalLoginRepository;

    private DoctorService doctorService;
    @BeforeEach
    void setUp() {

        doctorRepository = mock(DoctorRepository.class);

        doctorService = new DoctorServiceImpl(doctorRepository);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findByTreatmentTypeAndQualificationLevel() {

    }
    /*
    @Test
    void findByTreatmentTypeAndQualificationLevel() {

        for(Doctor doctor : doctors){
            doctorService.createOrUpdateDoctor(doctor);
        }

        Doctor doc = doctorService.findById(2);

        assertNotNull(doc);

        List<Doctor> result = doctorService.findByTreatmentTypeAndQualificationLevel(treatmentTypes.get(0), 5);

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getFirstName(), doctors.get(2).getFirstName());

        result = doctorService.findByTreatmentTypeAndQualificationLevel(treatmentTypes.get(1), 2);

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getFirstName(), doctors.get(1).getFirstName());
        assertEquals(result.get(1).getFirstName(), doctors.get(3).getFirstName());

        assertThrows(RuntimeException.class, () -> {
            doctorService.findByTreatmentTypeAndQualificationLevel(fakeTreatmentType, 2)
            ;}
        );

        assertThrows(RuntimeException.class, () -> {
            doctorService.findByTreatmentTypeAndQualificationLevel(
                treatmentTypes.get(0), 12)
            ;}
        );
    }

     */
}