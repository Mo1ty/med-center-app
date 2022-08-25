package com.mo1ty.medcenterapp.prototype.support;

import com.mo1ty.medcenterapp.entity.InternalLogin;

public class InternalLoginPrototype {

    public static InternalLogin createInternalLogin(String email){
        return new InternalLogin(email,
                "{plain}batman", "ROLE_DOCTOR");
    }

}
