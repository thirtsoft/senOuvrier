package com.ouvriers.repository;


import com.ouvriers.models.Appointment;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Utilisateur;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private OuvrierRepository ouvrierRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveAppointmentTest() {
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

        assertThat(appointment.getId()).isGreaterThan(0);

        assertThat(appointment.getReference()).isEqualTo("APP01");
    }

    @Test
    @Order(2)
    public void getAppointmentByIdTest() {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(1L);
        if (appointmentOptional.isPresent()) {
            assertThat(appointmentOptional.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateAppointmentTest() {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(1L);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            appointment.setReference("APP02");

            Appointment appointmentUpdated = appointmentRepository.save(appointment);

            Assertions.assertThat(appointmentUpdated.getReference()).isEqualTo("APP02");
        }
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateStatutOfAppointmentTest() {
        String statusOfAppointment = "Validated";
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(1L);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            appointment.setStatusOfAppointment(statusOfAppointment);

            Appointment appointmentUpdated = appointmentRepository.save(appointment);

            Assertions.assertThat(appointmentUpdated.getStatusOfAppointment()).isEqualTo("Validated");
        }
    }

    @Test
    @Order(5)
    public void getAppointmentByReferenceTest() {
        String reference = "APP03";
        Optional<Appointment> appointmentOptional = appointmentRepository.findByReference(reference);
        if (appointmentOptional.isPresent()) {
            assertThat(appointmentOptional.get()).isNotNull();
        }
    }

    @Test
    @Order(6)
    public void getNumberOfAppointmentByTest() {
        long number = appointmentRepository.count();
        assertThat(number).isNotNull();
    }

    @Test
    @Order(7)
    public void getNumberOfAppointmentsInMonthTest() {
        BigDecimal number = appointmentRepository.countNumberOfAppointmentsInMonth();
        BigDecimal val = BigDecimal.valueOf(2000000.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(8)
    public void getNumberOfPendingAppointmentsTest() {
        BigDecimal number = appointmentRepository.countNumberOfAppointmentByStatusPending();
        BigDecimal val = BigDecimal.valueOf(2000000.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(9)
    public void getNumberOfAppointmentByStatusAcceptedTest() {
        BigDecimal number = appointmentRepository.countNumberOfAppointmentByStatusPending();
        BigDecimal val = BigDecimal.valueOf(200.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(10)
    public void getNumberOfAcceptedAppointmentInYearTest() {
        BigDecimal number = appointmentRepository.countNumberOfAppointmentByStatusPending();
        BigDecimal val = BigDecimal.valueOf(250000.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(11)
    public void getNumberOfAppointmentByOuvrierIdTest() {
        Long ouvId = null;
        BigDecimal number = appointmentRepository.countNumberOfAppointmentByOuvrierId(ouvId);
        BigDecimal val = BigDecimal.valueOf(25000000.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(12)
    public void getNumberOfAppointmentByCustomerIdTest() {
        Long customerId = null;
        BigDecimal number = appointmentRepository.countNumberOfAppointmentByCustomerId(customerId);
        BigDecimal val = BigDecimal.valueOf(25000000.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(13)
    public void getNumberOfAppointmentByCustomerIdAndStatusAcceptedTest() {
        Long customerId = null;
        BigDecimal number = appointmentRepository.countNumberOfAppointmentByCustomerIdAndStatusAccepted(customerId);
        BigDecimal val = BigDecimal.valueOf(25000000.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(14)
    public void getAllAppointmentsTest() {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(15)
    public void getAllAppointmentsByIdDescTest() {
        List<Appointment> appointmentList = appointmentRepository.findByOrderByIdDesc();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(16)
    public void getAllAppointmentsByCustomerIdTest() {
        Long customerId = 1L;
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByUserId(customerId);
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(17)
    public void getAllAppointmentsByOuvrierIdTest() {
        Long ouvId = 1L;
        List<Appointment> appointmentList = appointmentRepository.findAppointmentByOuvrierId(ouvId);
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(18)
    public void getTop4AppointmentOrderByCreatedDateDescIdTest() {
        Long ouvId = 1L;
        List<Appointment> appointmentList = appointmentRepository.findTop4AppointmentOrderByCreatedDateDesc(ouvId);
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(19)
    public void getTop10AppointmentOrderByCreatedDateDescIdTest() {
        List<Appointment> appointmentList = appointmentRepository.findTop10PendingAppointmentOrderByCreatedDateDesc();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(20)
    public void getTop30AppointmentOrderByCreatedDateDescIdTest() {
        List<Appointment> appointmentList = appointmentRepository.findTop30ByOrderByIdDesc();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(21)
    public void getListAppointmentsByStatusPendingTest() {
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByStatusWaiting();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(22)
    public void getListAppointmentsByStatusAcceptedTest() {
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByStatusAccepted();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(23)
    public void getListAppointmentsByStatusRefusedTest() {
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByStatusRefused();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(24)
    public void getNumberOfAppointmentsPeerMonthTest() {
        List<?> appointmentList = appointmentRepository.countNumberOfAppointmentByMonth();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(25)
    public void getNumberOfAppointmentsPeerYearTest() {
        List<?> appointmentList = appointmentRepository.countNumberOfAppointmentByYear();
        assertThat(appointmentList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(26)
    @Rollback(value = false)
    public void deleteAppointmentTest() {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(1L);
        if (appointmentOptional.isPresent()) {
            appointmentRepository.delete(appointmentOptional.get());
        }
        Appointment appointment = null;
        Optional<Appointment> appointmentOptional1 = appointmentRepository.findById(1L);
        if (appointmentOptional1.isPresent()) {
            appointment = appointmentOptional1.get();
        }
        Assertions.assertThat(appointment).isNull();
    }


}
