package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.config.mapper.ContactVO;
import com.mo1ty.medcenterapp.entity.Contact;

public interface ContactService {

    ContactVO createContact(ContactVO contact);

    ContactVO updateContact(ContactVO contact);

    ContactVO findById(int id);

    void deleteContact(int id);

}
