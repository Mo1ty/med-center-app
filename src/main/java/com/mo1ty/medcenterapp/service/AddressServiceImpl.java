package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address createAddress(Address address) {
        // It is meant to be called on address objects with id = 0 (null/unassigned)
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        Optional<Address> result = addressRepository.findById(address.getAddressId());

        if(result.isPresent()){
            addressRepository.save(address);
            return address;
        }
        else{
            throw new DataNotFoundException("Address with this id does not exist!");
        }
    }

    @Override
    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    @Override
    public Address findById(int addressId) {

        Optional<Address> result = addressRepository.findById(addressId);

        return result.orElse(null);
    }

    @Override
    public void deleteAddress(int addressId) {
        addressRepository.deleteById(addressId);
    }
}
