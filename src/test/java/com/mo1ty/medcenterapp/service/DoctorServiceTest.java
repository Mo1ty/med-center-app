package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.service.repository.AddressRepository;
import com.mo1ty.medcenterapp.service.repository.DoctorRepository;
import com.mo1ty.medcenterapp.service.repository.TreatmentRepository;
import com.mo1ty.medcenterapp.service.repository.VisitsRepository;
import com.mo1ty.medcenterapp.service.DoctorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.*;

import static com.mo1ty.medcenterapp.entity.prototype.AddressPrototype.makeCzechAddress;
import static com.mo1ty.medcenterapp.entity.prototype.DoctorPrototype.makeDummyDoc;
import static com.mo1ty.medcenterapp.entity.prototype.TreatmentPrototype.makeDummyTreatment;
import static com.mo1ty.medcenterapp.entity.prototype.TreatmentPrototype.makeGermanTreatment;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DoctorServiceTest {


    private DoctorRepository doctorRepository;
    private TreatmentRepository treatmentRepository;
    private AddressRepository addressRepository;
    private VisitsRepository visitsRepository;
    private ModelMapper modelMapper;

    private DoctorServiceImpl doctorService;

    Treatment dummyTreatment = makeDummyTreatment();

    Doctor dummy = makeDummyDoc("jan.novotny@email.cz", makeCzechAddress(), dummyTreatment);
    DoctorVO dummyVO;


    @BeforeEach
    void setUp() {
        doctorRepository = mock(DoctorRepository.class);
        treatmentRepository = mock(TreatmentRepository.class);
        addressRepository = mock(AddressRepository.class);
        visitsRepository = mock(VisitsRepository.class);

        modelMapper = new ModelMapper();

        doctorService = new DoctorServiceImpl();
        doctorService.setDoctorRepository(doctorRepository);
        doctorService.setTreatmentRepository(treatmentRepository);
        doctorService.setAddressRepository(addressRepository);
        doctorService.setVisitsRepository(visitsRepository);
        doctorService.setModelMapper(modelMapper);

        dummy.setAllTreatments(Collections.singletonList(dummyTreatment));
        dummyVO = modelMapper.map(
                makeDummyDoc("jan.novotny@email.cz", makeCzechAddress(), dummyTreatment), DoctorVO.class);
        dummyVO.setAddressId(makeCzechAddress().getAddressId());

        when(doctorRepository.save(any())).thenReturn(dummy);
        when(doctorRepository.findById(any())).thenReturn(Optional.of(dummy));
        when(addressRepository.findById(any())).thenReturn(Optional.of(makeCzechAddress()));
        when(treatmentRepository.findById(any())).thenReturn(Optional.of(dummyTreatment));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createDoctor() {
        DoctorVO doctorVO = doctorService.createDoctor(dummyVO);

        assertNotNull(doctorVO);
        assertEquals(dummyVO.getEmail(), doctorVO.getEmail());
    }

    @Test
    void addTreatment() {

        dummy.setAllTreatments(new ArrayList<>());

        List<TreatmentVO> treatments = doctorService.addTreatment(dummy.getId(), dummyTreatment.getTreatmentId());

        assertNotNull(treatments);
        assertEquals(1, treatments.size());
        assertEquals(makeDummyTreatment().getTreatmentName(), treatments.get(0).getTreatmentName());
    }

    @Test
    void removeTreatment(){

        List<Treatment> treatmentList = new LinkedList<>(Arrays.asList(dummyTreatment, makeGermanTreatment(dummy)));
        dummy.setAllTreatments(treatmentList);

        List<TreatmentVO> treatments = doctorService.removeTreatment(dummy.getId(), makeDummyTreatment().getTreatmentId());

        assertNotNull(treatments);
        assertEquals(1, treatments.size());
    }

}