package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.InternalLogin;

import java.util.List;

public interface InternalLoginService {

    List<InternalLogin> findAll();

    InternalLogin findById(int internalLoginId);

    InternalLogin findByEmail(String email);

    InternalLogin createInternalLogin(InternalLogin internalLogin);

    InternalLogin updateInternalLogin(InternalLogin internalLogin);

    void deleteInternalLogin(int internalLoginId);

}