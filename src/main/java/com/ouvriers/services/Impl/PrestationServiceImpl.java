package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Appointment;
import com.ouvriers.models.Prestation;
import com.ouvriers.repository.PrestationRepository;
import com.ouvriers.services.PrestationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PrestationServiceImpl implements PrestationService {

    private final PrestationRepository prestationRepository;

    @Override
    public Prestation save(Prestation prestation) {
        return prestationRepository.save(prestation);
    }

    @Override
    public Prestation update(Long id, Prestation prestation) {
        if (!prestationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prestation not found");
        }

        Optional<Prestation> optionalPrestation = prestationRepository.findById(id);

        if (!optionalPrestation.isPresent()) {
            throw new ResourceNotFoundException("Prestation not found");
        }

        Prestation prestationResult = optionalPrestation.get();
        prestationResult.setTitle(prestation.getTitle());
        prestationResult.setDescription(prestation.getDescription());
        prestationResult.setOuvrier(prestation.getOuvrier());

        return prestationRepository.save(prestationResult);
    }

    @Override
    public BigDecimal countNumberOfPrestationsByOuvrierId(Long idOuv) {
        return prestationRepository.countNumberOfPrestationByOuvrierId(idOuv);
    }

    @Override
    public Prestation findById(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return null;
        }

        Optional<Prestation> optionalPrestation = prestationRepository.findById(id);

        return Optional.of(optionalPrestation.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Prestation avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<Prestation> findAll() {
        return prestationRepository.findAll();
    }

    @Override
    public List<Prestation> findAllPrestationsOrderByIdDesc() {
        return prestationRepository.findPrestationByOrderByIdDesc();
    }

    @Override
    public List<Prestation> FindAllPrestationsOrderByOuvrierId(Long ouvId) {
        return prestationRepository.FindPrestationsByOuvrierId(ouvId);
    }

    @Override
    public List<Prestation> findTop4PrestationsOrderByCreatedDateDesc(Long ouvRef) {
        return prestationRepository.findTop4PrestationOrderByCreatedDateDesc(ouvRef);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Prestation Id is null");
            return;
        }
        prestationRepository.deleteById(id);
    }
}
