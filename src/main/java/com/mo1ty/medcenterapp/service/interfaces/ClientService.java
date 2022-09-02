package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.mapper.ClientVO;

import java.util.List;

public interface ClientService {

    ClientVO createClient(ClientVO client);

    ClientVO updateClient(ClientVO client);

    List<ClientVO> findAll();

    ClientVO findById(int clientId);

    void deleteClient(int clientId);

}
