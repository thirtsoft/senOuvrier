package com.ouvriers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends PublicData {

    @Column(name = "code")
    private String code; // EG

    @Column(name = "country", length = 30)
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private Set<Locality> localities;

    public Address(String code, String country) {
        this.code = code;
        this.country = country;
    }

}
