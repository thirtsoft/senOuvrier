package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nbreEtoile", length = 60)
    private float nbreEtoile;

    @Column(name = "observation")
    @Lob
    private String observation;

    @Column(name = "createdDate")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "ouvId")
    private Ouvrier ouvrier;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;
}
