package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.repository.interfaces.AddressRepository;
import com.mo1ty.medcenterapp.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public void createOrUpdateAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public List<Address> findAll() {

        List<Address> result = addressRepository.findAll();

        if (result.size() == 0){
            throw new DataNotFoundException("No addresses were found in the table!");
        }

        return result;
    }

    @Override
    public Address findById(int addressId) {

        Optional<Address> result = addressRepository.findById(addressId);

        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new DataNotFoundException("Address with this id was not found!");
        }
    }

    @Override
    public void deleteAddress(int addressId) {
        addressRepository.deleteById(addressId);
    }
}
