package com.ouvriers.controllers;

import com.ouvriers.controllers.api.NewsletterApi;
import com.ouvriers.models.Newsletter;
import com.ouvriers.services.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class NewsletterController implements NewsletterApi {

    private final NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @Override
    public ResponseEntity<Newsletter> createNewsleter(Newsletter newsleterDto) {
        newsleterDto.setCreatedDate(new Date());
        Newsletter newNewsleterDto = newsletterService.save(newsleterDto);
        return new ResponseEntity<>(newNewsleterDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Newsletter> updateNewsleter(Long idNewsleter, Newsletter newsletter) {
        newsletter.setId(idNewsleter);
        Newsletter newNewsleterDto = newsletterService.save(newsletter);
        return new ResponseEntity<>(newNewsleterDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Newsletter> getNewsletterById(Long idNewsleter) {
        Newsletter newNewsleterDto = newsletterService.findById(idNewsleter);
        return new ResponseEntity<>(newNewsleterDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Newsletter>> getAllNewsletters() {
        return null;
    }

    @Override
    public ResponseEntity<List<Newsletter>> getListOfNewslettersOrderByIdDesc() {
        return null;
    }

    @Override
    public long getNumbersOfnewsletters() {
        return 0;
    }

    /*
    @Override
    public ResponseEntity<List<Newsletter>> getAllNewsleters() {
        List<Newsletter> newNewsleterDtos = newsletterService.findAll();
        return new ResponseEntity<>(newNewsleterDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Newsletter>> getListOfNewsletersOrderByIdDesc() {
        List<Newsletter> newNewsleterDtos = newsletterService.findAllNewslettersByOrderByIdDesc();
        return new ResponseEntity<>(newNewsleterDtos, HttpStatus.OK);
    }

    @Override
    public long getNumbersOfNewsleters() {
        return newsletterService.countNumbersOfNewsletters();
    }

    */

    @Override
    public void delete(Long idNewsleter) {
        newsletterService.delete(idNewsleter);
    }
}
