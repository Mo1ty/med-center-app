package com.mo1ty.medcenterapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="loyalty_levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoyaltyLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="loyalty_level_id")
    private int id;

    @Column(name="level_name")
    private String levelName;

    @Column(name="discount_percentage")
    private int discountPercentage;

    @Column(name="level_description")
    private String levelDescription;
}
