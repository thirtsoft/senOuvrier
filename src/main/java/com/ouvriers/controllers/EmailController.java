package com.ouvriers.controllers;

import com.ouvriers.controllers.api.EmailApi;
import com.ouvriers.models.Email;
import com.ouvriers.models.Newsletter;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class EmailController implements EmailApi {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public ResponseEntity<Email> sendEmailToManager(Email email) {
        try {
            email.setCreateDate(new Date());
            emailService.sendEmailToManager(email);
            return new ResponseEntity<Email>(email, HttpStatus.CREATED);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Email> responseEmailToCustomer(Email email) {
        try {
            email.setCreateDate(new Date());
            emailService.responseEmailToCustomer(email);
            return new ResponseEntity<Email>(email, HttpStatus.CREATED);
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<Utilisateur> sendMailToRecruteur(Utilisateur utilisateur) {
        try {
            emailService.sendEmailToRecruteur(utilisateur);
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Ouvrier> sendMailToOuvrier(Ouvrier ouvrier) {
        try {
            emailService.sendEmailToOuvrier(ouvrier);
            return new ResponseEntity<>(ouvrier, HttpStatus.OK);
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<Newsletter> sendMailToCustomer(Newsletter newsletter) {
        try {
            emailService.sendEmailToNewsletter(newsletter);
            return new ResponseEntity<>(newsletter, HttpStatus.OK);
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Newsletter> sendMailToAllCustomers(Newsletter newsletter) {
        try {
            emailService.sendMailToAllNewsletters(newsletter);
            return new ResponseEntity<>(newsletter, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<Email> getEmailById(Long id) {
        return ResponseEntity.ok(emailService.findById(id));
    }

    @Override
    public ResponseEntity<List<Email>> getAllEmailsOrderByIdDesc() {
        List<Email> EmailList = emailService.findByOrderByIdDesc();
        return new ResponseEntity(EmailList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfEmail() {
        return emailService.countNumberOfEmailInMonth();
    }

    @Override
    public void delete(Long id) {
        emailService.delete(id);
    }


}
