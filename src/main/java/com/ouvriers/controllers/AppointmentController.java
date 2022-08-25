package com.ouvriers.controllers;

import com.ouvriers.controllers.api.AppointmentApi;
import com.ouvriers.models.Appointment;
import com.ouvriers.models.HistoriqueAppointment;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.services.AppointmentService;
import com.ouvriers.services.HistoriqueAppointmentService;
import com.ouvriers.services.OuvrierService;
import com.ouvriers.services.UtilisateurService;
import com.ouvriers.utils.GenerateCode;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AppointmentController implements AppointmentApi {

    private final AppointmentService appointmentService;

    private final OuvrierService ouvrierService;

    private final UtilisateurService utilisateurService;

    private final HistoriqueAppointmentService historiqueAppointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,
                                 OuvrierService ouvrierService,
                                 UtilisateurService utilisateurService,
                                 HistoriqueAppointmentService historiqueAppointmentService) {
        this.appointmentService = appointmentService;
        this.ouvrierService = ouvrierService;
        this.utilisateurService = utilisateurService;
        this.historiqueAppointmentService = historiqueAppointmentService;
    }

    @Override
    public ResponseEntity<Appointment> create(Appointment appointment) {
        appointment.setStatusOfAppointment("Encours");
        appointment.setReference(GenerateCode.generateNumberOfCode());
        appointment.setCreatedDate(new Date());
        Appointment appointmentResult = appointmentService.save(appointment);

        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setAppointment(appointmentResult);
        historiqueAppointment.setAction("AJOUT RENDEZ-VOUS");
        historiqueAppointment.setCreatedDate(new Date());

        historiqueAppointmentService.save(historiqueAppointment);
        return new ResponseEntity<>(appointmentResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Appointment> createAppointment(Appointment appointment, Long idOuv, Long id) {
        Ouvrier ouvrier = Optional.of(ouvrierService.findById(idOuv)).get();
        Utilisateur utilisateur = Optional.of(utilisateurService.findById(id)).get();
        appointment.setStatusOfAppointment("Encours");
        //    appointment.setReference(this.generateAppointmentReference());
        appointment.setReference(GenerateCode.generateNumberOfCode());
        appointment.setCreatedDate(new Date());
        appointment.setOuvrier(ouvrier);
        appointment.setUtilisateur(utilisateur);

        Appointment appointmentResult = appointmentService.save(appointment);

        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setAppointment(appointmentResult);
        historiqueAppointment.setAction("AJOUT RENDEZ-VOUS");
        historiqueAppointment.setCreatedDate(new Date());
        historiqueAppointmentService.save(historiqueAppointment);

        return new ResponseEntity<>(appointmentResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Appointment> update(Long id, Appointment appointment) {
        appointment.setId(id);
        Appointment appointmentResult = appointmentService.save(appointment);

        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setAppointment(appointmentResult);
        historiqueAppointment.setAction("AJOUT RENDEZ-VOUS");
        historiqueAppointment.setCreatedDate(new Date());
        historiqueAppointmentService.save(historiqueAppointment);

        return new ResponseEntity<>(appointmentResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> updateStatusOfAppointment(String status, String id) {
        Appointment appointmentResult = appointmentService.updateStatusOfAppointment(status, id);
        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setAppointment(appointmentResult);
        historiqueAppointment.setAction("STATUS RENDEZ-VOUS MODIFIE");
        historiqueAppointment.setCreatedDate(new Date());
        historiqueAppointmentService.save(historiqueAppointment);
        return new ResponseEntity<>(appointmentResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> getAppointmentById(Long id) {
        Appointment appointmentResult = appointmentService.findById(id);
        return new ResponseEntity<>(appointmentResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> getAppointmentByRerefence(String reference) {
        Appointment appointmentResult = appointmentService.findByReference(reference);
        return new ResponseEntity<>(appointmentResult, HttpStatus.OK);
    }

    @Override
    public long getNumbersOfAppointments() {
        return appointmentService.countNumbersOfAppointments();
    }

    @Override
    public BigDecimal getNumbersOfAppointmentsInMonth() {
        return appointmentService.countNumberOfAppointmentsInMonth();
    }

    @Override
    public BigDecimal getNumberOfAppointmentByStatusPending() {
        return appointmentService.countNumberOfAppointmentByStatusPending();
    }

    @Override
    public BigDecimal getNumberOfAppointmentByStatusAccepted() {
        return appointmentService.countNumberOfAppointmentByStatusAccepted();
    }

    @Override
    public BigDecimal getNumberOfAcceptedAppointmentInYear() {
        return appointmentService.countNumberOfAcceptedAppointmentInYear();
    }

    @Override
    public BigDecimal getNumberOfAppointmentByOuvrierId(Long id) {
        return appointmentService.countNumberOfAppointmentByOuvrierId(id);
    }

    @Override
    public BigDecimal getNumberOfAppointmentByCustomerId(Long id) {
        return appointmentService.countNumberOfAppointmentByCustomerId(id);
    }

    @Override
    public BigDecimal getNumberOfAppointmentByCustomerIdAndStatusAccepted(Long userId) {
        return appointmentService.countNumberOfAppointmentByCustomerIdAndStatusAccepted(userId);
    }

    @Override
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointmentList = appointmentService.findAll();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Appointment>> getAllAppointmentsOrderByIdDesc() {
        List<Appointment> appointmentList = appointmentService.findAllAppointmentByIdDesc();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Appointment>> getAllAppointmentsByStatusPending() {
        List<Appointment> appointmentList = appointmentService.findListAppointmentByStatusPending();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Appointment>> getAllAppointmentByStatusAccepted() {
        List<Appointment> appointmentList = appointmentService.findListAppointmentByStatusAccepted();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Appointment>> getAllAppointmentByStatusRefused() {
        List<Appointment> appointmentList = appointmentService.findListAppointmentByStatusRefused();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Appointment>> getAllAppointmentsByCustomerId(Long userId) {
        List<Appointment> appointmentList = appointmentService.findListAppointmentByCustomerId(userId);
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Appointment>> getAllAppointmentsByOuvrierId(Long ouvId) {
        List<Appointment> appointmentList = appointmentService.findListAppointmentByOuvrierId(ouvId);
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Appointment>> getTop4AppointmentByOuvrierIdOrderByCreatedDateDesc(Long ouvId) {
        List<Appointment> appointmentList = appointmentService.findTop4AppointmentOrderByCreatedDateDesc(ouvId);
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @Override
    public List<?> getNumberTotalOfAppointmentPeerMonth() {
        return appointmentService.countNumberTotalOfAppointmentPeerMonth();
    }

    @Override
    public List<?> getNumberTotalOfAppointmentPeerYear() {
        return appointmentService.countNumberTotalOfAppointmentPeerYear();
    }

    @Override
    public void delete(Long id) {
        appointmentService.delete(id);
    }

    public String generateAppointmentReference() {
        final String FORMAT = "yyyyMMddHHmmss";
        return String.valueOf(DateTimeFormat.forPattern(FORMAT).print(LocalDateTime.now()));
    }

}
