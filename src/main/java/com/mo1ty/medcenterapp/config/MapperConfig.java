package com.mo1ty.medcenterapp.config;


import com.mo1ty.medcenterapp.config.mapper.ClientVO;
import com.mo1ty.medcenterapp.config.mapper.DoctorVO;
import com.mo1ty.medcenterapp.config.mapper.VisitVO;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.view.external.DoctorExternal;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.DoctorExternalRepository;
import com.mo1ty.medcenterapp.repository.SpecialityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MapperConfig {


    SpecialityRepository treatmentRepository;
    ClientRepository clientRepository;
    DoctorExternalRepository doctorRepository;

    @Autowired
    public MapperConfig(SpecialityRepository treatmentRepository, ClientRepository clientRepository, DoctorExternalRepository doctorRepository) {
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

        /*modelMapper.createTypeMap(Visit.class, VisitVO.class)
                .addMapping(Visit -> Visit.getTreatmentDone().getTreatmentId(), VisitVO::setTreatmentDoneId)
                .addMapping(Visit -> Visit.getClientVisited().getClientId(), VisitVO::setClientVisitedId)
                .addMapping(Visit -> Visit.getDoctorAccepted().getId(), VisitVO::setDoctorAcceptedId);


        modelMapper.createTypeMap(Client.class, ClientVO.class)
                .addMapping(Client -> Client.getAddress().getAddressId(), ClientVO::setAddressId);

        modelMapper.createTypeMap(DoctorExternal.class, DoctorExternalVO.class)
                .addMapping(Doctor -> Doctor.getAddress().getAddressId(), DoctorVO::setAddressId);

        */
        // Create Type Maps for turning VOs into POJOs

        return modelMapper;
    }


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(){

        return null;

    }

    /*
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
    */
}
