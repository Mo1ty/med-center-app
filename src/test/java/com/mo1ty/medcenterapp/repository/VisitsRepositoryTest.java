package com.mo1ty.medcenterapp.repository;

import com.mo1ty.medcenterapp.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mo1ty.medcenterapp.entity.prototype.AddressPrototype.makeAllAddresses;
import static com.mo1ty.medcenterapp.entity.prototype.ClientPrototype.makeAllClients;
import static com.mo1ty.medcenterapp.entity.prototype.DoctorPrototype.makeAllDoctors;
import static com.mo1ty.medcenterapp.entity.prototype.InternalLoginPrototype.makeAllLogins;
import static com.mo1ty.medcenterapp.entity.prototype.TreatmentPrototype.makeAllTreatments;
import static com.mo1ty.medcenterapp.entity.prototype.VisitsPrototype.makeAllVisits;
import static com.mo1ty.medcenterapp.entity.prototype.VisitsPrototype.makeLaterVisit;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VisitsRepositoryTest {

    AddressRepository addressRepository;
    InternalLoginRepository internalLoginRepository;
    DoctorRepository doctorRepository;
    TreatmentRepository treatmentRepository;
    ClientRepository clientRepository;
    VisitsRepository visitsRepository;

    List<Address> addressList;
    List<String> emails;
    List<InternalLogin> internalLoginList;
    List<Doctor> doctorList;
    List<Treatment> treatmentList;
    List<Visit> visitList;
    List<Client> clientList;

    @Autowired
    public VisitsRepositoryTest(AddressRepository addressRepository, InternalLoginRepository internalLoginRepository,
                                DoctorRepository doctorRepository, TreatmentRepository treatmentRepository,
                                ClientRepository clientRepository, VisitsRepository visitsRepository) {

        this.addressRepository = addressRepository;
        this.internalLoginRepository = internalLoginRepository;
        this.doctorRepository = doctorRepository;
        this.treatmentRepository = treatmentRepository;
        this.clientRepository = clientRepository;
        this.visitsRepository = visitsRepository;
    }

    @BeforeEach
    void setUp(){ // All entities go in this order: CZECH, GERMAN, FRENCH
        addressList = makeAllAddresses();
        addressRepository.saveAll(addressList);

        emails = new ArrayList<>(Arrays.asList(
                "jan.novotny@email.cz",
                "hans.muller@email.de",
                "emmanuel.garnier@email.fr"));
        internalLoginList = makeAllLogins(emails);
        internalLoginRepository.saveAll(internalLoginList);

        doctorList = makeAllDoctors(emails, addressList);
        doctorRepository.saveAll(doctorList);

        treatmentList = makeAllTreatments(doctorList);
        treatmentRepository.saveAll(treatmentList);

        clientList = makeAllClients(addressList);
        clientRepository.saveAll(clientList);

        // Add 2 Czech visits, 3 German visits and 2 French visits
        visitList = makeAllVisits(treatmentList, clientList, doctorList);
        visitList.add(makeLaterVisit(treatmentList.get(1), clientList.get(1), doctorList.get(1)));
        visitsRepository.saveAll(visitList);

    }

    @AfterEach
    void tearDown(){
        visitsRepository.deleteAll();
        treatmentRepository.deleteAll();
        doctorRepository.deleteAll();
        clientRepository.deleteAll();
        addressRepository.deleteAll();
        internalLoginRepository.deleteAll();

    }

    @Test
    void findAllByClientVisitedTest(){
        List<Visit> czechVisits = visitsRepository.findAllByClientVisited(clientList.get(0).getClientId());

        assertNotNull(czechVisits);
        assertNotEquals(visitList.size(), czechVisits.size());
        assertEquals(2, czechVisits.size());

        List<Visit> germanVisits = visitsRepository.findAllByClientVisited(clientList.get(1).getClientId());

        assertNotNull(germanVisits);
        assertNotEquals(visitList.size(), germanVisits.size());
        assertEquals(3, germanVisits.size());
    }

    @Test
    void findAllByDoctorAcceptedTest(){
        List<Visit> czechVisits = visitsRepository.findAllByDoctorAccepted(doctorList.get(0).getId());

        assertNotNull(czechVisits);
        assertNotEquals(visitList.size(), czechVisits.size());
        assertEquals(2, czechVisits.size());

        List<Visit> germanVisits = visitsRepository.findAllByDoctorAccepted(doctorList.get(1).getId());

        assertNotNull(germanVisits);
        assertNotEquals(visitList.size(), germanVisits.size());
        assertEquals(3, germanVisits.size());
    }


    @Test
    void findAllByDateTest() {
        List<Visit> visitsAfter = visitsRepository.findAllByDate(Date.from(Instant.now()));

        assertNotNull(visitsAfter);
        assertNotEquals(visitList.size(), visitsAfter.size());
        assertEquals(4, visitsAfter.size());

        List<Visit> allTimedVisits = visitsRepository.findAllByDate(
                Date.from(Instant.now().minusSeconds(1000000))
        );

        assertNotNull(allTimedVisits);
        assertEquals(visitList.size(), allTimedVisits.size());

        List<Visit> noVisits = visitsRepository.findAllByDate(
                Date.from(Instant.now().plusSeconds(1000000))
        );

        assertEquals(0, noVisits.size());
    }

    @Test
    void findAllByDateAndClientIdTest() {

        List<Visit> germanVisitsAfter = visitsRepository.findAllByDateAndClientId(
                Date.from(Instant.now()), clientList.get(1).getClientId()
        );

        assertNotNull(germanVisitsAfter);
        assertNotEquals(visitList.size(), germanVisitsAfter.size());
        assertEquals(2, germanVisitsAfter.size());

        List<Visit> allTimedGermanVisits = visitsRepository.findAllByDateAndClientId(
                Date.from(Instant.now().minusSeconds(1000000)), clientList.get(1).getClientId()
        );

        assertNotNull(allTimedGermanVisits);
        assertEquals(3, allTimedGermanVisits.size());

        List<Visit> noGermanVisits = visitsRepository.findAllByDateAndClientId(
                Date.from(Instant.now().plusSeconds(1000000)), clientList.get(1).getClientId()
                );

        assertEquals(0, noGermanVisits.size());

        List<Visit> czechTimedVisits = visitsRepository.findAllByDateAndClientId(
                Date.from(Instant.now()), clientList.get(0).getClientId()
        );

        assertEquals(1, czechTimedVisits.size());

    }

    @Test
    void findAllByDateAndDoctorIdTest() {

        List<Visit> germanVisitsAfter = visitsRepository.findAllByDateAndDoctorId(
                Date.from(Instant.now()), doctorList.get(1).getId()
        );

        assertNotNull(germanVisitsAfter);
        assertNotEquals(visitList.size(), germanVisitsAfter.size());
        assertEquals(2, germanVisitsAfter.size());

        List<Visit> allTimedGermanVisits = visitsRepository.findAllByDateAndDoctorId(
                Date.from(Instant.now().minusSeconds(1000000)), doctorList.get(1).getId()
        );

        assertNotNull(allTimedGermanVisits);
        assertEquals(3, allTimedGermanVisits.size());

        List<Visit> noGermanVisits = visitsRepository.findAllByDateAndDoctorId(
                Date.from(Instant.now().plusSeconds(1000000)), doctorList.get(1).getId()
        );

        assertEquals(0, noGermanVisits.size());

        List<Visit> czechTimedVisits = visitsRepository.findAllByDateAndDoctorId(
                Date.from(Instant.now()), doctorList.get(0).getId()
        );

        assertEquals(1, czechTimedVisits.size());

    }
}
