package com.ouvriers.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouvriers.dtos.OuvrierDto;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.repository.OuvrierRepository;
import com.ouvriers.services.OuvrierService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class OuvrierServiceImpl implements OuvrierService {

    private final OuvrierRepository ouvrierRepository;


    @Override
    public OuvrierDto save(OuvrierDto ouvrierDto) {
        return OuvrierDto.fromEntityToDto(
                ouvrierRepository.save(
                        OuvrierDto.fromDtoToEntity(ouvrierDto)
                )
        );
    }

    @Override
    public OuvrierDto update(Long idOuvrier, OuvrierDto ouvrierDto) {
        if (!ouvrierRepository.existsById(idOuvrier)) {
            throw new ResourceNotFoundException("Ouvrier not found");
        }

        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findById(idOuvrier);

        if (!optionalOuvrier.isPresent()) {
            throw new ResourceNotFoundException("Metier not found");
        }

        OuvrierDto ouvrierDtoResult = OuvrierDto.fromEntityToDto(optionalOuvrier.get());
        ouvrierDtoResult.setReference(ouvrierDto.getReference());
        ouvrierDtoResult.setFirstName(ouvrierDto.getFirstName());
        ouvrierDtoResult.setLastName(ouvrierDto.getLastName());
        ouvrierDtoResult.setSexe(ouvrierDto.getSexe());
        ouvrierDtoResult.setAddressActuel(ouvrierDto.getAddressActuel());
        ouvrierDtoResult.setPhoneOuvrier(ouvrierDto.getPhoneOuvrier());
        ouvrierDtoResult.setEmail(ouvrierDto.getEmail());
        ouvrierDtoResult.setNbreAnneeExperience(ouvrierDto.getNbreAnneeExperience());
        ouvrierDtoResult.setPretentionSalaire(ouvrierDto.getPretentionSalaire());
        ouvrierDtoResult.setDisponibity(ouvrierDto.getDisponibity());
        ouvrierDtoResult.setMobilite(ouvrierDto.getMobilite());
        ouvrierDtoResult.setCvOuvrier(ouvrierDto.getCvOuvrier());
        ouvrierDtoResult.setPhotoOuvrier(ouvrierDto.getPhotoOuvrier());
        ouvrierDtoResult.setMetierDto(ouvrierDto.getMetierDto());
        ouvrierDtoResult.setAddresseDto(ouvrierDto.getAddresseDto());

        return OuvrierDto.fromEntityToDto(
                ouvrierRepository.save(
                        OuvrierDto.fromDtoToEntity(ouvrierDtoResult)
                )
        );
    }

    @Override
    public OuvrierDto saveOuvrierWithFiles(String ouvrierDto,
                                           MultipartFile photoOuvrier,
                                           MultipartFile cvOuvrier) throws IOException {
        OuvrierDto ouvrierDtoMapper = new ObjectMapper().readValue(ouvrierDto, OuvrierDto.class);
        System.out.println(ouvrierDtoMapper);

        ouvrierDtoMapper.setPhotoOuvrier(photoOuvrier.getOriginalFilename());

        ouvrierDtoMapper.setCvOuvrier(cvOuvrier.getOriginalFilename());

        return OuvrierDto.fromEntityToDto(
                ouvrierRepository.save(
                        OuvrierDto.fromDtoToEntity(ouvrierDtoMapper)
                )
        );
    }

    @Override
    public OuvrierDto findById(Long id) {
        if (id == null) {
            log.error("Ouvrier Id is null");
            return null;
        }

        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findById(id);

        return Optional.of(OuvrierDto.fromEntityToDto(optionalOuvrier.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Ouvrier avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public OuvrierDto findByReference(String reference) {
        if (reference == null) {
            log.error("Ouvrier Id is null");
            return null;
        }

        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findByReference(reference);

        return Optional.of(OuvrierDto.fromEntityToDto(optionalOuvrier.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Ouvrier avec l'Id = " + reference + "n'a été trouvé")
        );
    }

    @Override
    public List<OuvrierDto> findAll() {
        return ouvrierRepository.findAll().stream()
                .map(OuvrierDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OuvrierDto> findListOfOuvriersByMetier(Long pId) {
        return ouvrierRepository.findListOuvriersByMetier(pId)
                .stream()
                .map(OuvrierDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OuvrierDto> findListOfOuvriersByKeyword(String keyword) {
        return ouvrierRepository.findListOfOuvriersByKeyword(keyword)
                .stream()
                .map(OuvrierDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OuvrierDto> findListOfOuvriersByDisponibility(String disponility) {
        return ouvrierRepository.findListOfOuvrierByDisponibility(disponility)
                .stream()
                .map(OuvrierDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumbersOfOuvriers() {
        return ouvrierRepository.countNumberOfOuvriers();
    }

    @Override
    public Page<OuvrierDto> findOuvriersByPageable(Pageable pageable) {
        return ouvrierRepository.findOuvriersByPageable(pageable)
                .map(OuvrierDto::fromEntityToDto);
    }

    @Override
    public Page<OuvrierDto> findOuvriersByKeywordByPageable(String mc, Pageable pageable) {
        return ouvrierRepository.findOuvriersByKeywordByPageable(mc, pageable)
                .map(OuvrierDto::fromEntityToDto);
    }

    @Override
    public Page<OuvrierDto> findOuvriersByLocalityPageables(Long addId, Pageable pageable) {
        return ouvrierRepository.findOuvriersByLocalityPageables(addId, pageable)
                .map(OuvrierDto::fromEntityToDto);
    }

    @Override
    public Page<OuvrierDto> findOuvriersByMetierPageables(Long metierId, Pageable pageable) {
        return ouvrierRepository.findOuvriersByMetierPageables(metierId, pageable)
                .map(OuvrierDto::fromEntityToDto);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Ouvrier Id is null");
            return;
        }
        ouvrierRepository.deleteById(id);
    }
}
