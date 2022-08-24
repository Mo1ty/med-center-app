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

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

}
