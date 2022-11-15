package com.mo1ty.medcenterapp.service.interfaces;

import com.mo1ty.medcenterapp.entity.Contact;
import com.mo1ty.medcenterapp.entity.LoginData;

public interface LoginDataService {

    LoginData createLoginData(LoginData loginData);

    LoginData updateLoginData(LoginData loginData);

    LoginData findById(int id);

    void deleteLoginData(int id);

}
