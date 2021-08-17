package com.ouvriers.services.Impl;

import com.ouvriers.dtos.OuvrierDto;
import com.ouvriers.repository.OuvrierRepository;
import com.ouvriers.services.OuvrierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OuvrierServiceImpl implements OuvrierService {

    private final OuvrierRepository ouvrierRepository;

    @Override
    public OuvrierDto save(OuvrierDto ouvrierDto) {
        return null;
    }

    @Override
    public OuvrierDto update(Long idChauffeur, OuvrierDto ouvrierDto) {
        return null;
    }

    @Override
    public OuvrierDto saveOuvrierWithFiles(String ouvrierDto, MultipartFile photoChauffeur, MultipartFile cvChauffeur) throws IOException {
        return null;
    }

    @Override
    public OuvrierDto findById(Long id) {
        return null;
    }

    @Override
    public OuvrierDto findByReference(String reference) {
        return null;
    }

    @Override
    public List<OuvrierDto> findAll() {
        return null;
    }

    @Override
    public List<OuvrierDto> findListChauffeurByPermis(Long pId) {
        return null;
    }

    @Override
    public List<OuvrierDto> findListChauffeurByKeyword(String keyword) {
        return null;
    }

    @Override
    public List<OuvrierDto> findChauffeurByDisponibility(String disponility) {
        return null;
    }

    @Override
    public BigDecimal countNumbersOfChauffeurs() {
        return null;
    }

    @Override
    public Page<OuvrierDto> findChauffeurByPageable(Pageable pageable) {
        return null;
    }

    @Override
    public Page<OuvrierDto> findChauffeurByKeywordByPageable(String mc, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OuvrierDto> findChauffeurByLocalityPageables(Long addId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OuvrierDto> findChauffeurByPermisPageables(Long permisId, Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
