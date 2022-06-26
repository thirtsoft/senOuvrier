package com.ouvriers.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.repository.OuvrierRepository;
import com.ouvriers.services.OuvrierService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Ouvrier save(Ouvrier ouvrier) {
        return ouvrierRepository.save(ouvrier);
    }

    @Override
    public Ouvrier update(Long idOuvrier, Ouvrier ouvrier) {
        if (!ouvrierRepository.existsById(idOuvrier)) {
            throw new ResourceNotFoundException("Ouvrier not found");
        }

        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findById(idOuvrier);

        if (!optionalOuvrier.isPresent()) {
            throw new ResourceNotFoundException("Metier not found");
        }

        Ouvrier ouvrierResult = optionalOuvrier.get();
        ouvrierResult.setReference(ouvrier.getReference());
        ouvrierResult.setFirstName(ouvrier.getFirstName());
        ouvrierResult.setLastName(ouvrier.getLastName());
        ouvrierResult.setSexe(ouvrier.getSexe());
        ouvrierResult.setAddressActuel(ouvrier.getAddressActuel());
        ouvrierResult.setPhoneOuvrier(ouvrier.getPhoneOuvrier());
        ouvrierResult.setEmail(ouvrier.getEmail());
        ouvrierResult.setNbreAnneeExperience(ouvrier.getNbreAnneeExperience());
        ouvrierResult.setPretentionSalaire(ouvrier.getPretentionSalaire());
        ouvrierResult.setDisponibity(ouvrier.getDisponibity());
        ouvrierResult.setMobilite(ouvrier.getMobilite());
        ouvrierResult.setCvOuvrier(ouvrier.getCvOuvrier());
        ouvrierResult.setPhotoOuvrier(ouvrier.getPhotoOuvrier());
        ouvrierResult.setMetier(ouvrier.getMetier());
        ouvrierResult.setAddress(ouvrier.getAddress());

        return ouvrierRepository.save(ouvrierResult);
    }

    @Override
    public Ouvrier saveOuvrierWithFiles(String ouvrier,
                                           MultipartFile photoOuvrier,
                                           MultipartFile cvOuvrier) throws IOException {

        Ouvrier ouvrierMapper = new ObjectMapper().readValue(ouvrier, Ouvrier.class);

        ouvrierMapper.setPhotoOuvrier(photoOuvrier.getOriginalFilename());

        ouvrierMapper.setCvOuvrier(cvOuvrier.getOriginalFilename());

        return ouvrierRepository.save(ouvrierMapper);
    }

    @Override
    public Ouvrier findById(Long id) {
        if (id == null) {
            log.error("Ouvrier Id is null");
            return null;
        }

        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findById(id);

        return Optional.of(optionalOuvrier.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Ouvrier avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public Ouvrier findByReference(String reference) {
        if (reference == null) {
            log.error("Ouvrier Id is null");
            return null;
        }

        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findByReference(reference);

        return Optional.of(optionalOuvrier.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Ouvrier avec l'Id = " + reference + "n'a été trouvé")
        );
    }

    @Override
    public List<Ouvrier> findAll() {
        return ouvrierRepository.findAll();
    }

    @Override
    public List<Ouvrier> findAllOuvriersByIdDesc() {
        return ouvrierRepository.findOuvrierByOrderByIdDesc();
    }

    @Override
    public List<Ouvrier> findListOuvrierBySelected() {
        return ouvrierRepository.findOuvrierBySelected();
    }

    @Override
    public List<Ouvrier> findListOfOuvriersByMetier(Long pId) {
        return ouvrierRepository.findListOuvriersByMetier(pId);
    }

    @Override
    public List<Ouvrier> findListOfOuvriersByKeyword(String keyword) {
        return ouvrierRepository.findListOfOuvriersByKeyword(keyword);
    }

    @Override
    public List<Ouvrier> findListOfOuvriersByDisponibility(String disponility) {
        return ouvrierRepository.findListOfOuvrierByDisponibility(disponility);
    }

    @Override
    public BigDecimal countNumbersOfOuvriers() {
        return ouvrierRepository.countNumberOfOuvriers();
    }

    @Override
    public List<?> countNumberOfOuvrierByMonth() {
        return ouvrierRepository.countNumberOfOuvrierPeerMonth();
    }

    @Override
    public List<?> countNumberOfOuvrierByYear() {
        return ouvrierRepository.countNumberOfOuvriersPeerYear();
    }

    @Override
    public Page<Ouvrier> findOuvriersByPageable(Pageable pageable) {
        return ouvrierRepository.findOuvriersByPageable(pageable);
    }

    @Override
    public Page<Ouvrier> findOuvriersByKeywordByPageable(String mc, Pageable pageable) {
        return ouvrierRepository.findOuvriersByKeywordByPageable(mc, pageable);
    }

    @Override
    public Page<Ouvrier> findOuvriersByLocalityPageables(Long addId, Pageable pageable) {
        return ouvrierRepository.findOuvriersByLocalityPageables(addId, pageable);
    }

    @Override
    public Page<Ouvrier> findOuvriersByMetierPageables(Long metierId, Pageable pageable) {
        return ouvrierRepository.findOuvriersByMetierPageables(metierId, pageable);
    }

    @Override
    public List<Ouvrier> getAllOuvrierDtos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ouvrierRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Ouvrier> getAllOuvrierDtosByIdAddress(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ouvrierRepository.findAllOuvriersByAddressId(id, pageable)
                .getContent();
    }

    @Override
    public List<Ouvrier> getAllOuvriersByMetierId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ouvrierRepository.findAllOuvriersByMetierId(id, pageable)
                .getContent();
    }


    @Override
    public List<Ouvrier> getAllOuvrierDtosByKey(String disponibility, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ouvrierRepository.findByDisponibityContaining(disponibility, pageable)
                .getContent();
    }

    @Override
    public long getAllOuvrierDtosSize() {
        return ouvrierRepository.count();
    }

    @Override
    public long getOuvriersDtosByAddressIdLength(Long id) {
        return ouvrierRepository.getOuvrierLengthByAddressId(id);
    }

    @Override
    public long getOuvriersDtosByMetierIdLength(Long id) {
        return ouvrierRepository.getOuvrierLengthByMetierId(id);
    }

    @Override
    public long getOuvrierDtosSizeByKey(String disponibility) {
        return ouvrierRepository.getOuvrierSizeByKey(disponibility);
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
