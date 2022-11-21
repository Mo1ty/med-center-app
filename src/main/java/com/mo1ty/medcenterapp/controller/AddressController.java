package com.mo1ty.medcenterapp.controller;

import com.mo1ty.medcenterapp.config.mapper.AddressVO;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/{addressId}")
    @PreAuthorize("hasAnyRole('CLIENT', 'DOCTOR', 'ADMIN')")
    public AddressVO getAddressById(@PathVariable int addressId){
        return addressService.findById(addressId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CLIENT', 'DOCTOR', 'ADMIN')")
    public AddressVO addAddress(@RequestBody AddressVO addressVO){
        return addressService.createAddress(addressVO);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('CLIENT', 'DOCTOR', 'ADMIN')")
    public AddressVO updateAddress(@RequestBody AddressVO addressVO){
        return addressService.updateAddress(addressVO);
    }

    @DeleteMapping("/{addressId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAddress(@PathVariable int addressId){
        addressService.deleteAddress(addressId);
    }

}
