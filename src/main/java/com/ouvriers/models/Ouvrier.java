package com.ouvriers.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ouvrier")
public class Ouvrier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 50)
    private String reference;

    @Column(name = "firstName", length = 90)
    private String firstName;

    @Column(name = "lastName", length = 70)
    private String lastName;

    @Column(name = "sexe", length = 70)
    private String sexe;

    @Column(name = "addressActuel", length = 90)
    private String addressActuel;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phoneOuvrier", length = 30)
    private String phoneOuvrier;

    @Column(name = "experience", length = 50)
    private Integer nbreAnneeExperience;

    @Column(name = "pretentionSalaire", length = 90)
    private Double pretentionSalaire;

    @Column(name = "mobilite", length = 100)
    private String mobilite;

    @Column(name = "disponibility", length = 20)
    private String disponibity;

    @Column(name = "cvOuvrier", length = 30)
    private String cvOuvrier;

    @Column(name = "photoOuvrier", length = 30)
    private String photoOuvrier;

    @ManyToOne
    @JoinColumn(name = "metierId")
    private Metier metier;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Addresse addresse;

}
