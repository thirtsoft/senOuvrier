package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "locality")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Locality extends PublicData {

    @Column(name = "rue", length = 70)
    private String rue;

    @Column(name = "codePostal", length = 70)
    private String codePostal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addresseId")
    private Address address;

}
