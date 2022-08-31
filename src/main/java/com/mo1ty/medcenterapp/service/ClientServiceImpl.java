package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.repository.interfaces.ClientRepository;
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
    ModelMapper modelMapper;

    @Override
    public Client createClient(ClientVO client) {
        return clientRepository.save(modelMapper.map(client, Client.class));
    }

    @Override
    public Client updateClient(ClientVO client) {
        Optional<Client> result = clientRepository.findById(client.getClientId());

        if(result.isPresent()){
            Client clnt = modelMapper.map(client, Client.class);
            clientRepository.save(clnt);
            return clientRepository.findById(clnt.getClientId()).orElse(null);
        }
        else{
            return null;
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
        clientRepository.deleteById(clientId);
    }
}
