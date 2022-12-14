package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.config.mapper.ClientVO;
import com.mo1ty.medcenterapp.config.mapper.ContactVO;
import com.mo1ty.medcenterapp.entity.Client;
import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.service.interfaces.ClientService;
import com.mo1ty.medcenterapp.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ContactVO createContact(ContactVO contact) {
        return contactService.createContact(contact);
    }

    @PutMapping
    public ContactVO updateContact(ContactVO contact) {
        return contactService.updateContact(contact);
    }


    @GetMapping("/{id}")
    public ContactVO findById(@PathVariable int id) {
        return contactService.findById(id);
    }

    @GetMapping("/personal/{loginId}")
    public ContactVO findByLoginId(@PathVariable int loginId) {
        return contactService.findByLoginId(loginId);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable int id) {
        contactService.deleteContact(id);
    }

}
