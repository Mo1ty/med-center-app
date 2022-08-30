package com.mo1ty.medcenterapp.mapper.config;

import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.VisitVO;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MapperConfig {

    // Converter to turn a list of visits into the VO object
    Converter<List<Visit>, List<VisitVO>> toVisitVo = new AbstractConverter<List<Visit>, List<VisitVO>>() {
        @Override
        protected List<VisitVO> convert(List<Visit> visits) {
            List<VisitVO> visitsVo = new ArrayList<>();
            for(Visit visit : visits){
                visitsVo.add(modelMapper().map(visit, VisitVO.class));
            }

            return visitsVo;
        }
    };

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(Visit.class, VisitVO.class)
                .addMapping(Visit -> Visit.getTreatmentDone().getTreatmentId(), VisitVO::setTreatmentDone)
                .addMapping(Visit -> Visit.getClientVisited().getClientId(), VisitVO::setClientVisited)
                .addMapping(Visit -> Visit.getDoctorAccepted().getId(), VisitVO::setDoctorAccepted);

        modelMapper.addConverter(toVisitVo);

        return modelMapper;
    }


}
