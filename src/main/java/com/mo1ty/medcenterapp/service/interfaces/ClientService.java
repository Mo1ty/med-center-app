package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Client;

import java.util.List;

public interface ClientService {

    void createNewClient(Client client);

    List<Client> findAll();

    Client findById(int clientId);

    void updateClient(Client client, int clientId);

    void deleteClient(int clientId);

}
