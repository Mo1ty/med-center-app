package com.mo1ty.medcenterapp.mapper.config;

import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.VisitVO;
import com.mo1ty.medcenterapp.service.repository.ClientRepository;
import com.mo1ty.medcenterapp.service.repository.DoctorRepository;
import com.mo1ty.medcenterapp.service.repository.TreatmentRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Create Type Maps for turning POJOs into VOs.

        modelMapper.createTypeMap(Visit.class, VisitVO.class)
                .addMapping(Visit -> Visit.getTreatmentDone().getTreatmentId(), VisitVO::setTreatmentDoneId)
                .addMapping(Visit -> Visit.getClientVisited().getClientId(), VisitVO::setClientVisitedId)
                .addMapping(Visit -> Visit.getDoctorAccepted().getId(), VisitVO::setDoctorAcceptedId);

        modelMapper.createTypeMap(Client.class, ClientVO.class)
                .addMapping(Client -> Client.getAddress().getAddressId(), ClientVO::setAddressId);

        modelMapper.createTypeMap(Doctor.class, DoctorVO.class)
                .addMapping(Doctor -> Doctor.getAddress().getAddressId(), DoctorVO::setAddressId);

        // Create Type Maps for turning VOs into POJOs

        return modelMapper;
    }

}
