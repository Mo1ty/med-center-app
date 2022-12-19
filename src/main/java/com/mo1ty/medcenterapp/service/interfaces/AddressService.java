package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.config.mapper.AddressVO;
import com.mo1ty.medcenterapp.entity.Address;

public interface AddressService {

    AddressVO createAddress(AddressVO address);

    AddressVO updateAddress(AddressVO address);

    AddressVO findById(int id);

    void deleteAddress(int id);
}
