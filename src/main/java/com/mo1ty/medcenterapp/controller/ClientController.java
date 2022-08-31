package com.mo1ty.medcenterapp.controller;


import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Doctor;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.mapper.DoctorVO;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public List<ClientVO> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/{clientId}")
    public ClientVO getClient(@PathVariable int clientId){
        ClientVO client = clientService.findById(clientId);

        if(client == null){
            throw new DataNotFoundException("Client with id " + clientId + " was not found!");
        }

        return client;
    }

    @PostMapping("")
    public Client addClient(@RequestBody ClientVO client){
        return clientService.createClient(client);
    }

    @PutMapping("")
    public Client updateClient(@RequestBody ClientVO client){
        ClientVO clnt = clientService.findById(client.getClientId());

        if(clnt == null){
            throw new DataNotPresentException("Requested client does not exist! Consider adding a new entity instead.");
        }

        return clientService.updateClient(client);
    }

    @DeleteMapping("/{clientId}")
    public ClientVO deleteClient(@PathVariable int clientId){

        // Will not execute if any visit has this client

        ClientVO client = clientService.findById(clientId);

        if(client == null){
            throw new DataNotPresentException("Requested client does not exist!");
        }

        clientService.deleteClient(clientId);

        return client;
    }

}
