package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.controller.exception.InvalidValuesInputException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.repository.interfaces.AddressRepository;
import com.mo1ty.medcenterapp.repository.interfaces.ClientRepository;
import com.mo1ty.medcenterapp.repository.interfaces.VisitsRepository;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    VisitsRepository visitsRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ClientVO createClient(ClientVO clientVO) {
        Optional<Address> addr = addressRepository.findById(clientVO.getAddressId());

        if(addr.isPresent()){
            Client client = modelMapper.map(clientVO, Client.class);
            clientRepository.save(client);
            return modelMapper.map(clientRepository.findById(client.getClientId()).orElse(null), ClientVO.class);

        }
        else{
            throw new InvalidValuesInputException("Address was not found in the database." +
                    "Check the data and try again");
        }
    }

    @Override
    public ClientVO updateClient(ClientVO clientVO) {
        Optional<Client> result = clientRepository.findById(clientVO.getClientId());
        List<Visit> visitsPresent = areVisitsPresent(clientVO.getAllVisitsIds());
        Optional<Address> addr = addressRepository.findById(clientVO.getAddressId());

        if(result.isPresent() && addr.isPresent() && visitsPresent != null){
            Client client = modelMapper.map(clientVO, Client.class);
            clientRepository.save(client);
            return modelMapper.map(clientRepository.findById(client.getClientId()).orElse(null), ClientVO.class);
        }
        else{
            throw new InvalidValuesInputException("Either address, or visits, or the entity itself were not in the database." +
                    "Check the data and try again");
        }
    }

    @Override
    public List<ClientVO> findAll() {

        List<Client> clients = clientRepository.findAll();

        if (clients.size() == 0){
            throw new DataNotPresentException("No clients were found in the table!");
        }

        List<ClientVO> clientVOList = new ArrayList<>();

        for(Client client : clients){
            clientVOList.add(modelMapper.map(client, ClientVO.class));
        }

        return clientVOList;
    }

    @Override
    public ClientVO findById(int clientId) {

        Optional<Client> result = clientRepository.findById(clientId);

        if(result.isPresent()){
            return modelMapper.map(result.get(), ClientVO.class);
        }
        else{
            throw new DataNotPresentException("Client with this id was not found!");
        }
    }

    @Override
    public void deleteClient(int clientId) {
        // Will not execute if any visit has this client

        Optional<Client> client = clientRepository.findById(clientId);

        if(client.isPresent()){
            clientRepository.deleteById(clientId);
        }
        else{
            throw new DataNotPresentException("Requested client does not exist!");
        }
    }

    public List<Visit> areVisitsPresent(List<Integer> visits){
        List<Visit> result = visitsRepository.findAllById(visits);
        if(result.size() == visits.size()){
            return result;
        }
        return null;
    }
}
