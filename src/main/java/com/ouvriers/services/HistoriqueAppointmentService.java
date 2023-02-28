package com.ouvriers.services;

import com.ouvriers.models.HistoriqueAppointment;

import java.util.List;

public interface HistoriqueAppointmentService {

    HistoriqueAppointment save(HistoriqueAppointment historiqueAppointment);

    HistoriqueAppointment update(Long id, HistoriqueAppointment historiqueAppointment);

    HistoriqueAppointment findById(Long id);

    List<HistoriqueAppointment> findAll();

    List<HistoriqueAppointment> findHistoriqueAppointmentByOrderByIdDesc();

    void delete(Long id);

}
