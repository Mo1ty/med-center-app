package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.config.mapper.ContactVO;
import com.mo1ty.medcenterapp.entity.Contact;

public interface ContactService {

    Contact createContact(ContactVO contact);

    Contact updateContact(ContactVO contact);

    Contact findById(int id);

    void deleteContact(int id);

}
