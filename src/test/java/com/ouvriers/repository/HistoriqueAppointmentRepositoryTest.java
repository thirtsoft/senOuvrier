package com.ouvriers.repository;

import com.ouvriers.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistoriqueAppointmentRepositoryTest {

    @Autowired
    private HistoriqueAppointmentRepository historiqueAppointmentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private OuvrierRepository ouvrierRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveHistoriqueAppointment() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName("Tairou");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setUsername("thir");
        utilisateurRepository.save(utilisateur);
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setFirstName("Ablaye");
        ouvrier.setLastName("Diagne");
        ouvrier.setDisponibity("Plein temps");
        ouvrier.setEmail("ouvrier@gmail.com");
        ouvrierRepository.save(ouvrier);
        Appointment appointment = new Appointment();
        appointment.setReference("APP01");
        appointment.setDescription("Appointment01");
        appointment.setAppointmentDate(new Date());
        appointment.setTime(LocalTime.parse("14:12"));
        appointment.setUtilisateur(utilisateur);
        appointment.setOuvrier(ouvrier);
        appointmentRepository.save(appointment);
        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setId(1L);
        historiqueAppointment.setAction("Ajouter");
        historiqueAppointment.setAppointment(appointment);
        historiqueAppointment.setCreatedDate(new Date());
        historiqueAppointmentRepository.save(historiqueAppointment);

        assertThat(historiqueAppointment.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getHistoriqueAppointmentByIdTest() {
        Optional<HistoriqueAppointment> optionalHistoriqueAppointment = historiqueAppointmentRepository.findById(1L);
        if (optionalHistoriqueAppointment.isPresent()) {
            assertThat(optionalHistoriqueAppointment.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateHistoriqueAppointmentTest() {
        Optional<HistoriqueAppointment> optionalHistoriqueAppointment = historiqueAppointmentRepository.findById(1L);
        if (optionalHistoriqueAppointment.isPresent()) {
            HistoriqueAppointment historiqueAppointment = optionalHistoriqueAppointment.get();
            historiqueAppointment.setAction("Update");

            HistoriqueAppointment historiqueAppointmentUpdated = historiqueAppointmentRepository.save(historiqueAppointment);

            Assertions.assertThat(historiqueAppointmentUpdated.getAction()).isEqualTo("Update");
        }
    }

    @Test
    @Order(4)
    public void getAllHistoriqueAppointmentsTest() {
        List<HistoriqueAppointment> historiqueAppointmentList = historiqueAppointmentRepository.findAll();
        assertThat(historiqueAppointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(5)
    public void getAllHistoriqueAppointmentsOrderByIdDescTest() {
        List<HistoriqueAppointment> historiqueAppointmentList = historiqueAppointmentRepository.findHistoriqueAppointmentByOrderByIdDesc();
        assertThat(historiqueAppointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteHistoriqueAppointmentTest() {
        Optional<HistoriqueAppointment> optionalHistoriqueAppointment = historiqueAppointmentRepository.findById(1L);
        if (optionalHistoriqueAppointment.isPresent()) {
            historiqueAppointmentRepository.delete(optionalHistoriqueAppointment.get());
        }
        HistoriqueAppointment historiqueAppointment = null;
        Optional<HistoriqueAppointment> optionalHistoriqueAppointment1 = historiqueAppointmentRepository.findById(1L);
        if (optionalHistoriqueAppointment1.isPresent()) {
            historiqueAppointment = optionalHistoriqueAppointment1.get();
        }
        Assertions.assertThat(historiqueAppointment).isNull();
    }




}
