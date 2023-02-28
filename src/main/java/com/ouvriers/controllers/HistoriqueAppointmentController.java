package com.ouvriers.controllers;

import com.ouvriers.controllers.api.HistoriqueAppointmentApi;
import com.ouvriers.models.HistoriqueAppointment;
import com.ouvriers.services.HistoriqueAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HistoriqueAppointmentController implements HistoriqueAppointmentApi {

    private final HistoriqueAppointmentService historiqueAppointmentService;

    @Autowired
    public HistoriqueAppointmentController(HistoriqueAppointmentService historiqueAppointmentService) {
        this.historiqueAppointmentService = historiqueAppointmentService;
    }

    @Override
    public ResponseEntity<HistoriqueAppointment> create(HistoriqueAppointment historiqueAppointment) {
        HistoriqueAppointment historiqueAppointmentResult = historiqueAppointmentService.save(historiqueAppointment);
        return new ResponseEntity<>(historiqueAppointmentResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueAppointment> update(Long idHistoriqueAppointment, HistoriqueAppointment historiqueAppointment) {
        historiqueAppointment.setId(idHistoriqueAppointment);
        HistoriqueAppointment historiqueAppointmentResult = historiqueAppointmentService.save(historiqueAppointment);
        return new ResponseEntity<>(historiqueAppointmentResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HistoriqueAppointment> getHistoriqueAppointmentById(Long idHistoriqueAppointment) {
        HistoriqueAppointment historiqueAppointmentResult = historiqueAppointmentService.findById(idHistoriqueAppointment);
        return new ResponseEntity<>(historiqueAppointmentResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueAppointment>> getAllHistoriqueAppointments() {
        List<HistoriqueAppointment> historiqueAppointmentList = historiqueAppointmentService.findAll();
        return new ResponseEntity<>(historiqueAppointmentList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueAppointment>> getAllHistoriqueAppointmentOrderByIdDesc() {
        List<HistoriqueAppointment> historiqueAppointmentList = historiqueAppointmentService.findHistoriqueAppointmentByOrderByIdDesc();
        return new ResponseEntity<>(historiqueAppointmentList, HttpStatus.OK);
    }

    @Override
    public void deleteHistoriqueAppointment(Long idHistoriqueAppointment) {
        historiqueAppointmentService.delete(idHistoriqueAppointment);
    }
}
