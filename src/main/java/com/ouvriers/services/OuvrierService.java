package com.ouvriers.services;

import com.ouvriers.dtos.AddresseDto;
import com.ouvriers.dtos.OuvrierDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface OuvrierService {

    OuvrierDto save(OuvrierDto ouvrierDto);

    OuvrierDto update(Long idChauffeur, OuvrierDto ouvrierDto);

    OuvrierDto saveOuvrierWithFiles(String ouvrierDto,
                                      MultipartFile photoChauffeur,
                                      MultipartFile cvChauffeur) throws IOException;

    OuvrierDto findById(Long id);

    OuvrierDto findByReference(String reference);

    List<OuvrierDto> findAll();

    List<OuvrierDto> findListChauffeurByPermis(Long pId);

    List<OuvrierDto> findListChauffeurByKeyword(String keyword);

    List<OuvrierDto> findChauffeurByDisponibility(String disponility);

    BigDecimal countNumbersOfChauffeurs();

    Page<OuvrierDto> findChauffeurByPageable(Pageable pageable);

    Page<OuvrierDto> findChauffeurByKeywordByPageable(String mc, Pageable pageable);


    Page<OuvrierDto> findChauffeurByLocalityPageables(Long addId, Pageable pageable);

    Page<OuvrierDto> findChauffeurByPermisPageables(@Param("permId") Long permisId, Pageable pageable);

    void delete(Long id);

}
