package com.ouvriers.services;

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

    OuvrierDto update(Long idOuvrier, OuvrierDto ouvrierDto);

    OuvrierDto saveOuvrierWithFiles(String ouvrierDto,
                                    MultipartFile photoOuvrier,
                                    MultipartFile cvOuvrier) throws IOException;

    OuvrierDto findById(Long id);

    OuvrierDto findByReference(String reference);

    List<OuvrierDto> findAll();

    List<OuvrierDto> findListOuvrierByMetier(Long pId);

    List<OuvrierDto> findListOuvrierByKeyword(String keyword);

    List<OuvrierDto> findOuvrierByDisponibility(String disponility);

    BigDecimal countNumbersOfOuvriers();

    Page<OuvrierDto> findOuvrierByPageable(Pageable pageable);

    Page<OuvrierDto> findOuvrierByKeywordByPageable(String mc, Pageable pageable);


    Page<OuvrierDto> findOuvrierByLocalityPageables(Long addId, Pageable pageable);

    Page<OuvrierDto> findOuvrierByMetierPageables(@Param("permId") Long MetierId, Pageable pageable);

    void delete(Long id);

}
