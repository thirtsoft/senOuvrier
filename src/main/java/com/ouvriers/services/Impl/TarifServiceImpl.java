package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Tarif;
import com.ouvriers.repository.TarifRepository;
import com.ouvriers.services.TarifService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TarifServiceImpl implements TarifService {

    private final TarifRepository tarifRepository;

    @Override
    public Tarif save(Tarif tarif) {
        return tarifRepository.save(tarif);
    }

    @Override
    public Tarif update(Long idTarif, Tarif tarif) {
        if (!tarifRepository.existsById(idTarif)) {
            throw new ResourceNotFoundException("Tarif not found");
        }

        Optional<Tarif> optionalTarif = tarifRepository.findById(idTarif);

        if (!optionalTarif.isPresent()) {
            throw new ResourceNotFoundException("Tarif not found");
        }

        Tarif tarifResult = optionalTarif.get();
        tarifResult.setReference(tarif.getReference());
        tarifResult.setMontantTarif(tarif.getMontantTarif());
        tarifResult.setDescription(tarif.getDescription());
        tarifResult.setTypeAnnonce(tarif.getTypeAnnonce());

        return tarifRepository.save(tarifResult);
    }

    @Override
    public Tarif findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Tarif> optionalTarif = tarifRepository.findById(id);

        return Optional.of(optionalTarif.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Tarif avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<Tarif> findAll() {
        return tarifRepository.findAll();
    }

    @Override
    public List<Tarif> findByTarifByIdDesc() {
        return tarifRepository.findTarifByOrderByIdDesc();
    }

    @Override
    public List<Tarif> findListTarifByKeyword(String keyword) {
        return tarifRepository.findTarifByKeyword(keyword);
    }

    @Override
    public List<Tarif> findListTarifByAnnonce(Long pId) {
        return tarifRepository.findTarifByAnnonce(pId);
    }

    @Override
    public Page<Tarif> findTarifByPageable(Pageable pageable) {
        return tarifRepository.findTarif(pageable);
    }

    @Override
    public Page<Tarif> findTarifByAnnonceByPageable(Long annonceId, Pageable pageable) {
        return tarifRepository.findTarifByAnnoncePageables(annonceId, pageable);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Tarif Id is null");
            return;
        }
        tarifRepository.deleteById(id);
    }
}
