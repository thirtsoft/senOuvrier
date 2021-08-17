package com.ouvriers.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "whistList")
public class WhistList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 60)
    private String reference;

    @Column(name = "nbreEtoile", length = 60)
    private String nbreEtoile;

    @Column(name = "observation", length = 200)
    private String observation;

    @ManyToOne(fetch= FetchType.LAZY)
    private Ouvrier ouvrier;

/*
    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;
    */
}
