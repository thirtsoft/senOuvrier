package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "serviceOffert")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOffert implements Serializable {

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

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "ouvId")
    private Ouvrier ouvrier;

    public ServiceOffert(Long id, String reference, String designation, Ouvrier ouvrier) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.ouvrier = ouvrier;
    }
}
