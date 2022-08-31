package com.mo1ty.medcenterapp.mapper.config;

import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Treatment;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.VisitVO;
import com.mo1ty.medcenterapp.repository.interfaces.AddressRepository;
import com.mo1ty.medcenterapp.repository.interfaces.TreatmentRepository;
import com.mo1ty.medcenterapp.repository.interfaces.VisitsRepository;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MapperConfig {

    @Autowired
    VisitsRepository visitsRepository;
    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    AddressRepository addressRepository;

    /*
    // Converter to turn a list of visits into the VO object
    Converter<List<Visit>, ArrayList<Integer>> toVisitVo = new AbstractConverter<List<Visit>, ArrayList<Integer>>() {
        @Override
        protected ArrayList<Integer> convert(List<Visit> visits) {
            ArrayList<Integer> visitsVo = new ArrayList<>();
            for(Visit visit : visits){
                visitsVo.add(visit.getVisitId());
            }

            return visitsVo;
        }
    };

    Converter<List<Treatment>, ArrayList<Integer>> toTreatmentVo = new AbstractConverter<List<Treatment>, ArrayList<Integer>>() {
        @Override
        protected ArrayList<Integer> convert(List<Treatment> treatments) {
            ArrayList<Integer> treatmentsVo = new ArrayList<>();
            for(Treatment treatment : treatments){
                treatmentsVo.add(treatment.getTreatmentId());
            }

            return treatmentsVo;
        }
    };

    Converter<ArrayList<Integer>, List<Visit>> toVisit = new AbstractConverter<ArrayList<Integer>, List<Visit>>() {
        @Override
        protected List<Visit> convert(ArrayList<Integer> integers) {
            List<Visit> visits = new ArrayList<>();

            for(int visitId : integers){
                visits.add(visitsRepository.findById(visitId).orElse(null));
            }
            return visits;
        }
    };

    Converter<ArrayList<Integer>, List<Treatment>> toTreatment = new AbstractConverter<ArrayList<Integer>, List<Treatment>>() {
        @Override
        protected List<Treatment> convert(ArrayList<Integer> integers) {
            List<Treatment> treatments = new ArrayList<>();

            for(int treatmentId : integers){
                treatments.add(treatmentRepository.findById(treatmentId).orElse(null));
            }
            return treatments;
        }
    };

    Converter<Treatment, Integer> toTreatmentVo = new AbstractConverter<Treatment, Integer>() {
        @Override
        protected Integer convert(Treatment treatment) {
            return (Integer) treatment.getTreatmentId();
        }
    };

    Converter<Visit, Integer> toVisitVo = new AbstractConverter<Visit, Integer>() {
        @Override
        protected Integer convert(Visit visit) {
            return visit.getVisitId();
        }
    };*/

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        modelMapper.createTypeMap(Visit.class, VisitVO.class)
                .addMapping(Visit -> Visit.getTreatmentDone().getTreatmentId(), VisitVO::setTreatmentDoneId)
                .addMapping(Visit -> Visit.getClientVisited().getClientId(), VisitVO::setClientVisitedId)
                .addMapping(Visit -> Visit.getDoctorAccepted().getId(), VisitVO::setDoctorAcceptedId);

        modelMapper.createTypeMap(Client.class, ClientVO.class)
                .addMapping(Client -> Client.getAddress().getAddressId(), ClientVO::setAddressId)
                .addMapping(Client::getVisitsIds, ClientVO::setAllVisitsIds);

        modelMapper.createTypeMap(Doctor.class, DoctorVO.class)
                .addMapping(Doctor -> Doctor.getAddress().getAddressId(), DoctorVO::setAddressId)
                .addMapping(Doctor::getTreatmentsIds, DoctorVO::setAllTreatmentsIds)
                .addMapping(Doctor::getVisitsIds, DoctorVO::setAllVisitsIds);



        /*modelMapper.createTypeMap(Doctor.class, DoctorVO.class)
                .addMapping(Doctor -> Doctor.getAddress().getAddressId(), DoctorVO::setAddressId)
                .addMapping(Doctor -> Doctor.getAllTreatments()
                                            .stream()
                                            .map(Treatment::getTreatmentId)
                                            .collect(Collectors.toList()), DoctorVO::setAllTreatmentsIds)
                .addMapping(Doctor -> Doctor.getAllVisits()
                                            .stream()
                                            .map(Visit::getVisitId)
                                            .collect(Collectors.toList()), DoctorVO::setAllVisitsIds);*/

        // doctorTypeMap.setPropertyConverter(toVisitVo);
        // doctorTypeMap.setPropertyConverter(toTreatmentVo);


        return modelMapper;
    }

}
