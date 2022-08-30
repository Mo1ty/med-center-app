package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.InternalLogin;
import com.mo1ty.medcenterapp.repository.interfaces.AddressRepository;
import com.mo1ty.medcenterapp.repository.interfaces.DoctorRepository;
import com.mo1ty.medcenterapp.repository.interfaces.InternalLoginRepository;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DoctorServiceImplTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private InternalLoginRepository internalLoginRepository;

    private DoctorService doctorService;

    List<String> emails;
    List<InternalLogin> internalLogins;
    //List<TreatmentType> treatmentTypes;
    Address addr;
    List<Doctor> doctors;

    @BeforeEach
    void setUp() {

        /*doctorService = new DoctorServiceImpl(doctorRepository);

        emails = new ArrayList<>(Arrays.asList("1@email.com", "2@email.com",
                "3@email.com", "4@email.com"));

        internalLogins = new ArrayList<>();
        for(String mail : emails){
            internalLogins.add(createInternalLogin(mail));
        }

        treatmentTypes = new ArrayList<>(
                Arrays.asList(makeTreatmentType("Clean"), makeTreatmentType("Surgery")));

        addr = makeAddress();

        doctors = createDoctors(addr, internalLogins, treatmentTypes);

        fakeTreatmentType = makeTreatmentType("Fake");*/
    }

    @AfterEach
    void tearDown() {
        doctorRepository.deleteAll();
        addressRepository.deleteAll();
        internalLoginRepository.deleteAll();
    }

    @Test
    void findAll(){

        // When(doctorService.findById(eq(doctors.get(0).getId())).thenReturn(doctors.get(0)));

        internalLoginRepository.saveAll(internalLogins);
        List<InternalLogin> result1 = internalLoginRepository.findAll();


        addressRepository.save(addr);
        List<Address> addresses = addressRepository.findAll();

        doctorRepository.saveAll(doctors);
        List<Doctor> doc = doctorService.findAll();

        assertEquals(doc.size(), doctors.size());
    }

    @Test
    void findByTreatmentTypeAndQualificationLevel() {

        /*internalLoginRepository.saveAll(internalLogins);
        addressRepository.save(addr);
        doctorRepository.saveAll(doctors);

        List<Doctor> result = doctorService.findByTreatmentTypeAndQualificationLevel(treatmentTypes.get(0), 5);

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getFirstName(), doctors.get(2).getFirstName());

        result = doctorService.findByTreatmentTypeAndQualificationLevel(treatmentTypes.get(1), 2);

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getFirstName(), doctors.get(1).getFirstName());
        assertEquals(result.get(1).getFirstName(), doctors.get(3).getFirstName());

        assertThrows(DataNotFoundException.class, ()
                -> doctorService.findByTreatmentTypeAndQualificationLevel
                (fakeTreatmentType, 2)
        );

        assertThrows(DataNotFoundException.class, ()
                -> doctorService.findByTreatmentTypeAndQualificationLevel
                (treatmentTypes.get(0), 12)
        );*/

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