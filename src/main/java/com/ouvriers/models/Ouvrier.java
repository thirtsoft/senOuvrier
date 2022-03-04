package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ouvrier")
@Data
@NoArgsConstructor
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

    @Column(name = "sexe", length = 30)
    private String sexe;

    @Column(name = "addressActuel", length = 90)
    private String addressActuel;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "phoneOuvrier", length = 30)
    private String phoneOuvrier;

    @Column(name = "experience", length = 100)
    private String nbreAnneeExperience;

    @Column(name = "pretentionSalaire", length = 90)
    private double pretentionSalaire;

    @Column(name = "disponibility", length = 60)
    private String disponibity;

    private boolean selected;

    @Column(name = "mobilite", length = 100)
    private String mobilite;

    @Column(name = "cvOuvrier", length = 150)
    private String cvOuvrier;

    @Column(name = "photoOuvrier", length = 150)
    private String photoOuvrier;

    @ManyToOne
    @JoinColumn(name = "metierId")
    private Metier metier;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Addresse addresse;

    public Ouvrier(Long id, String reference, String firstName, String lastName, String sexe,
                   String addressActuel, String email, String phoneOuvrier,
                   String nbreAnneeExperience, double pretentionSalaire, String disponibity,
                   String mobilite, String photoOuvrier, String cvOuvrier, Metier metier, Addresse addresse) {
        this.id = id;
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.addressActuel = addressActuel;
        this.email = email;
        this.phoneOuvrier = phoneOuvrier;
        this.nbreAnneeExperience = nbreAnneeExperience;
        this.pretentionSalaire = pretentionSalaire;
        this.disponibity = disponibity;
        this.mobilite = mobilite;
        this.cvOuvrier = cvOuvrier;
        this.photoOuvrier = photoOuvrier;
        this.metier = metier;
        this.addresse = addresse;
    }
}
