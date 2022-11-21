package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.config.mapper.ClientVO;
import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.entity.LoyaltyLevel;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.ContactRepository;
import com.mo1ty.medcenterapp.repository.LoyaltyLevelRepository;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    ContactRepository contactRepository;
    LoyaltyLevelRepository loyaltyLevelRepository;
    ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ContactRepository contactRepository,
                             LoyaltyLevelRepository loyaltyLevelRepository, ModelMapper modelMapper){
        this.clientRepository = clientRepository;
        this.contactRepository = contactRepository;
        this.loyaltyLevelRepository = loyaltyLevelRepository;
        this.modelMapper = modelMapper;
    }

    // Provides no integrity check
    @Override
    public ClientVO createClient(ClientVO clientVO) {
        Contact contact = this.contactRepository.getReferenceById(clientVO.getContactId());
        Client client = new Client(0, contact, 0, this.loyaltyLevelRepository.getReferenceById(1));
        clientRepository.save(client);
        return modelMapper.map(client, ClientVO.class);
    }

    // Provides no integrity check
    @Override
    public ClientVO updateClient(ClientVO clientVO) {
        Contact contact = this.contactRepository.getReferenceById(clientVO.getContactId());
        Client client = new Client(clientVO.getId(), contact, clientVO.getTotalSpent(), this.loyaltyLevelRepository.getReferenceById(clientVO.getId()));
        clientRepository.save(client);
        return modelMapper.map(client, ClientVO.class);
    }

    @Override
    public ClientVO findById(int id) {
        List<Client> clientList = clientRepository.findAllById(Collections.singletonList(id));

        if(clientList.size() == 1){
            return modelMapper.map(clientList.get(0), ClientVO.class);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    // Does not check if contact & visits are deleted with it
    @Override
    public void deleteClient(int id) {
        this.clientRepository.deleteById(id);
    }

}
