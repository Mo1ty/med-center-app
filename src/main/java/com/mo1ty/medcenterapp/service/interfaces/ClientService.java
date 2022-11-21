package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.config.mapper.ClientVO;

import java.util.List;

public interface ClientService {

    ClientVO createClient(ClientVO clientVO);

    ClientVO updateClient(ClientVO clientVO);

    ClientVO findById(int id);

    void deleteClient(int id);

}
