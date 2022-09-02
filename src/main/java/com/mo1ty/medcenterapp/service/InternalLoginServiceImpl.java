package com.mo1ty.medcenterapp.service;

import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.entity.InternalLogin;
import com.mo1ty.medcenterapp.repository.InternalLoginRepository;
import com.mo1ty.medcenterapp.service.interfaces.InternalLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternalLoginServiceImpl implements InternalLoginService {

    @Autowired
    InternalLoginRepository internalLoginRepository;

    @Override
    public List<InternalLogin> findAll() {
        return internalLoginRepository.findAll();
    }

    @Override
    public InternalLogin findById(int internalLoginId) {
        Optional<InternalLogin> result = internalLoginRepository.findById(internalLoginId);

        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new DataNotFoundException("Internal login with this id was not found!");
        }
    }

    @Override
    public InternalLogin createInternalLogin(InternalLogin internalLogin) {
        return internalLoginRepository.save(internalLogin);
    }

    @Override
    public InternalLogin updateInternalLogin(InternalLogin internalLogin) {
        Optional<InternalLogin> result =
                internalLoginRepository.findById(internalLogin.getInternalId());

        if(result.isPresent()){
            internalLoginRepository.save(internalLogin);
            return internalLogin;
        }
        else{
            throw new DataNotPresentException("Internal login with this id was not present!");
        }
    }

    @Override
    public void deleteInternalLogin(int internalLoginId) {
        internalLoginRepository.deleteById(internalLoginId);
    }

}
