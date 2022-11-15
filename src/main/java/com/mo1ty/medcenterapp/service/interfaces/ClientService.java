package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.config.mapper.ClientVO;

import java.util.List;

public interface ClientService {

    Client createClient(ClientVO clientVO);

    Client updateClient(ClientVO clientVO);

    Client findById(int id);

    void deleteClient(int id);

}
