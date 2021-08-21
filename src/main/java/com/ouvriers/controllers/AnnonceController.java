package com.ouvriers.controllers;

import com.ouvriers.controllers.api.AnnonceApi;
import com.ouvriers.dtos.AnnonceDto;
import com.ouvriers.services.AnnonceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class AnnonceController implements AnnonceApi {

    private final AnnonceService annonceService;

    @Override
    public ResponseEntity<AnnonceDto> save(AnnonceDto annonceDto) {
        return ResponseEntity.ok(annonceService.save(annonceDto));
    }

    @Override
    public ResponseEntity<AnnonceDto> update(Long id, AnnonceDto annonceDto) {
        annonceDto.setId(id);
        return ResponseEntity.ok(annonceService.save(annonceDto));

    }

    @Override
    public ResponseEntity<AnnonceDto> getAnnonceById(Long id) {
        return ResponseEntity.ok(annonceService.findById(id));
    }

    @Override
    public ResponseEntity<AnnonceDto> getAnnonceByRerefence(String reference) {
        return ResponseEntity.ok(annonceService.findByReference(reference));
    }

    @Override
    public ResponseEntity<List<AnnonceDto>> getAllAnnonces() {
        return ResponseEntity.ok(annonceService.findAll());
    }

    @Override
    public ResponseEntity<List<AnnonceDto>> getListOfAnnoncesByMetiers(Long pId) {
        return ResponseEntity.ok(annonceService.findListAnnonceByMetier(pId));
    }

    @Override
    public ResponseEntity<List<AnnonceDto>> getListOfAnnoncesByKeyword(String keyword) {
        return ResponseEntity.ok(annonceService.findListAnnonceByKeyword("%" + keyword + "%"));
    }

    @Override
    public BigDecimal getNumbersOfAnnonces() {
        return annonceService.countNumbersOfAnnonces();
    }

    @Override
    public byte[] getPhotoAnnonce(Long id) throws Exception {
        return new byte[0];
    }

    @Override
    public void uploadPhotoAnnonce(MultipartFile photoAnnonce, Long idAnnonce) throws IOException {

    }

    @Override
    public Page<AnnonceDto> getListAnnonceByPageable(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByPageable(pageable);
    }

    @Override
    public Page<AnnonceDto> getAnnonceByLocalityPageables(Long addId, int page, int size) {
        return null;
    }

    @Override
    public Page<AnnonceDto> getAnnonceByMetierPageables(Long metierId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByMetierByPageable(metierId, pageable);
    }
}
