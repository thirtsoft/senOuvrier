package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.HistoriqueAppointment;
import com.ouvriers.repository.HistoriqueAppointmentRepository;
import com.ouvriers.services.HistoriqueAppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class HistoriqueAppointmentServiceImpl implements HistoriqueAppointmentService {

    private final HistoriqueAppointmentRepository historiqueAppointmentRepository;

    @Autowired
    public HistoriqueAppointmentServiceImpl(HistoriqueAppointmentRepository historiqueAppointmentRepository) {
        this.historiqueAppointmentRepository = historiqueAppointmentRepository;
    }

    @Override
    public HistoriqueAppointment save(HistoriqueAppointment historiqueAppointment) {
        return historiqueAppointmentRepository.save(historiqueAppointment);
    }

    @Override
    public HistoriqueAppointment update(Long id, HistoriqueAppointment historiqueAppointment) {
        if (!historiqueAppointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueAppointment not found");
        }

        Optional<HistoriqueAppointment> optionalHistoriqueAppointment = historiqueAppointmentRepository.findById(id);

        if (!optionalHistoriqueAppointment.isPresent()) {
            throw new ResourceNotFoundException("HistoriqueAppointment not found");
        }
        HistoriqueAppointment historiqueAppointmentResult = optionalHistoriqueAppointment.get();
        historiqueAppointmentResult.setAction(historiqueAppointment.getAction());
        historiqueAppointmentResult.setCreatedDate(historiqueAppointment.getCreatedDate());
        historiqueAppointmentResult.setAppointment(historiqueAppointment.getAppointment());

        return historiqueAppointmentRepository.save(historiqueAppointmentResult);
    }

    @Override
    public HistoriqueAppointment findById(Long id) {
        if (id == null) {
            log.error("HistoriqueAnnonce Id is null");
            return null;
        }

        Optional<HistoriqueAppointment> optionalHistoriqueAppointment = historiqueAppointmentRepository.findById(id);

        return Optional.of(optionalHistoriqueAppointment.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun HistoriqueAppointment avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<HistoriqueAppointment> findAll() {
        return historiqueAppointmentRepository.findAll();
    }

    @Override
    public List<HistoriqueAppointment> findHistoriqueAppointmentByOrderByIdDesc() {
        return historiqueAppointmentRepository.findHistoriqueAppointmentByOrderByIdDesc();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("HistoriqueAppointment not found");
            return;
        }
        historiqueAppointmentRepository.deleteById(id);
    }
}
