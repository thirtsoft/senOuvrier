package com.ouvriers.services;

import com.ouvriers.models.Ouvrier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface OuvrierService {

    Ouvrier save(Ouvrier ouvrier);

    Ouvrier update(Long idOuvrier, Ouvrier ouvrier);

    Ouvrier saveOuvrierWithFiles(String ouvrier,
                                    MultipartFile photoOuvrier,
                                    MultipartFile cvOuvrier) throws IOException;

    Ouvrier findById(Long id);

    Ouvrier findByReference(String reference);

    List<Ouvrier> findAll();

    List<Ouvrier> findByOuvrierByIdDesc();

    List<Ouvrier> findListOuvrierBySelected();

    List<Ouvrier> findListOfOuvriersByMetier(Long pId);

    List<Ouvrier> findListOfOuvriersByKeyword(String keyword);

    List<Ouvrier> findListOfOuvriersByDisponibility(String disponility);

    BigDecimal countNumbersOfOuvriers();

    Page<Ouvrier> findOuvriersByPageable(Pageable pageable);

    Page<Ouvrier> findOuvriersByKeywordByPageable(String mc, Pageable pageable);

    Page<Ouvrier> findOuvriersByLocalityPageables(Long addId, Pageable pageable);

    Page<Ouvrier> findOuvriersByMetierPageables(@Param("permId") Long metierId, Pageable pageable);

    void delete(Long id);

}
