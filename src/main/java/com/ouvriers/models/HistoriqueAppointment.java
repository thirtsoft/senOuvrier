package com.ouvriers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "historiqueAppointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueAppointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "appointId")
    private Appointment appointment;
}
