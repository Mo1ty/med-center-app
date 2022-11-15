package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Address;

public interface AddressService {

    Address createAddress(Address address);

    Address updateAddress(Address address);

    Address findById(int id);

    void deleteAddress(int id);
}
