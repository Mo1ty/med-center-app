package com.mo1ty.medcenterapp.mapper.config;

import com.mo1ty.medcenterapp.entity.*;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.VisitVO;
import com.mo1ty.medcenterapp.repository.interfaces.*;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MapperConfig {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    VisitsRepository visitsRepository;

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

    Converter<Integer, Address> toAddress = new AbstractConverter<Integer, Address>() {
        @Override
        protected Address convert(Integer integer) {
            return addressRepository.findById(integer).orElse(null);
        }
    };

    Converter<Integer, Treatment> toTreatment = new AbstractConverter<Integer, Treatment>() {
        @Override
        protected Treatment convert(Integer integer) {
            return treatmentRepository.findById(integer).orElse(null);
        }
    };

    Converter<Integer, Client> toClient = new AbstractConverter<Integer, Client>() {
        @Override
        protected Client convert(Integer integer) {
            return clientRepository.findById(integer).orElse(null);
        }
    };

    Converter<Integer, Doctor> toDoctor = new AbstractConverter<Integer, Doctor>() {
        @Override
        protected Doctor convert(Integer integer) {
            return doctorRepository.findById(integer).orElse(null);
        }
    };

    Converter<List<Integer>, List<Treatment>> toTreatments = new AbstractConverter<List<Integer>, List<Treatment>>() {
        @Override
        protected List<Treatment> convert(List<Integer> integers) {
            List<Treatment> treatments = new ArrayList<>();

            for(int treatmentId : integers){
                treatments.add(treatmentRepository.findById(treatmentId).orElse(null));
            }
            return treatments;
        }
    };

    Converter<List<Integer>, List<Visit>> toVisit = new AbstractConverter<List<Integer>, List<Visit>>() {
        @Override
        protected List<Visit> convert(List<Integer> integers) {
            List<Visit> visits = new ArrayList<>();

            for(int visitId : integers){
                visits.add(visitsRepository.findById(visitId).orElse(null));
            }
            return visits;
        }
    };

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

        TypeToken<List<Integer>> typeInt = new TypeToken<List<Integer>>() {};
        TypeToken<List<Treatment>> typeTreatment = new TypeToken<List<Treatment>>() {};

        TypeToken<List<Visit>> typeVisits = new TypeToken<List<Visit>>() {};

        modelMapper.addConverter(toTreatments, typeInt.getRawType(), typeTreatment.getRawType());
        modelMapper.addConverter(toVisit, typeInt.getRawType(), typeVisits.getRawType());

        modelMapper.addConverter(toAddress);
        modelMapper.addConverter(toTreatment);
        modelMapper.addConverter(toClient);
        modelMapper.addConverter(toDoctor);



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
