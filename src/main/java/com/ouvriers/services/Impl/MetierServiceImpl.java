package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Metier;
import com.ouvriers.repository.MetierRepository;
import com.ouvriers.services.MetierService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class MetierServiceImpl implements MetierService {

    private final MetierRepository metierRepository;


    @Override
    public Metier save(Metier metier) {
        return metierRepository.save(metier);
    }

    @Override
    public Metier update(Long idMetier, Metier metier) {
        if (!metierRepository.existsById(idMetier)) {
            throw new ResourceNotFoundException("Metier not found");
        }

        Optional<Metier> optionalMetier = metierRepository.findById(idMetier);

        if (!optionalMetier.isPresent()) {
            throw new ResourceNotFoundException("Metier not found");
        }

        Metier metierResult = optionalMetier.get();
        metierResult.setReference(metier.getReference());
        metierResult.setDesignation(metier.getDesignation());
        metierResult.setDescription(metier.getDescription());

        return metierRepository.save(metier);
    }

    @Override
    public Metier findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Metier> optionalMetier = metierRepository.findById(id);

        return Optional.of(optionalMetier.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Metier avec l'Id = " + id + "n'a été trouvé")
        );
    }


    @Override
    public List<Metier> findAll() {
        return metierRepository.findAll();
    }

    @Override
    public List<Metier> findByMetierByIdDesc() {
        return metierRepository.findListOfMetierByOrderByIdDesc();
    }


    @Override
    public long countNumbersOfMetiers() {
        return metierRepository.count();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return;
        }
        metierRepository.deleteById(id);

    }
}
