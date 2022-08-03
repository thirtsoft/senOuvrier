package com.ouvriers.repository;

import com.ouvriers.models.HistoriqueAnnonce;
import com.ouvriers.models.HistoriqueAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueAppointmentRepository extends JpaRepository<HistoriqueAppointment, Long> {

    List<HistoriqueAppointment> findHistoriqueAppointmentByOrderByIdDesc();

}
