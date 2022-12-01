package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.config.mapper.ClientVO;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.entity.LoginData;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.repository.ClientRepository;
import com.mo1ty.medcenterapp.repository.ContactRepository;
import com.mo1ty.medcenterapp.repository.LoginDataRepository;
import com.mo1ty.medcenterapp.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginDataServiceImpl implements LoginDataService {

    LoginDataRepository loginDataRepository;
    AddressRepository addressRepository;
    ContactRepository contactRepository;
    ClientService clientService;
    LoyaltyLevelService loyaltyLevelService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public LoginDataServiceImpl(LoginDataRepository loginDataRepository, AddressRepository addressRepository,
                                ContactRepository contactRepository, PasswordEncoder passwordEncoder,
                                ClientService clientService, LoyaltyLevelService loyaltyLevelService) {
        this.loginDataRepository = loginDataRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
        this.loyaltyLevelService = loyaltyLevelService;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public ResponseEntity<String> registerUser(Contact contactData) {

        LoginData userData = contactData.getLoginData();
        Address addressData = contactData.getAddress();

        Client savedClient = null;
        Address savedAddress = null;
        LoginData savedUser = null;
        ResponseEntity response = null;

        try {
            // Setting LoginData and saving it to the database
            userData.setPassword(passwordEncoder.encode(userData.getPassword()));
            savedUser = loginDataRepository.save(userData);
            if(savedUser.getId() <= 0) {
                throw new RuntimeException("The entity of class \"LoginData\" was not created!");
            }
            else {
                contactData.setLoginData(savedUser);
            }

            // Setting Address and saving it to the database
            savedAddress = addressRepository.save(addressData);
            if(savedAddress.getId() <= 0) {
                throw new RuntimeException("The entity of class \"Address\" was not created!");
            }

            // Saving contact and creating client with LoginData & Address set before
            contactData = contactRepository.save(contactData);
            if(contactData.getId() > 0) {
                clientService.createClient(new ClientVO(0, contactData.getId(), 0, 1));

                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are registered!");
            }
        }

        catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An excepion occured! Message: " + ex.getLocalizedMessage());
        }
        return response;
    }
}
