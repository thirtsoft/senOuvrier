package com.ouvriers.services;

import com.ouvriers.models.Email;
import com.ouvriers.models.Newsletter;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Utilisateur;
import org.springframework.mail.MailException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

public interface EmailService {

    void sendEmailToManager(Email email) throws MailException;

    void responseEmailToCustomer(Email email) throws MailException, MessagingException, UnsupportedEncodingException;

    void sendEmailToRecruteur(Utilisateur utilisateurDto) throws MailException, MessagingException, UnsupportedEncodingException;

    void sendEmailToOuvrier(Ouvrier ouvrier) throws MailException, MessagingException, UnsupportedEncodingException;

    void sendEmailToNewsletter(Newsletter newsletterDto) throws MailException, MessagingException, UnsupportedEncodingException;

    void sendMailToAllNewsletters(Newsletter newsletterDto);

    Email findById(Long id);

    List<Email> findByOrderByIdDesc();

    BigDecimal countNumberOfEmailInMonth();

    void delete(Long id);
}
