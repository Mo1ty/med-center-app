package com.mo1ty.medcenterapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private int id;

    @Column(name="city")
    private String city;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="street")
    private String street;

    @Column(name="house_number")
    private int houseNumber;

    @OneToMany(
            mappedBy = "address",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JsonIgnore
    private List<Contact> persons;

}
