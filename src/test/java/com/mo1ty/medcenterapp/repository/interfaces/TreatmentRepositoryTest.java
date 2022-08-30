package com.mo1ty.medcenterapp.repository.interfaces;

import com.mo1ty.medcenterapp.entity.Treatment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TreatmentRepositoryTest {

    @Autowired
    TreatmentRepository treatmentRepository;

    //List<TreatmentType> treatmentTypes;

    List<Treatment> treatments;

    @BeforeEach
    void setUp() {

        /*treatmentTypes = new ArrayList<>(
                Arrays.asList(makeTreatmentType("Clean"), makeTreatmentType("Surgery")));

        treatments = new ArrayList<>(Arrays.asList(
                makeTreatment(treatmentTypes.get(0), "Annual Cleaning"),
                makeTreatment(treatmentTypes.get(1), "Tooth Removal"),
                makeTreatment(treatmentTypes.get(0), "Tartar Removal")
        ));*/

    }

    @AfterEach
    void tearDown() {
        treatmentRepository.deleteAll();
    }

    @Test
    void findByTreatmentName() {
        /*treatmentTypeRepository.saveAll(treatmentTypes);
        treatmentRepository.saveAll(treatments);

        assertEquals(treatmentRepository.findByTreatmentName
                ("Annual Cleaning").size(), 1);
        assertEquals(treatmentRepository.findByTreatmentName
                ("Annual Cleaning").get(0).getTreatmentType(), treatmentTypes.get(0));

        assertEquals(treatmentRepository.findByTreatmentName
                ("Tooth Removal").size(), 1);
        assertEquals(treatmentRepository.findByTreatmentName
                ("Tooth Removal").get(0).getTreatmentType(), treatmentTypes.get(1));

        assertEquals(treatmentRepository.findByTreatmentName("Tooth Maker").size(), 0);*/
    }
}