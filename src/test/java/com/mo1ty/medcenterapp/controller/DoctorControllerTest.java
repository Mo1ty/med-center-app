package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.service.DoctorServiceImpl;
import com.mo1ty.medcenterapp.service.controller.DoctorController;
import com.mo1ty.medcenterapp.service.interfaces.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mo1ty.medcenterapp.entity.prototype.AddressPrototype.makeAllAddresses;
import static com.mo1ty.medcenterapp.entity.prototype.DoctorPrototype.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DoctorControllerTest {

    List<Doctor> doctors;
    DoctorService doctorServ;
    ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        List<Address> addresses = makeAllAddresses();
        List<String> emails = new ArrayList<>(Arrays.asList(
                "jan.novotny@email.cz",
                "hans.muller@email.de",
                "emmanuel.garnier@email.fr"
        ));
        doctors = new LinkedList<>(Arrays.asList(
                makeCzechDoctor(emails.get(0), addresses.get(0)),
                makeGermanDoctor(emails.get(1), addresses.get(1)),
                makeFrenchDoctor(emails.get(2), addresses.get(2))
        ));
        modelMapper = new ModelMapper();
        doctorServ = mock(DoctorServiceImpl.class);
        when(doctorServ.findAll()).thenReturn(doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorVO.class))
                .collect(Collectors.toList()));
    }

    @Test
    void getAllDoctors(){
        DoctorController doctorController = new DoctorController(doctorServ);

        List<DoctorVO> response = doctorController.getAllDoctors();
        assertNotNull(response);
        assertEquals(doctors.size(), response.size());
    }

}
