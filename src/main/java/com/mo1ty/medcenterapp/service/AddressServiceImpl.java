package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.error.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        Optional<Address> result = addressRepository.findById(address.getId());
        if(result.isPresent())
            return addressRepository.save(address);
        else
            throw new InvalidInputException("Address with this id does not exist!");
    }

    @Override
    public Address findById(int addressId) {
        Optional<Address> result = addressRepository.findById(addressId);
        if(result.isPresent()) {
            return result.get();
        }
        else {
            throw new DataNotFoundException("Address with id " + addressId + " was not found!");
        }
    }

    @Override
    public void deleteAddress(int addressId) {
        Optional<Address> address = addressRepository.findById(addressId);
        if(address.isPresent()){
            addressRepository.deleteById(addressId);
        }
        else {
            throw new DataNotFoundException("Requested address is not present in the database!");
        }
    }
}
