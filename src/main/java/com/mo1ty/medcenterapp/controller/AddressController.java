package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("")
    public List<Address> getAllAddresses(){
        return addressService.findAll();
    }

    @GetMapping("/{addressId}")
    public Address getAddress(@PathVariable int addressId){
        Address address = addressService.findById(addressId);

        if(address == null){
            throw new DataNotFoundException("Address with id " + addressId + " was not found!");
        }

        return address;
    }

    @PostMapping("")
    public Address addAddress(@RequestBody Address address){
        return addressService.createAddress(address);
    }

    @PutMapping("")
    public Address updateAddress(@RequestBody Address address){
        Address addr = addressService.findById(address.getAddressId());

        if(addr == null){
            throw new DataNotFoundException("Requested address was not found!");
        }

        return addressService.updateAddress(address);
    }

    @DeleteMapping("/{addressId}")
    public Address deleteAddress(@PathVariable int addressId){

        // Will not execute if any client/doctor has an address

        Address addr = addressService.findById(addressId);

        if(addr == null){
            throw new DataNotFoundException("Requested address was not found!");
        }

        addressService.deleteAddress(addressId);

        return addr;
    }
}