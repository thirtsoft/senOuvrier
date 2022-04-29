package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "newsleter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Newsletter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emailVisiteur")
    private String emailVisiteur;

    @Column(name = "createdDate")
    private Date createdDate;

    private String subject;

    private String message;
}
