package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Ville;
import com.ouvriers.repository.VilleRepository;
import com.ouvriers.services.VilleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class VilleServiceImpl implements VilleService {

    private final VilleRepository villeRepository;

    @Override
    public Ville save(Ville ville) {
        return villeRepository.save(ville);
    }

    @Override
    public Ville update(Long idVille, Ville ville) {
        if (!villeRepository.existsById(idVille)) {
            throw new ResourceNotFoundException("Ville not found");
        }

        Optional<Ville> optionalVille = villeRepository.findById(idVille);

        if (!optionalVille.isPresent()) {
            throw new ResourceNotFoundException("Ville not found");
        }

        Ville villeResult = optionalVille.get();
        villeResult.setReference(ville.getReference());
        villeResult.setNom(ville.getNom());
        villeResult.setPays(ville.getPays());

        return villeRepository.save(villeResult);
    }

    @Override
    public Ville findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Ville> optionalVille = villeRepository.findById(id);

        return Optional.of(optionalVille.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Ville avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<Ville> findAll() {
        return villeRepository.findAll();
    }

    @Override
    public List<Ville> findByVillesByIdDesc() {
        return villeRepository.findVilleByOrderByIdDesc();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Ville Id is null");
            return;
        }
        villeRepository.deleteById(id);
    }
}
