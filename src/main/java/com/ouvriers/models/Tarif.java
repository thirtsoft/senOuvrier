package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tarif")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarif implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 50)
    private String reference;

    @Column(name = "montant", length = 50)
    private Double montantTarif;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "annonceId")
    private Annonce annonce;
}
