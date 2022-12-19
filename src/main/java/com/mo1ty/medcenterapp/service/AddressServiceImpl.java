package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.config.mapper.AddressVO;
import com.mo1ty.medcenterapp.controller.error.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.error.exception.InvalidInputException;
import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.repository.AddressRepository;
import com.mo1ty.medcenterapp.service.interfaces.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressVO createAddress(AddressVO addressVO) {
        Address address = modelMapper.map(addressVO, Address.class);
        addressRepository.save(address);
        return modelMapper.map(addressRepository.findById(address.getId()).orElse(null), AddressVO.class);
    }

    @Override
    public AddressVO updateAddress(AddressVO addressVO) {
        Optional<Address> result = addressRepository.findById(addressVO.getId());
        if(result.isPresent()) {
            Address address = modelMapper.map(addressVO, Address.class);
            addressRepository.save(address);
            return modelMapper.map(addressRepository.findById(address.getId()).orElse(null), AddressVO.class);
        }
        else
            throw new InvalidInputException("Address with this id does not exist!");
    }

    @Override
    public AddressVO findById(int addressId) {
        Optional<Address> result = addressRepository.findById(addressId);
        if(result.isPresent()) {
            return modelMapper.map(result.get(), AddressVO.class);
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
