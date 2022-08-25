package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Jeton;
import com.ouvriers.repository.JetonRepository;
import com.ouvriers.services.JetonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class JetonServiceImpl implements JetonService {

    private final JetonRepository jetonRepository;

    public JetonServiceImpl(JetonRepository jetonRepository) {
        this.jetonRepository = jetonRepository;
    }

    @Override
    public Jeton save(Jeton jeton) {
        return jetonRepository.save(jeton);
    }

    @Override
    public Jeton update(Long id, Jeton jeton) {
        if (!jetonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Jeton not found");
        }

        Optional<Jeton> optionalJeton = jetonRepository.findById(id);

        Jeton jetonResult = optionalJeton.get();
        jetonResult.setMontant(jeton.getMontant());
        jetonResult.setEtat(jeton.getEtat());
        jetonResult.setCreatedDate(jeton.getCreatedDate());
        jetonResult.setUtilisateur(jeton.getUtilisateur());

        return jetonRepository.save(jetonResult);
    }

    @Override
    public Jeton updateEtatOfJetonDto(String etat, String id) {
        Optional<Jeton> jetonOptional = jetonRepository.findById(Long.valueOf(id));

        Jeton jetonDtoResult = jetonOptional.get();

        jetonDtoResult.setEtat(etat);

        return jetonRepository.save(jetonDtoResult);
    }

    @Override
    public Jeton findById(Long id) {
        if (id == null) {
            log.error("Jeton not found");
            return null;
        }

        Optional<Jeton> optionalJeton = jetonRepository.findById(id);

        return Optional.of(optionalJeton.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Jeton avec l'Id = " + id + "n'a été trouvé")
        );

    }

    @Override
    public List<Jeton> findAll() {
        return jetonRepository.findAll();
    }

    @Override
    public List<Jeton> findAllJetonsByOrderByIdDesc() {
        return jetonRepository.findListOfJetonByOrderByIdDesc();
    }

    @Override
    public List<Jeton> FindListJetonByCustomerId(Long userId) {
        return jetonRepository.FindListJetonByCustomerId(userId);
    }

    @Override
    public BigDecimal sumTotalOfJetonInYear() {
        return jetonRepository.sumTotalOfJetonInYear();
    }

    @Override
    public long countNumbersOfJetons() {
        return jetonRepository.count();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Jeton not found");
            return;
        }
        jetonRepository.deleteById(id);
    }
}
