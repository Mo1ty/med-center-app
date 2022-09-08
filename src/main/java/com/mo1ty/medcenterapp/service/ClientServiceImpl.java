package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.controller.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Visit;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.VisitsRepository;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private AddressRepository addressRepository;
    private VisitsRepository visitsRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository, VisitsRepository visitsRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.visitsRepository = visitsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientVO createClient(ClientVO clientVO) {

        Optional<Address> addr = addressRepository.findById(clientVO.getAddressId());

        if(addr.isPresent()){
            Address address = addr.get();
            Client client = new Client();
            client.setAddress(address);
            modelMapper.map(clientVO, client);
            clientRepository.save(client);
            return modelMapper.map(clientRepository.findById(client.getClientId()).orElse(null), ClientVO.class);

        }
        else{
            throw new InvalidInputException("Address was not found in the database." +
                    "Check the data and try again");
        }
    }

    @Override
    public ClientVO updateClient(ClientVO clientVO) {
        Optional<Client> result = clientRepository.findById(clientVO.getClientId());
        Optional<Address> addr = addressRepository.findById(clientVO.getAddressId());

        if(result.isPresent() && addr.isPresent()){
            Client client = result.get();
            client.setAddress(addr.get());
            modelMapper.map(clientVO, client);
            clientRepository.save(client);
            return modelMapper.map(clientRepository.findById(client.getClientId()).orElse(null), ClientVO.class);
        }
        else{
            throw new InvalidInputException("Either address, or visits, or the entity itself were not in the database." +
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
