package com.ouvriers.models;

import com.ouvriers.enums.StatusAnnonce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "annonce")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Annonce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 50)
    private String reference;

    @Column(name = "libelle", length = 100)
    private String libelle;

    @Column(name = "lieuPoste", length = 90)
    private String lieuPoste;

    @Column(name = "salaire", length = 90)
    private String salaire;

    @Column(name = "emailPoste", length = 90)
    private String emailPoste;

    @Column(name = "time", length = 50)
    private String time;

    @Column(name = "anneeExperience")
    private int anneeExperience;

    @Column(name = "description", length = 200)
    @Lob
    private String description;

    @Column(name = "createdDate", length = 70)
    private Date createdDate;

    @Column(name = "dateCloture", length = 70)
    private Date dateCloture;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 90)
    private StatusAnnonce statusAnnonce;

    @ManyToOne
    @JoinColumn(name = "metierId")
    private Metier metier;

    @ManyToOne
    @JoinColumn(name = "recrId")
    private Recruteur recruteur;

    @ManyToOne
    @JoinColumn(name = "villeId")
    private Ville ville;

}
