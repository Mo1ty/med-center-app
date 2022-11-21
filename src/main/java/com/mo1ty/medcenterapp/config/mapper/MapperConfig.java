package com.mo1ty.medcenterapp.config.mapper;


import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.DoctorRepository;
import com.mo1ty.medcenterapp.repository.SpecialityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@Configuration
public class MapperConfig {


    SpecialityRepository treatmentRepository;
    ClientRepository clientRepository;
    DoctorRepository doctorRepository;

    @Autowired
    public MapperConfig(SpecialityRepository treatmentRepository, ClientRepository clientRepository, DoctorRepository doctorRepository) {
        this.treatmentRepository = treatmentRepository;
        this.clientRepository = clientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Create Type Maps for turning POJOs into VOs.

        modelMapper.createTypeMap(Client.class, ClientVO.class)
                .addMapping(Client -> Client.getContact().getId(), ClientVO::setContactId)
                .addMapping(Client -> Client.getLoyaltyLevel().getId(), ClientVO:: setLoyaltyLevelId);

        return modelMapper;
    }

}
