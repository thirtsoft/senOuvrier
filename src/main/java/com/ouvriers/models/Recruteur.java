package com.ouvriers.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recruteur")
public class Recruteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", length = 90)
    private String firstName;

    @Column(name = "lastName", length = 70)
    private String lastName;

    @Column(name = "nomEntreprise", length = 90)
    private String nomEntreprise;

    @Column(name = "website", length = 90)
    private String website;

    @Column(name = "secteurActivite", length = 100)
    private String secteurActivite;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phoneRecruteur", length = 30)
    private String phoneRecruteur;

    @Column(name = "addressRecruteur", length = 30)
    private String addressRecruteur;

    @Column(name = "villeRecruteur", length = 30)
    private String villeRecruteur;

    @Lob
    @Column(name = "information")
    private String information;

}
