package com.ouvriers.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name = "education", length = 60)
    private String education;

    @Column(name = "description", length = 60)
    private String description;

    private boolean selected;

    @Column(name = "mobilite", length = 100)
    private String mobilite;

    @Column(name = "cvOuvrier", length = 150)
    private String cvOuvrier;

    @Column(name = "photoOuvrier", length = 150)
    private String photoOuvrier;

    @Column(name = "dateInscription", length = 70)
    private Date dateInscription;

    private String subject;

    private String message;

    @ManyToOne
    @JoinColumn(name = "metierId")
    private Metier metier;

    @ManyToOne
    private Address address;

    public Ouvrier(Long id, String reference, String firstName, String lastName, String sexe,
                   String addressActuel, String email, String phoneOuvrier,
                   String nbreAnneeExperience, double pretentionSalaire, String disponibity,
                   String mobilite, String photoOuvrier, String cvOuvrier, Metier metier, Address address) {
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
        this.address = address;
    }

    public Ouvrier(Long id, String reference,
                   String firstName, String lastName, String sexe,
                   String addressActuel, String email, String phoneOuvrier,
                   String nbreAnneeExperience, double pretentionSalaire, String disponibity,
                   boolean selected, String mobilite, String cvOuvrier,
                   String photoOuvrier, Metier metier, Address address) {
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
        this.selected = selected;
        this.mobilite = mobilite;
        this.cvOuvrier = cvOuvrier;
        this.photoOuvrier = photoOuvrier;
        this.metier = metier;
        this.address = address;
    }
}
