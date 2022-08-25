package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.repository.interfaces.ClientRepository;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void createOrUpdateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {

        List<Client> result = clientRepository.findAll();

        if (result.size() == 0){
            throw new DataNotFoundException("No clients were found in the table!");
        }

        return result;
    }

    @Override
    public Client findById(int clientId) {

        Optional<Client> result = clientRepository.findById(clientId);

        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new DataNotFoundException("Client with this id was not found!");
        }
    }

    @Override
    public void deleteClient(int clientId) {
        clientRepository.deleteById(clientId);
    }
}
