package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Contact;

public interface ContactService {

    Contact createContact(Contact contact);

    Contact updateContact(Contact contact);

    Contact findById(int id);

    void deleteContact(int id);

}
