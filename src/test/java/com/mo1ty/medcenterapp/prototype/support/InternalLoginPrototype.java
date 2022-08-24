package com.mo1ty.medcenterapp.prototype.support;

import com.mo1ty.medcenterapp.entity.InternalLogin;

public class InternalLoginPrototype {

    public static InternalLogin createInternalLogin(){
        return new InternalLogin(1, "email_em@email.com",
                "{plain}batman", "ROLE_DOCTOR");
    }

}
