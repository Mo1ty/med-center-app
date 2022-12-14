package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.config.mapper.ClientVO;
import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.ContactRepository;
import com.mo1ty.medcenterapp.repository.LoyaltyLevelRepository;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientVO createClient(ClientVO clientVO) {
        return clientService.createClient(clientVO);
    }

    @PutMapping
    public ClientVO updateClient(ClientVO clientVO) {
        return clientService.updateClient(clientVO);
    }

    @GetMapping("/{id}")
    public ClientVO findById(@PathVariable int id) {
        return clientService.findById(id);
    }

    @GetMapping("/by-contact/{id}")
    public ClientVO findByContactId(@PathVariable int id) {
        return clientService.findByContactId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/by_login/{id}")
    public ClientVO findByLoginId(@PathVariable int id) {
        return clientService.findByLoginId(id);
    }

}
