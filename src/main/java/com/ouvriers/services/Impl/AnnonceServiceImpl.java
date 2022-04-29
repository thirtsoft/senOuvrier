package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Annonce;
import com.ouvriers.repository.AnnonceRepository;
import com.ouvriers.services.AnnonceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;

    @Override
    public Annonce save(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    @Override
    public Annonce update(Long idAnnonce, Annonce annonce) {
        if (!annonceRepository.existsById(idAnnonce)) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        Optional<Annonce> optionalAnnonce = annonceRepository.findById(idAnnonce);

        if (!optionalAnnonce.isPresent()) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        Annonce annonceResult = optionalAnnonce.get();
        annonceResult.setReference(annonce.getReference());
        annonceResult.setLibelle(annonce.getLibelle());
        annonceResult.setEmailPoste(annonce.getEmailPoste());
        annonceResult.setLieuPoste(annonce.getLieuPoste());
        annonceResult.setSalaire(annonce.getSalaire());
        annonceResult.setTime(annonce.getTime());
        annonceResult.setAnneeExperience(annonce.getAnneeExperience());
        annonceResult.setTypeContrat(annonce.getTypeContrat());
        annonceResult.setSelected(annonce.isSelected());
        annonceResult.setStatus(annonce.getStatus());
        annonceResult.setDescription(annonce.getDescription());
        annonceResult.setCreatedDate(annonce.getCreatedDate());
        annonceResult.setDateCloture(annonce.getDateCloture());
        annonceResult.setMetier(annonce.getMetier());
        annonceResult.setUtilisateur(annonce.getUtilisateur());

        return annonceRepository.save(annonceResult);
    }

    @Override
    public Annonce findById(Long id) {
        if (id == null) {
            log.error("Annonce Id is null");
            return null;
        }

        Optional<Annonce> optionalAnnonce = annonceRepository.findById(id);

        return Optional.of(optionalAnnonce.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public Annonce findByReference(String reference) {
        if (reference == null) {
            log.error("Annonce Id is null");
            return null;
        }

        Optional<Annonce> optionalAnnonce = annonceRepository.findByReference(reference);

        return Optional.of(optionalAnnonce.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + reference + "n'a été trouvé")
        );
    }

    @Override
    public Annonce FindAnnonceByCustomerId(Long userId) {
        if (userId == null) {
            log.error("Annonce by customer id not found");
            return null;
        }
        Optional<Annonce> optionalAnnonce = annonceRepository.FindAnnonceByCustomerId(userId);

        return Optional.of(optionalAnnonce.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + userId + "n'a été trouvé")
        );

    }

    @Override
    public List<Annonce> findAll() {
        return annonceRepository.findAll();
    }

    @Override
    public List<Annonce> findByAnnonceByIdDesc() {
        return annonceRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Annonce> findListAnnonceBySelected() {
        return annonceRepository.findAnnonceBySelected();
    }

    @Override
    public List<Annonce> findListAnnonceByKeyword(String keyword) {
        if (keyword == null) {
            log.error("Annonce not found");
        }
        return annonceRepository.findAnnonceByKeyword(keyword);
    }

    @Override
    public List<Annonce> findListAnnonceByLibelle(String libelle) {
        if (libelle == null) {
            log.error("Annonce not found");
        }
        return annonceRepository.findListAnnonceByLibelle(libelle);
    }

    @Override
    public List<Annonce> findListAnnonceByMetier(Long mId) {
        return annonceRepository.findListAnnonceByMetier(mId)
                ;
    }

    @Override
    public List<Annonce> FindListAnnonceByCustomerId(Long userId) {
        return annonceRepository.FindListAnnonceByCustomerId(userId);
    }

    @Override
    public List<Annonce> find5LatestRecordsByOrderByIdDesc() {
        return annonceRepository.findTop8ByOrderByIdDesc();
    }

    @Override
    public List<Annonce> findListAnnonceByStatusPending() {
        return annonceRepository.findListAnnonceByStatusPending();
    }

    @Override
    public List<Annonce> findListAnnonceByStatusValid() {
        return annonceRepository.findListAnnonceByStatusValid();
    }

    @Override
    public List<Annonce> findListAnnonceByStatusRejet() {
        return annonceRepository.findListAnnonceByStatusRefused();
    }

    @Override
    public BigDecimal countNumbersOfAnnonces() {
        return annonceRepository.countNumberOfAnnonces();
    }

    @Override
    public BigDecimal countNumberOfAnnoncesInMonth() {
        return annonceRepository.countNumberOfAnnoncesInMonth();
    }

    @Override
    public BigDecimal countNumberOfAnnonceByStatusPending() {
        return annonceRepository.countNumberOfAnnonceByStatusPending();
    }

    @Override
    public List<?> countNumberTotalOfAnnonceByMonth() {
        return annonceRepository.countNumberOfAnnonceByMonth();
    }

    @Override
    public List<?> countNumberTotalOfAnnonceByYear() {
        return annonceRepository.countNumberOfAnnonceByYear();
    }

    @Override
    public Page<Annonce> findAnnonceByPageable(Pageable pageable) {
        return annonceRepository.findAll(pageable);
    }

    @Override
    public Page<Annonce> findAnnonceByMetierByPageable(Long metierId, Pageable pageable) {
        return annonceRepository.findAnnonceByMetierPageables(metierId, pageable);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return;
        }
        annonceRepository.deleteById(id);

    }
}
