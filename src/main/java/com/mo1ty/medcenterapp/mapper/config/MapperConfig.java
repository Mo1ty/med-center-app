package com.mo1ty.medcenterapp.mapper.config;

import com.mo1ty.medcenterapp.entity.*;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.mapper.TreatmentVO;
import com.mo1ty.medcenterapp.mapper.VisitVO;
import com.mo1ty.medcenterapp.repository.*;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    * Setting converters for turning Value Objects to POJOs.
    * They will be set as converters for the Type Maps.
    * The opposite process does not require container usage,
    * was fully made through addMapping and set in the bean.
     */

    Converter<VisitVO, Visit> toVisit = new AbstractConverter<VisitVO, Visit>() {
        @Override
        protected Visit convert(VisitVO visitVO) {
            Visit visit = new Visit();

            visit.setVisitId(visitVO.getVisitId());
            visit.setTreatmentDone(treatmentRepository.findById(visitVO.getTreatmentDoneId()).orElse(null));
            visit.setClientVisited(clientRepository.findById(visitVO.getClientVisitedId()).orElse(null));
            visit.setDoctorAccepted(doctorRepository.findById(visitVO.getDoctorAcceptedId()).orElse(null));
            visit.setDatetime(visitVO.getDatetime());

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

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Create Type Maps for turning POJOs to VOs.

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

        // Create Type Maps for turning VOs to POJOs.

        modelMapper.createTypeMap(DoctorVO.class, Doctor.class)
                .setConverter(toDoctor);
        modelMapper.createTypeMap(ClientVO.class, Client.class)
                .setConverter(toClient);
        modelMapper.createTypeMap(TreatmentVO.class, Treatment.class)
                .setConverter(toTreatment);
        modelMapper.createTypeMap(VisitVO.class, Visit.class)
                .setConverter(toVisit);


        return modelMapper;
    }

}
