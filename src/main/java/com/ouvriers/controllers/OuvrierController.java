package com.ouvriers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouvriers.controllers.api.OuvrierApi;
import com.ouvriers.dtos.OuvrierDto;
import com.ouvriers.services.OuvrierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@AllArgsConstructor
public class OuvrierController implements OuvrierApi {

    private final OuvrierService ouvrierService;

    private final String ouvrierPhotosDir = "C://Users//Folio9470m//senouvrier//ouvrier//photos//";

    private final String ouvrierCvDir = "C://Users//Folio9470m//senouvrier//ouvrier//cvs//";

    @Override
    public ResponseEntity<OuvrierDto> save(OuvrierDto ouvrierDto) {
        return ResponseEntity.ok(ouvrierService.save(ouvrierDto));
    }

    @Override
    public ResponseEntity<OuvrierDto> saveOuvrierWithFiles(String ouvrierDto,
                                                           MultipartFile photoOuvrier,
                                                           MultipartFile cvOuvrier) throws IOException {
        return ResponseEntity.ok(ouvrierService.saveOuvrierWithFiles(ouvrierDto, photoOuvrier, cvOuvrier));
    }

    @Override
    public ResponseEntity<OuvrierDto> saveOuvrierWithPhotoAndCv(String chauffeur,
                                                                MultipartFile photoOuvrier,
                                                                MultipartFile cvOuvrier) throws IOException {

        OuvrierDto ouvrierDtoDto = new ObjectMapper().readValue(chauffeur, OuvrierDto.class);

        if (photoOuvrier != null && !photoOuvrier.isEmpty()) {
            ouvrierDtoDto.setPhotoOuvrier(photoOuvrier.getOriginalFilename());
            photoOuvrier.transferTo(new File(ouvrierPhotosDir + photoOuvrier.getOriginalFilename()));
        }

        if (cvOuvrier != null && !cvOuvrier.isEmpty()) {
            ouvrierDtoDto.setCvOuvrier(cvOuvrier.getOriginalFilename());
            cvOuvrier.transferTo(new File(ouvrierCvDir + cvOuvrier.getOriginalFilename()));
        }

        return ResponseEntity.ok(ouvrierService.save(ouvrierDtoDto));
    }

    @Override
    public ResponseEntity<OuvrierDto> update(Long id, OuvrierDto ouvrierDto) {
        ouvrierDto.setId(id);
        return ResponseEntity.ok(ouvrierService.save(ouvrierDto));
    }

    @Override
    public ResponseEntity<OuvrierDto> getOuvrierById(Long id) {
        return ResponseEntity.ok(ouvrierService.findById(id));
    }

    @Override
    public ResponseEntity<OuvrierDto> getOuvrierByRerefence(String reference) {
        return ResponseEntity.ok(ouvrierService.findByReference("%" + reference + "%"));
    }

    @Override
    public ResponseEntity<List<OuvrierDto>> getAllOuvriers() {
        return ResponseEntity.ok(ouvrierService.findAll());
    }

    @Override
    public ResponseEntity<List<OuvrierDto>> getListOfOuvriersByMetiers(Long pId) {
        return ResponseEntity.ok(ouvrierService.findListOfOuvriersByMetier(pId));
    }

    @Override
    public ResponseEntity<List<OuvrierDto>> getListOfOuvriersByKeyword(String keyword) {
        return ResponseEntity.ok(ouvrierService.findListOfOuvriersByKeyword("%" + keyword + "%"));
    }

    @Override
    public ResponseEntity<List<OuvrierDto>> getListOfOuvriersByDisponibility(String disponibility) {
        return ResponseEntity.ok(ouvrierService.findListOfOuvriersByDisponibility("" + disponibility + "%"));
    }

    @Override
    public BigDecimal getNumbersOfOuvriers() {
        return ouvrierService.countNumbersOfOuvriers();
    }

    @Override
    public Page<OuvrierDto> getListOuvrierByPageable(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ouvrierService.findOuvriersByPageable(pageable);
    }

    @Override
    public byte[] getPhotoOuvrier(Long id) throws Exception {
        OuvrierDto ouvrierDto = ouvrierService.findById(id);

        System.out.println("Ouvrier DTO -- " + ouvrierDto);

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/senouvrier//ouvrier//photos/" + ouvrierDto.getPhotoOuvrier()));

    }

    @Override
    public void uploadPhotoOuvrier(MultipartFile photoOuvrier, Long idOuvrier) throws IOException {
        OuvrierDto ouvrierDto = ouvrierService.findById(idOuvrier);
        ouvrierDto.setPhotoOuvrier(photoOuvrier.getOriginalFilename());
        Files.write(Paths.get(
                        System.getProperty("user.home") + "/senouvrier/ouvrier/photos/" + ouvrierDto.getPhotoOuvrier()),
                photoOuvrier.getBytes());

        ouvrierService.save(ouvrierDto);
    }

    @Override
    public byte[] getCvOuvrier(Long id) throws Exception {
        OuvrierDto ouvrierDto = ouvrierService.findById(id);

        System.out.println("Article DTO -- " + ouvrierDto);

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/senouvrier/ouvrier/cvs/" + ouvrierDto.getCvOuvrier()));

    }

    @Override
    public void uploadCvOuvrier(MultipartFile cvOuvrier, Long idOuvrier) throws IOException {
        OuvrierDto ouvrierDto = ouvrierService.findById(idOuvrier);
        ouvrierDto.setCvOuvrier(cvOuvrier.getOriginalFilename());
        Files.write(Paths.get(
                        System.getProperty("user.home") + "/senouvrier/ouvrier/cvs/" + ouvrierDto.getPhotoOuvrier()),
                cvOuvrier.getBytes());

        ouvrierService.save(ouvrierDto);
    }

    @Override
    public void downloadOuvrierFile(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        File file = new File(ouvrierCvDir + fileName);
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @Override
    public Page<OuvrierDto> getOuvrierByKeywordByPageable(String mc, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ouvrierService.findOuvriersByKeywordByPageable(mc, pageable);
    }

    @Override
    public Page<OuvrierDto> getOuvrierByLocalityPageables(Long addId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ouvrierService.findOuvriersByLocalityPageables(addId, pageable);
    }

    @Override
    public Page<OuvrierDto> getOuvrierByMetierPageables(Long permisId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ouvrierService.findOuvriersByPageable(pageable);
    }
}
