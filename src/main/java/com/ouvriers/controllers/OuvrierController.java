package com.ouvriers.controllers;

import com.ouvriers.controllers.api.OuvrierApi;
import com.ouvriers.dtos.OuvrierDto;
import com.ouvriers.services.OuvrierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class OuvrierController implements OuvrierApi {

    private final OuvrierService ouvrierService;

    @Override
    public ResponseEntity<OuvrierDto> save(OuvrierDto ouvrierDto) {
        return null;
    }

    @Override
    public ResponseEntity<OuvrierDto> saveOuvrierWithFiles(String OuvrierDto, MultipartFile photoOuvrier, MultipartFile cvOuvrier) throws IOException {
        return null;
    }

    @Override
    public ResponseEntity<OuvrierDto> update(Long id, OuvrierDto ouvrierDto) {
        return null;
    }

    @Override
    public ResponseEntity<OuvrierDto> getOuvrierById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<OuvrierDto> getOuvrierByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<OuvrierDto>> getAllOuvriers() {
        return null;
    }

    @Override
    public ResponseEntity<List<OuvrierDto>> getListOfOuvriersByMetiers(Long pId) {
        return null;
    }

    @Override
    public ResponseEntity<List<OuvrierDto>> getListOfOuvriersByKeyword(String keyword) {
        return null;
    }

    @Override
    public ResponseEntity<List<OuvrierDto>> getListOfOuvriersByDisponibility(String disponibility) {
        return null;
    }

    @Override
    public BigDecimal getNumbersOfOuvriers() {
        return null;
    }

    @Override
    public Page<OuvrierDto> getListOuvrierByPageable(int page, int size) {
        return null;
    }

    @Override
    public byte[] getPhotoOuvrier(Long id) throws Exception {
        return new byte[0];
    }

    @Override
    public void uploadPhotoOuvrier(MultipartFile photoOuvrier, Long idOuvrier) throws IOException {

    }

    @Override
    public byte[] getCvOuvrier(Long id) throws Exception {
        return new byte[0];
    }

    @Override
    public void uploadCvOuvrier(MultipartFile cvOuvrier, Long idOuvrier) throws IOException {

    }

    @Override
    public void downloadOuvrierFile(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {

    }

    @Override
    public Page<OuvrierDto> getOuvrierByKeywordByPageable(String mc, int page, int size) {
        return null;
    }

    @Override
    public Page<OuvrierDto> getOuvrierByLocalityPageables(Long addId, int page, int size) {
        return null;
    }

    @Override
    public Page<OuvrierDto> getOuvrierByMetierPageables(Long permisId, int page, int size) {
        return null;
    }
}
