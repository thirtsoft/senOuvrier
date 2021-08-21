package com.ouvriers.services.Impl;

import com.ouvriers.dtos.AnnonceDto;
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
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;

    @Override
    public AnnonceDto save(AnnonceDto annonceDto) {
        return AnnonceDto.fromEntityToDto(
                annonceRepository.save(
                        AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    }

    @Override
    public AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto) {
        if (!annonceRepository.existsById(idAnnonce)) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        Optional<Annonce> optionalAnnonce = annonceRepository.findById(idAnnonce);

        if (!optionalAnnonce.isPresent()) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        AnnonceDto annonceDtoResult = AnnonceDto.fromEntityToDto(optionalAnnonce.get());
        annonceDtoResult.setReference(annonceDto.getReference());
        annonceDtoResult.setLibelle(annonceDto.getLibelle());
        annonceDtoResult.setEmailPoste(annonceDto.getEmailPoste());
        annonceDtoResult.setLieuPoste(annonceDto.getLieuPoste());
        annonceDtoResult.setSalaire(annonceDto.getSalaire());
        annonceDtoResult.setTime(annonceDto.getTime());
        annonceDtoResult.setAnneeExperience(annonceDto.getAnneeExperience());
        annonceDtoResult.setStatusAnnonce(annonceDto.getStatusAnnonce());
        annonceDtoResult.setDescription(annonceDto.getDescription());
        annonceDtoResult.setDateCandidature(annonceDto.getDateCandidature());
        annonceDtoResult.setDateCloture(annonceDto.getDateCloture());
        annonceDtoResult.setVilleDto(annonceDto.getVilleDto());
        annonceDtoResult.setMetierDto(annonceDto.getMetierDto());
        annonceDtoResult.setRecruteurDto(annonceDto.getRecruteurDto());

        return AnnonceDto.fromEntityToDto(
                annonceRepository.save(
                        AnnonceDto.fromDtoToEntity(annonceDtoResult)
                )
        );
    }

    @Override
    public AnnonceDto findById(Long id) {
        if (id == null) {
            log.error("Annonce Id is null");
            return null;
        }

        Optional<Annonce> optionalAnnonce = annonceRepository.findById(id);

        return Optional.of(AnnonceDto.fromEntityToDto(optionalAnnonce.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public AnnonceDto findByReference(String reference) {
        if (reference == null) {
            log.error("Annonce Id is null");
            return null;
        }

        Optional<Annonce> optionalAnnonce = annonceRepository.findByReference(reference);

        return Optional.of(AnnonceDto.fromEntityToDto(optionalAnnonce.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + reference + "n'a été trouvé")
        );
    }

    @Override
    public List<AnnonceDto> findAll() {
        return annonceRepository.findAll().stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findListAnnonceByKeyword(String keyword) {
        if (keyword == null) {
            log.error("Annonce not found");
        }
        return annonceRepository.findAnnonceByKeyword(keyword).stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findListAnnonceByLibelle(String libelle) {
        if (libelle == null) {
            log.error("Annonce not found");
        }
        return annonceRepository.findListAnnonceByLibelle(libelle)
                .stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findListAnnonceByMetier(Long mId) {
        return annonceRepository.findListAnnonceByMetier(mId)
                .stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumbersOfAnnonces() {
        return annonceRepository.countNumberOfAnnonces();
    }

    @Override
    public Page<AnnonceDto> findAnnonceByPageable(Pageable pageable) {
        return annonceRepository.findAll(pageable)
                .map(AnnonceDto::fromEntityToDto);
    }

    @Override
    public Page<AnnonceDto> findAnnonceByMetierByPageable(Long metierId, Pageable pageable) {
        return annonceRepository.findAnnonceByMetierPageables(metierId, pageable)
                .map(AnnonceDto::fromEntityToDto);
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
