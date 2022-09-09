package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.mapper.ClientVO;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientVO> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/{clientId}")
    public ClientVO getClient(@PathVariable int clientId){
        return clientService.findById(clientId);
    }

    @PostMapping
    public ClientVO addClient(@RequestBody ClientVO client){
        return clientService.createClient(client);
    }

    @PutMapping
    public ClientVO updateClient(@RequestBody ClientVO client){
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable int clientId){

        // Will not execute if any visit has this client, fix later
        clientService.deleteClient(clientId);

        return new ResponseEntity<>("200: The entity was successfully deleted!", HttpStatus.OK);
    }

}
