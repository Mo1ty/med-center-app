package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.config.mapper.ContactVO;
import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.entity.LoginData;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.repository.ContactRepository;
import com.mo1ty.medcenterapp.repository.LoginDataRepository;
import com.mo1ty.medcenterapp.service.interfaces.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Collections;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    AddressRepository addressRepository;
    ContactRepository contactRepository;
    LoginDataRepository loginDataRepository;

    ModelMapper modelMapper;

    @Autowired
    public ContactServiceImpl(AddressRepository addressRepository, ContactRepository contactRepository,
                              LoginDataRepository loginDataRepository, ModelMapper modelMapper){
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.loginDataRepository = loginDataRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactVO createContact(ContactVO contactVO) {
        Address address = this.addressRepository.getReferenceById(contactVO.getAddressId());
        LoginData loginData = loginDataRepository.getReferenceById(contactVO.getLoginDataId());
        try {
            Contact contact = new Contact(0, loginData, contactVO.getFirstName(), contactVO.getLastName(), contactVO.getPhoneNumber(), address);
            return modelMapper.map(contactRepository.save(contact), ContactVO.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ContactVO updateContact(ContactVO contactVO) {
        Address address = null;
        if(contactVO.getAddressId() != 0)
            address = this.addressRepository.getReferenceById(contactVO.getAddressId());
        LoginData loginData = loginDataRepository.getReferenceById(contactVO.getLoginDataId());
        try {
            Contact contact = new Contact(0, loginData, contactVO.getFirstName(), contactVO.getLastName(), contactVO.getPhoneNumber(), address);
            return modelMapper.map(contactRepository.save(contact), ContactVO.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ContactVO findById(int id) {
        List<Contact> contactList = contactRepository.findAllById(Collections.singletonList(id));
        if(contactList.size() == 1){
            return modelMapper.map(contactList.get(0), ContactVO.class);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    @Override
    public ContactVO findByLoginId(int loginId) {
        List<Contact> contactList = contactRepository.findAllByLoginId(loginId);
        if(contactList.size() == 1){
            return modelMapper.map(contactList.get(0), ContactVO.class);
        }
        throw new DataNotFoundException("Entity with this ID was not found in database!");
    }

    @Override
    public void deleteContact(int id) {
        this.contactRepository.deleteById(id);
    }
}
