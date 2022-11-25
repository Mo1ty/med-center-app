package com.mo1ty.medcenterapp.config.mapper;


import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.DoctorPublicRepository;
import com.mo1ty.medcenterapp.repository.SpecialityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfig {


    SpecialityRepository treatmentRepository;
    ClientRepository clientRepository;
    DoctorPublicRepository doctorPublicRepository;

    @Autowired
    public MapperConfig(SpecialityRepository treatmentRepository, ClientRepository clientRepository, DoctorPublicRepository doctorPublicRepository) {
        this.treatmentRepository = treatmentRepository;
        this.clientRepository = clientRepository;
        this.doctorPublicRepository = doctorPublicRepository;
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
