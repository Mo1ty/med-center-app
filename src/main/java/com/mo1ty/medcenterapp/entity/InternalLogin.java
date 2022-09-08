package com.mo1ty.medcenterapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="internal_logins")
public class InternalLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="internal_id")
    private int internalId;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String role;

    public InternalLogin(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
