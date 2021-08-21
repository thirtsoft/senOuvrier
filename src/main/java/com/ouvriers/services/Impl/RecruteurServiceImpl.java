package com.ouvriers.services.Impl;

import com.ouvriers.dtos.RecruteurDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Recruteur;
import com.ouvriers.repository.RecruteurRepository;
import com.ouvriers.services.RecruteurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class RecruteurServiceImpl implements RecruteurService {

    private final RecruteurRepository recruteurRepository;

    @Override
    public RecruteurDto save(RecruteurDto recruteurDto) {
        return RecruteurDto.fromEntityToDto(
                recruteurRepository.save(
                        RecruteurDto.fromDtoToEntity(recruteurDto)
                )
        );
    }

    @Override
    public RecruteurDto update(Long idRecruteur, RecruteurDto recruteurDto) {
        if (!recruteurRepository.existsById(idRecruteur)) {
            throw new ResourceNotFoundException("Recruteur not found");
        }

        Optional<Recruteur> optionalRecruteur = recruteurRepository.findById(idRecruteur);

        if (!optionalRecruteur.isPresent()) {
            throw new ResourceNotFoundException("Recruteur not found");
        }

        RecruteurDto recruteurDtoResult = RecruteurDto.fromEntityToDto(optionalRecruteur.get());
        recruteurDtoResult.setFirstName(recruteurDto.getFirstName());
        recruteurDtoResult.setLastName(recruteurDto.getLastName());
        recruteurDtoResult.setAddressRecruteur(recruteurDto.getAddressRecruteur());
        recruteurDtoResult.setPhoneRecruteur(recruteurDto.getPhoneRecruteur());
        recruteurDtoResult.setEmail(recruteurDto.getEmail());
        recruteurDtoResult.setNomEntreprise(recruteurDto.getNomEntreprise());
        recruteurDtoResult.setWebsite(recruteurDto.getWebsite());
        recruteurDtoResult.setVilleRecruteur(recruteurDto.getVilleRecruteur());
        recruteurDtoResult.setSecteurActivite(recruteurDto.getSecteurActivite());
        recruteurDtoResult.setInformation(recruteurDto.getInformation());

        return RecruteurDto.fromEntityToDto(
                recruteurRepository.save(
                        RecruteurDto.fromDtoToEntity(recruteurDtoResult)
                )
        );
    }

    @Override
    public RecruteurDto findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<Recruteur> optionalRecruteur = recruteurRepository.findById(id);


        return Optional.of(RecruteurDto.fromEntityToDto(optionalRecruteur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Metier avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<RecruteurDto> findAll() {
        return recruteurRepository.findAll()
                .stream()
                .map(RecruteurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumbersOfRecruteurs() {
        return recruteurRepository.countNumberOfRecruteurs();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return;
        }
        recruteurRepository.deleteById(id);
    }


}
