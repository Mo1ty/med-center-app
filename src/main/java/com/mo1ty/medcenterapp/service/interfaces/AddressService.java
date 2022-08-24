package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Address;

import java.util.List;

public interface AddressService {

    void createOrUpdateAddress(Address address);

    List<Address> findAll();

    Address findById(int addressId);

    void deleteAddress(int addressId);

}
