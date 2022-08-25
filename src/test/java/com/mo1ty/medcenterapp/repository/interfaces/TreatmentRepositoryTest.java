package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Treatment;
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

import static com.mo1ty.medcenterapp.prototype.TreatmentPrototype.makeTreatment;
import static com.mo1ty.medcenterapp.prototype.support.TreatmentTypePrototype.makeTreatmentType;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TreatmentRepositoryTest {

    @Autowired
    TreatmentTypeRepository treatmentTypeRepository;
    @Autowired
    TreatmentRepository treatmentRepository;

    List<TreatmentType> treatmentTypes;

    List<Treatment> treatments;

    @BeforeEach
    void setUp() {

        treatmentTypes = new ArrayList<>(
                Arrays.asList(makeTreatmentType("Clean"), makeTreatmentType("Surgery")));

        treatments = new ArrayList<>(Arrays.asList(
                makeTreatment(treatmentTypes.get(0), "Annual Cleaning"),
                makeTreatment(treatmentTypes.get(1), "Tooth Removal"),
                makeTreatment(treatmentTypes.get(0), "Tartar Removal")
        ));

    }

    @AfterEach
    void tearDown() {
        treatmentTypeRepository.deleteAll();
        treatmentRepository.deleteAll();
    }

    @Test
    void findByTreatmentName() {
        treatmentTypeRepository.saveAll(treatmentTypes);
        treatmentRepository.saveAll(treatments);

        assertEquals(treatmentRepository.findByTreatmentName
                ("Annual Cleaning").getTreatmentType(), treatmentTypes.get(0));

        assertEquals(treatmentRepository.findByTreatmentName
                ("Tooth Removal").getTreatmentType(), treatmentTypes.get(1));

        assertNull(treatmentRepository.findByTreatmentName("Tooth Maker"));
    }
}