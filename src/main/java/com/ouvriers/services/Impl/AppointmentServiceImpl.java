package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Appointment;
import com.ouvriers.repository.AppointmentRepository;
import com.ouvriers.services.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Long idAppointment, Appointment appointment) {
        if (!appointmentRepository.existsById(idAppointment)) {
            throw new ResourceNotFoundException("Appointment not found");
        }

        Optional<Appointment> appointmentOptional = appointmentRepository.findById(idAppointment);

        if (!appointmentOptional.isPresent()) {
            throw new ResourceNotFoundException("Appointment not found");
        }

        Appointment appointmentResult = appointmentOptional.get();
        appointmentResult.setReference(appointment.getReference());
        appointmentResult.setAppointmentDate(appointment.getAppointmentDate());
        appointmentResult.setStatusOfAppointment(appointment.getStatusOfAppointment());
        appointmentResult.setTime(appointment.getTime());
        appointmentResult.setOuvrier(appointment.getOuvrier());
        appointment.setUtilisateur(appointment.getUtilisateur());

        return appointmentRepository.save(appointmentResult);
    }

    @Override
    public Appointment updateStatusOfAppointment(String statusOfAppointment, String id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(Long.valueOf(id));
        Appointment appointmentResult = appointmentOptional.get();
        appointmentResult.setStatusOfAppointment(statusOfAppointment);
        return appointmentRepository.save(appointmentResult);
    }

    @Override
    public Appointment findById(Long id) {
        if (id == null) {
            log.error("Appointment Id is null");
            return null;
        }

        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);

        return Optional.of(appointmentOptional.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Appointment avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public Appointment findByReference(String reference) {
        if (reference == null) {
            log.error("Appointment Id is null");
            return null;
        }

        Optional<Appointment> appointmentOptional = appointmentRepository.findByReference(reference);

        return Optional.of(appointmentOptional.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Appointment avec l'Id = " + reference + "n'a été trouvé")
        );
    }

    @Override
    public long countNumbersOfAppointments() {
        return appointmentRepository.count();
    }

    @Override
    public BigDecimal countNumberOfAppointmentsInMonth() {
        return appointmentRepository.countNumberOfAppointmentsInMonth();
    }

    @Override
    public BigDecimal countNumberOfAppointmentByStatusPending() {
        return appointmentRepository.countNumberOfAppointmentByStatusPending();
    }

    @Override
    public BigDecimal countNumberOfAppointmentByStatusAccepted() {
        return appointmentRepository.countNumberOfAppointmentByStatusAccepted();
    }

    @Override
    public BigDecimal countNumberOfAcceptedAppointmentInYear() {
        return appointmentRepository.countNumberOfAcceptedAppointmentInYear();
    }

    @Override
    public BigDecimal countNumberOfAppointmentByOuvrierId(Long idOuv) {
        return appointmentRepository.countNumberOfAppointmentByOuvrierId(idOuv);
    }

    @Override
    public BigDecimal countNumberOfAppointmentByCustomerId(Long userId) {
        return appointmentRepository.countNumberOfAppointmentByCustomerId(userId);
    }

    @Override
    public BigDecimal countNumberOfAppointmentByCustomerIdAndStatusAccepted(Long userId) {
        return appointmentRepository.countNumberOfAppointmentByCustomerIdAndStatusAccepted(userId);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAllAppointmentByIdDesc() {
        return appointmentRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Appointment> findListAppointmentByCustomerId(Long userId) {
        return appointmentRepository.findAppointmentsByUserId(userId);
    }

    @Override
    public List<Appointment> findListAppointmentByOuvrierId(Long ouvId) {
        return appointmentRepository.findAppointmentByOuvrierId(ouvId);
    }

    @Override
    public List<Appointment> findTop4AppointmentOrderByCreatedDateDesc(Long ouvId) {
        return appointmentRepository.findTop4AppointmentOrderByCreatedDateDesc(ouvId);
    }

    @Override
    public List<Appointment> findTop10PendingAppointmentOrderByCreatedDateDesc() {
        return appointmentRepository.findTop10PendingAppointmentOrderByCreatedDateDesc();
    }

    @Override
    public List<Appointment> findTop30AppointmentOrderByCreatedDateDesc() {
        return appointmentRepository.findTop30ByOrderByIdDesc();
    }

    @Override
    public List<Appointment> findListAppointmentByStatusPending() {
        return appointmentRepository.findAppointmentsByStatusWaiting();
    }

    @Override
    public List<Appointment> findListAppointmentByStatusAccepted() {
        return appointmentRepository.findAppointmentsByStatusAccepted();
    }

    @Override
    public List<Appointment> findListAppointmentByStatusRefused() {
        return appointmentRepository.findAppointmentsByStatusRefused();
    }

    @Override
    public List<?> countNumberTotalOfAppointmentPeerMonth() {
        return appointmentRepository.countNumberOfAppointmentByMonth();
    }

    @Override
    public List<?> countNumberTotalOfAppointmentPeerYear() {
        return appointmentRepository.countNumberOfAppointmentByYear();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return;
        }
        appointmentRepository.deleteById(id);

    }
}
