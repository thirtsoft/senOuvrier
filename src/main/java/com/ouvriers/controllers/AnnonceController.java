package com.ouvriers.controllers;

import com.ouvriers.controllers.api.AnnonceApi;
import com.ouvriers.models.Annonce;
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
    public ResponseEntity<Annonce> save(Annonce Annonce) {
        return ResponseEntity.ok(annonceService.save(Annonce));
    }

    @Override
    public ResponseEntity<Annonce> update(Long id, Annonce Annonce) {
        Annonce.setId(id);
        return ResponseEntity.ok(annonceService.save(Annonce));

    }

    @Override
    public ResponseEntity<Annonce> getAnnonceById(Long id) {
        return ResponseEntity.ok(annonceService.findById(id));
    }

    @Override
    public ResponseEntity<Annonce> getAnnonceByRerefence(String reference) {
        return ResponseEntity.ok(annonceService.findByReference(reference));
    }

    @Override
    public ResponseEntity<List<Annonce>> getAllAnnonces() {
        return ResponseEntity.ok(annonceService.findAll());
    }

    @Override
    public ResponseEntity<List<Annonce>> getListOfAnnoncesByMetiers(Long pId) {
        return ResponseEntity.ok(annonceService.findListAnnonceByMetier(pId));
    }

    @Override
    public ResponseEntity<List<Annonce>> getListOfAnnoncesByKeyword(String keyword) {
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
    public Page<Annonce> getListAnnonceByPageable(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByPageable(pageable);
    }

    @Override
    public Page<Annonce> getAnnonceByLocalityPageables(Long addId, int page, int size) {
        return null;
    }

    @Override
    public Page<Annonce> getAnnonceByMetierPageables(Long metierId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByMetierByPageable(metierId, pageable);
    }
}
