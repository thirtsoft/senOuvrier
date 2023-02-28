package com.ouvriers.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;

    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    private LocalTime time;

    private String statusOfAppointment="Encours";

    private String description;

    @ManyToOne
    @JoinColumn(name = "ouvId", nullable = false)
 //   @JsonProperty("ouvId")
    private Ouvrier ouvrier;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
  //  @JsonProperty("userId")
    private Utilisateur utilisateur;

}
