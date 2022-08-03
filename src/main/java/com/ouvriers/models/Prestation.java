package com.ouvriers.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "prestation")
@Data
@NoArgsConstructor
public class Prestation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "ouvId", nullable = false)
    private Ouvrier ouvrier;


}
