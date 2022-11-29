package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Contact;
import org.springframework.http.ResponseEntity;

public interface LoginDataService {

    ResponseEntity<String> registerUser(Contact contactData);

}
