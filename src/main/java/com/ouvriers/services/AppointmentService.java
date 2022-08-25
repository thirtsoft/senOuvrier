package com.ouvriers.services;

import com.ouvriers.models.Appointment;

import java.math.BigDecimal;
import java.util.List;

public interface AppointmentService {

    Appointment save(Appointment appointment);

    Appointment update(Long idAppointment, Appointment appointment);

    Appointment updateStatusOfAppointment(String statusOfAppointment, String id);

    Appointment findById(Long id);

    Appointment findByReference(String reference);

    long countNumbersOfAppointments();

    BigDecimal countNumberOfAppointmentsInMonth();

    BigDecimal countNumberOfAppointmentByStatusPending();

    BigDecimal countNumberOfAppointmentByStatusAccepted();

    BigDecimal countNumberOfAcceptedAppointmentInYear();

    BigDecimal countNumberOfAppointmentByOuvrierId(Long idOuv);

    BigDecimal countNumberOfAppointmentByCustomerId(Long userId);

    BigDecimal countNumberOfAppointmentByCustomerIdAndStatusAccepted(Long userId);

    List<Appointment> findAll();

    List<Appointment> findAllAppointmentByIdDesc();

    List<Appointment> findListAppointmentByCustomerId(Long userId);

    List<Appointment> findListAppointmentByOuvrierId(Long ouvId);

    List<Appointment> findTop4AppointmentOrderByCreatedDateDesc(Long ouvId);

    List<Appointment> findListAppointmentByStatusPending();

    List<Appointment> findListAppointmentByStatusAccepted();

    List<Appointment> findListAppointmentByStatusRefused();

    List<?> countNumberTotalOfAppointmentPeerMonth();

    List<?> countNumberTotalOfAppointmentPeerYear();

    void delete(Long id);


}
