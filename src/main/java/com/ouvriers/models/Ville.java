package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ville")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ville implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 60)
    private String reference;

    @Column(name = "nom", length = 60)
    private String nom;

    @Column(name = "pays", length = 60)
    private String pays;
}
