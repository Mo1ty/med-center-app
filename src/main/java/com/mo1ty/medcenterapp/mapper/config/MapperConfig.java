package com.mo1ty.medcenterapp.mapper.config;

import com.mo1ty.medcenterapp.controller.exception.InvalidValuesInputException;
import com.mo1ty.medcenterapp.entity.*;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
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

    Converter<VisitVO, Visit> toVisit = new AbstractConverter<VisitVO, Visit>() {
        @Override
        protected Visit convert(VisitVO visitVO) {
            Visit visit = new Visit();

            visit.setVisitId(visitVO.getVisitId());
            visit.setTreatmentDone(treatmentRepository.findById(visitVO.getTreatmentDoneId()).orElse(null));
            visit.setClientVisited(clientRepository.findById(visitVO.getClientVisitedId()).orElse(null));
            visit.setDoctorAccepted(doctorRepository.findById(visitVO.getDoctorAcceptedId()).orElse(null));
            visit.setDate(visitVO.getDate());
            visit.setTime(visitVO.getTime());

            return visit;
        }
    };

    Converter<TreatmentVO, Treatment> toTreatment = new AbstractConverter<TreatmentVO, Treatment>() {
        @Override
        protected Treatment convert(TreatmentVO treatmentVO) {
            Treatment treatment = new Treatment();

            treatment.setTreatmentId(treatmentVO.getTreatmentId());
            treatment.setTreatmentName(treatmentVO.getTreatmentName());
            treatment.setPrice(treatmentVO.getPrice());
            treatment.setDoctors(doctorRepository.findAllById(treatmentVO.getDoctorsIds()));

            return treatment;
        }
    };

    Converter<ClientVO, Client> toClient = new AbstractConverter<ClientVO, Client>() {
        @Override
        protected Client convert(ClientVO clientVO) {
            Client client = new Client();

            client.setClientId(clientVO.getClientId());
            client.setFirstName(clientVO.getFirstName());
            client.setLastName(clientVO.getLastName());
            client.setEmail(clientVO.getEmail());
            client.setPassword(clientVO.getPassword());
            client.setAddress(addressRepository.findById(clientVO.getAddressId()).orElse(null));
            client.setAllVisits(visitsRepository.findAllById(clientVO.getAllVisitsIds()));

            return client;
        }
    };

    Converter<DoctorVO, Doctor> toDoctor = new AbstractConverter<DoctorVO, Doctor>() {
        @Override
        protected Doctor convert(DoctorVO doctorVO) {
            Doctor doc = new Doctor();

            doc.setId(doctorVO.getId());
            doc.setFirstName(doctorVO.getFirstName());
            doc.setLastName(doctorVO.getLastName());
            doc.setEmail(doctorVO.getEmail());
            doc.setAddress(addressRepository.findById(doctorVO.getAddressId()).orElse(null));
            doc.setAllTreatments(treatmentRepository.findAllById(doctorVO.getAllTreatmentsIds()));
            doc.setAllVisits(visitsRepository.findAllById(doctorVO.getAllVisitsIds()));

            return doc;
        }
    };

    /*
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
    */

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

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


        modelMapper.createTypeMap(DoctorVO.class, Doctor.class)
                .setConverter(toDoctor);
        modelMapper.createTypeMap(ClientVO.class, Client.class)
                .setConverter(toClient);
        modelMapper.createTypeMap(TreatmentVO.class, Treatment.class)
                .setConverter(toTreatment);
        modelMapper.createTypeMap(VisitVO.class, Visit.class)
                .setConverter(toVisit);

        /*TypeToken<List<Integer>> typeInt = new TypeToken<List<Integer>>() {};
        TypeToken<List<Treatment>> typeTreatment = new TypeToken<List<Treatment>>() {};
        TypeToken<List<Visit>> typeVisits = new TypeToken<List<Visit>>() {};
        TypeToken<List<Doctor>> typeDoctors = new TypeToken<List<Doctor>>(){};



        modelMapper.addConverter(toVisit, typeInt.getRawType(), typeVisits.getRawType());

        modelMapper.addConverter(toTreatments, typeInt.getRawType(), typeTreatment.getRawType());
        modelMapper.addConverter(toDoctors, typeInt.getRawType(), typeDoctors.getRawType());

        modelMapper.addConverter(toAddress, Integer.class, Address.class);
        modelMapper.addConverter(toTreatment, Integer.class, Treatment.class);
        modelMapper.addConverter(toClient, Integer.class, Client.class);*/



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
