package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "metier")
@AllArgsConstructor
@NoArgsConstructor
public class Metier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 50, unique = true)
    private String reference;

    @Column(name = "designation", length = 90)
    private String designation;

    @Column(name = "description", length = 200)
    @Lob
    private String description;


}
