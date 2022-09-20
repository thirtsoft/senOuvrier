package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "metier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 50, unique = true)
    private String reference;

    @Column(name = "designation", length = 90)
    private String designation;

    @Column(name = "photoMetier", length = 90)
    private String photoMetier;

    @Column(name = "description", length = 200)
    @Lob
    private String description;

/*
    public Metier(Long id, String reference, String designation, String photoMetier) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.photoMetier = photoMetier;
    }
    */
}
