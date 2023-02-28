package com.ouvriers.repository;

import com.ouvriers.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByReference(String reference);

    @Query("select count(c) from Appointment c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfAppointmentsInMonth();

    @Query("select count(c) from Appointment c where c.statusOfAppointment = 'Encours' ")
    BigDecimal countNumberOfAppointmentByStatusPending();

    @Query("select count(c) from Appointment c where c.statusOfAppointment = 'Accepted' ")
    BigDecimal countNumberOfAppointmentByStatusAccepted();

    @Query("select count(c) from Appointment c where (c.statusOfAppointment = 'Accepted') and (year(c.createdDate) = year(current_date)) ")
    BigDecimal countNumberOfAcceptedAppointmentInYear();

    @Query("select count(c) from Appointment c where c.ouvrier.id =:ouv")
    BigDecimal countNumberOfAppointmentByOuvrierId(@Param("ouv") Long idOuv);

    @Query("select count(c) from Appointment c where c.utilisateur.id =:user")
    BigDecimal countNumberOfAppointmentByCustomerId(@Param("user") Long userId);

    @Query("select count(c) from Appointment c where (c.utilisateur.id =:userId) and (c.statusOfAppointment = 'Accepted') ")
    BigDecimal countNumberOfAppointmentByCustomerIdAndStatusAccepted(@Param("userId") Long userId);

    List<Appointment> findByOrderByIdDesc();

    @Query("select a from Appointment a where a.statusOfAppointment like 'Encours'")
    List<Appointment> findAppointmentsByStatusWaiting();

    @Query("select a from Appointment a where a.statusOfAppointment like 'Accepted'")
    List<Appointment> findAppointmentsByStatusAccepted();

    @Query("select a from Appointment a where a.statusOfAppointment like 'Refused'")
    List<Appointment> findAppointmentsByStatusRefused();

    @Query("select a from Appointment a where a.ouvrier.id = :id")
    List<Appointment> findAppointmentByOuvrierId(@Param("id") Long ouvId);

    @Query("select n from Appointment n where n.ouvrier.id =:num")
    List<Appointment> findTop4AppointmentOrderByCreatedDateDesc(@Param("num") Long ouvRef);

    @Query("select a from Appointment a where a.statusOfAppointment like 'Encours'")
    List<Appointment> findTop10PendingAppointmentOrderByCreatedDateDesc();

    List<Appointment> findTop30ByOrderByIdDesc();

    @Query("select a from Appointment a where a.utilisateur.id = :id")
    List<Appointment> findAppointmentsByUserId(@Param("id") Long userId);

    @Query("select EXTRACT(month from(c.createdDate)), count(c) from Appointment c group by EXTRACT(month from(c.createdDate))")
    List<?> countNumberOfAppointmentByMonth();

    @Query("select EXTRACT(year from(c.createdDate)), count(c) from Appointment c group by EXTRACT(year from(c.createdDate))")
    List<?> countNumberOfAppointmentByYear();
}
