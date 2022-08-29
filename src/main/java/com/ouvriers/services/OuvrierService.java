package com.ouvriers.services;

import com.ouvriers.models.Ouvrier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    List<Ouvrier> findAllOuvriersByIdDesc();

    List<Ouvrier> findListOuvrierBySelected();

    List<Ouvrier> findListOfOuvriersByMetier(Long pId);

    List<Ouvrier> findListOfOuvriersByKeyword(String keyword);

    List<Ouvrier> findListOfOuvriersByDisponibility(String disponility);

    BigDecimal countNumbersOfOuvriers();

    List<?> countNumberOfOuvrierByMonth();

    List<?> countNumberOfOuvrierByYear();

    Page<Ouvrier> findOuvriersByPageable(Pageable pageable);

    Page<Ouvrier> findOuvriersByKeywordByPageable(String mc, Pageable pageable);

    Page<Ouvrier> findOuvriersByLocalityPageables(Long addId, Pageable pageable);

    Page<Ouvrier> findOuvriersByLocalityIdPageables(Long locId, Pageable pageable);

    Page<Ouvrier> findOuvriersByMetierPageables(Long metierId, Pageable pageable);


    List<Ouvrier> getAllOuvrierDtos(int page, int size);

    List<Ouvrier> getAllOuvrierDtosByIdAddress(Long id, int page, int size);

    List<Ouvrier> getAllOuvriersByLocalityId(Long id, int page, int size);

    List<Ouvrier> getAllOuvriersByMetierId(Long id, int page, int size);

    List<Ouvrier> getAllOuvrierDtosByKey(String disponibility, int page, int size);

    long getAllOuvrierDtosSize();

    long getOuvriersDtosByAddressIdLength(Long id);

    long getOuvriersByLocalityIdLength(Long id);

    long getOuvriersDtosByMetierIdLength(Long id);

    long getOuvrierDtosSizeByKey(String disponibility);

    void delete(Long id);

}
