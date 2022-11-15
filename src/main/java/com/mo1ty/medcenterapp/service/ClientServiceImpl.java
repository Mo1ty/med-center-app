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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    ContactRepository contactRepository;
    LoyaltyLevelRepository loyaltyLevelRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ContactRepository contactRepository,
                             LoyaltyLevelRepository loyaltyLevelRepository){
        this.clientRepository = clientRepository;
        this.contactRepository = contactRepository;
        this.loyaltyLevelRepository = loyaltyLevelRepository;
    }

    // Provides no integrity check
    @Override
    public Client createClient(ClientVO clientVO) {
        Contact contact = this.contactRepository.getReferenceById(clientVO.getContactId());
        Client client = new Client(0, contact, 0, this.loyaltyLevelRepository.getReferenceById(0));
        return clientRepository.save(client);
    }

    // Provides no integrity check
    @Override
    public Client updateClient(ClientVO clientVO) {
        Contact contact = this.contactRepository.getReferenceById(clientVO.getContactId());
        Client client = new Client(clientVO.getId(), contact, clientVO.getId(), this.loyaltyLevelRepository.getReferenceById(0));

        return clientRepository.save(client);
    }

    @Override
    public Client findById(int id) {
        List<Client> clientList = clientRepository.findAllById(Collections.singletonList(id));

        if(clientList.size() == 1){
            return clientList.get(0);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    // Does not check if contact & visits are deleted with it
    @Override
    public void deleteClient(int id) {
        this.clientRepository.deleteById(id);
    }

}
