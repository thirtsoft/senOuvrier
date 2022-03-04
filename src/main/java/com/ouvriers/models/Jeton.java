package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "jeton")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jeton implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "montant")
    private float montant;

    @Column(name = "etat")
    private String etat;

    @Column(name = "createdDate")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;

}
