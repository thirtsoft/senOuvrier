package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "historiqueAnnonce")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriqueAnnonce implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "annonceId")
    private Annonce annonce;


}
