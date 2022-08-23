package com.mo1ty.medcenterapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "price")
    private int price;

    @Column(name = "required_qualification")
    private int requiredQualification;

    @OneToMany(mappedBy = "serviceDone", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Visit> allVisits;

    public Service(ServiceType serviceTypeId, String serviceName, int price, int requiredQualification) {
        this.serviceType = serviceTypeId;
        this.serviceName = serviceName;
        this.price = price;
        this.requiredQualification = requiredQualification;
    }
}
