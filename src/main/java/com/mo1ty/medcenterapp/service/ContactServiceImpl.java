package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.config.mapper.ContactVO;
import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.repository.ContactRepository;
import com.mo1ty.medcenterapp.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    AddressRepository addressRepository;
    ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(AddressRepository addressRepository, ContactRepository contactRepository){
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact createContact(ContactVO contactVO) {
        Address address = this.addressRepository.getReferenceById(contactVO.getAddressId());
        Contact contact = new Contact(0, contactVO.getFirstName(), contactVO.getLastName(), contactVO.getPhoneNumber(), address);
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(ContactVO contactVO) {
        Address address = this.addressRepository.getReferenceById(contactVO.getAddressId());
        Contact contact = new Contact(contactVO.getId(), contactVO.getFirstName(), contactVO.getLastName(), contactVO.getPhoneNumber(), address);
        return contactRepository.save(contact);
    }

    @Override
    public Contact findById(int id) {
        List<Contact> contactList = contactRepository.findAllById(Collections.singletonList(id));
        if(contactList.size() == 1){
            return contactList.get(0);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    @Override
    public void deleteContact(int id) {
        this.contactRepository.deleteById(id);
    }
}
