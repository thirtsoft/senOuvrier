package com.ouvriers.controllers;

import com.ouvriers.controllers.api.AnnonceApi;
import com.ouvriers.dtos.AnnonceDto;
import com.ouvriers.services.AnnonceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<AnnonceDto> save(AnnonceDto AnnonceDto) {
        return null;
    }

    @Override
    public ResponseEntity<AnnonceDto> update(Long id, AnnonceDto AnnonceDto) {
        return null;
    }

    @Override
    public ResponseEntity<AnnonceDto> getAnnonceById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AnnonceDto> getAnnonceByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<AnnonceDto>> getAllAnnonces() {
        return null;
    }

    @Override
    public ResponseEntity<List<AnnonceDto>> getListOfAnnoncesByMetiers(Long pId) {
        return null;
    }

    @Override
    public ResponseEntity<List<AnnonceDto>> getListOfAnnoncesByKeyword(String keyword) {
        return null;
    }

    @Override
    public BigDecimal getNumbersOfAnnonces() {
        return null;
    }

    @Override
    public Page<AnnonceDto> getListAnnonceByPageable(int page, int size) {
        return null;
    }

    @Override
    public byte[] getPhotoAnnonce(Long id) throws Exception {
        return new byte[0];
    }

    @Override
    public void uploadPhotoAnnonce(MultipartFile photoAnnonce, Long idAnnonce) throws IOException {

    }

    @Override
    public Page<AnnonceDto> getAnnonceByLocalityPageables(Long addId, int page, int size) {
        return null;
    }

    @Override
    public Page<AnnonceDto> getAnnonceByMetierPageables(Long permisId, int page, int size) {
        return null;
    }
}
