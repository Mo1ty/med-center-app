package com.mo1ty.medcenterapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private int houseNumber;

    public Address(String city, String postalCode, String street, int houseNumber) {
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
