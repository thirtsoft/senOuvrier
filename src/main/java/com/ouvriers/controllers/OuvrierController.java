package com.ouvriers.controllers;

import com.ouvriers.controllers.api.OuvrierApi;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.services.OuvrierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import java.util.Date;
import java.util.List;


@RestController
@AllArgsConstructor
public class OuvrierController implements OuvrierApi {

    private final OuvrierService ouvrierService;

    private final String ouvrierPhotosDir = "C://Users//Folio9470m//senouvrier//ouvrier//photos//";

    private final String ouvrierCvDir = "C://Users//Folio9470m//senouvrier//ouvrier//cvs//";

    @Override
    public ResponseEntity<Ouvrier> save(Ouvrier ouvrier) {
        ouvrier.setDateInscription(new Date());
        Ouvrier ouvrierResult = ouvrierService.save(ouvrier);
        return new ResponseEntity<>(ouvrierResult, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Ouvrier> saveOuvrierWithFiles(String ouvrier,
                                                           MultipartFile photoOuvrier,
                                                           MultipartFile cvOuvrier) throws IOException {
        Ouvrier ouvrierResult = ouvrierService.saveOuvrierWithFiles(ouvrier, photoOuvrier, cvOuvrier);
        return new ResponseEntity<>(ouvrierResult, HttpStatus.CREATED);
    }

    /*
    @Override
    public ResponseEntity<Ouvrier> saveOuvrierWithPhotoAndCv(String ouvrier,
                                                                MultipartFile photoOuvrier,
                                                                MultipartFile cvOuvrier) throws IOException {

        Ouvrier ouvrierDto = new ObjectMapper().readValue(ouvrier, Ouvrier.class);

        if (photoOuvrier != null && !photoOuvrier.isEmpty()) {
            ouvrierDto.setPhotoOuvrier(photoOuvrier.getOriginalFilename());
            photoOuvrier.transferTo(new File(ouvrierPhotosDir + photoOuvrier.getOriginalFilename()));
        }

        if (cvOuvrier != null && !cvOuvrier.isEmpty()) {
            ouvrierDto.setCvOuvrier(cvOuvrier.getOriginalFilename());
            cvOuvrier.transferTo(new File(ouvrierCvDir + cvOuvrier.getOriginalFilename()));
        }

        return ResponseEntity.ok(ouvrierService.save(ouvrierDto));
    }
    */

    @Override
    public ResponseEntity<Ouvrier> update(Long id, Ouvrier ouvrier) {
        ouvrier.setId(id);
        Ouvrier ouvrierResult = ouvrierService.save(ouvrier);
        return new ResponseEntity<>(ouvrierResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ouvrier> getOuvrierById(Long id) {
        Ouvrier ouvrierResult = ouvrierService.findById(id);
        return new ResponseEntity<>(ouvrierResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ouvrier> getOuvrierByRerefence(String reference) {
        Ouvrier ouvrierResult = ouvrierService.findByReference("%" + reference + "%");
        return new ResponseEntity<>(ouvrierResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ouvrier>> getAllOuvriers() {
        List<Ouvrier> ouvrierList = ouvrierService.findAll();
        return new ResponseEntity<>(ouvrierList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ouvrier>> getListOfOuvriersByMetiers(Long pId) {
        List<Ouvrier> ouvrierList = ouvrierService.findListOfOuvriersByMetier(pId);
        return new ResponseEntity<>(ouvrierList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ouvrier>> getListOfOuvriersByKeyword(String keyword) {
        List<Ouvrier> ouvrierList = ouvrierService.findListOfOuvriersByKeyword("%" + keyword + "%");
        return new ResponseEntity<>(ouvrierList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ouvrier>> getListOfOuvriersByDisponibility(String disponibility) {
        List<Ouvrier> ouvrierList = ouvrierService.findListOfOuvriersByDisponibility("" + disponibility + "%");
        return new ResponseEntity<>(ouvrierList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumbersOfOuvriers() {
        return ouvrierService.countNumbersOfOuvriers();
    }

    @Override
    public Page<Ouvrier> getListOuvrierByPageable(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ouvrierService.findOuvriersByPageable(pageable);
    }

    @Override
    public byte[] getPhotoOuvrier(Long id) throws Exception {
        Ouvrier ouvrier = ouvrierService.findById(id);

        System.out.println("Ouvrier DTO -- " + ouvrier);

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/senouvrier//ouvrier//photos/" + ouvrier.getPhotoOuvrier()));

    }

    @Override
    public void uploadPhotoOuvrier(MultipartFile photoOuvrier, Long idOuvrier) throws IOException {
        Ouvrier ouvrier = ouvrierService.findById(idOuvrier);
        ouvrier.setPhotoOuvrier(photoOuvrier.getOriginalFilename());
        Files.write(Paths.get(
                        System.getProperty("user.home") + "/senouvrier/ouvrier/photos/" + ouvrier.getPhotoOuvrier()),
                photoOuvrier.getBytes());

        ouvrierService.save(ouvrier);
    }

    @Override
    public byte[] getCvOuvrier(Long id) throws Exception {
        Ouvrier ouvrier = ouvrierService.findById(id);

        System.out.println("Article DTO -- " + ouvrier);

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/senouvrier/ouvrier/cvs/" + ouvrier.getCvOuvrier()));

    }

    @Override
    public void uploadCvOuvrier(MultipartFile cvOuvrier, Long idOuvrier) throws IOException {
        Ouvrier ouvrier = ouvrierService.findById(idOuvrier);
        ouvrier.setCvOuvrier(cvOuvrier.getOriginalFilename());
        Files.write(Paths.get(
                        System.getProperty("user.home") + "/senouvrier/ouvrier/cvs/" + ouvrier.getPhotoOuvrier()),
                cvOuvrier.getBytes());

        ouvrierService.save(ouvrier);
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
    public Page<Ouvrier> getOuvrierByKeywordByPageable(String mc, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ouvrierService.findOuvriersByKeywordByPageable(mc, pageable);
    }

    @Override
    public Page<Ouvrier> getOuvrierByLocalityPageables(Long addId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ouvrierService.findOuvriersByLocalityPageables(addId, pageable);
    }

    @Override
    public Page<Ouvrier> getOuvrierByMetierPageables(Long permisId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ouvrierService.findOuvriersByPageable(pageable);
    }
}
