package com.mo1ty.medcenterapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="services_types")
@Getter
@Setter
@NoArgsConstructor
public class ServiceType {

    // before continuing, check how are joins and relations shown in JPA & Java itself

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="service_type_id")
    private int serviceTypeId;

    @Column(name="service_type")
    private String serviceType;

    @Column(name="description")
    private String description;


    public ServiceType(String serviceType, String description) {
        this.serviceType = serviceType;
        this.description = description;
    }
}
