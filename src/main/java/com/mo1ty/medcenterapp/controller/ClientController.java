package com.mo1ty.medcenterapp.controller;


import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("")
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable int clientId){
        Client client = clientService.findById(clientId);

        if(client == null){
            throw new DataNotFoundException("Address with id " + clientId + " was not found!");
        }

        return client;
    }

    @PostMapping("")
    public Client addClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @PutMapping("")
    public Client updateClient(@RequestBody Client client){
        Client clnt = clientService.findById(client.getClientId());

        if(clnt == null){
            throw new DataNotFoundException("Requested client was not found!");
        }

        return clientService.updateClient(client);
    }

    @DeleteMapping("/{clientId}")
    public Client deleteClient(@PathVariable int clientId){

        // Will not execute if any client/doctor has an address

        Client client = clientService.findById(clientId);

        if(client == null){
            throw new DataNotFoundException("Requested address was not found!");
        }

        clientService.deleteClient(clientId);

        return client;
    }

}
