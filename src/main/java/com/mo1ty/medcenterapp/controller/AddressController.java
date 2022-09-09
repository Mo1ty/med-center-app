package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {


    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAllAddresses(){
        return addressService.findAll();
    }

    @GetMapping("/{addressId}")
    public Address getAddress(@PathVariable int addressId){
        return addressService.findById(addressId);
    }

    @PostMapping
    public Address addAddress(@RequestBody Address address){
        return addressService.createAddress(address);
    }

    @PutMapping
    public Address updateAddress(@RequestBody Address address){
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/{addressId}")
    public Address deleteAddress(@PathVariable int addressId){
        Address address = addressService.findById(addressId);
        addressService.deleteAddress(addressId);
        return address;
    }
}
