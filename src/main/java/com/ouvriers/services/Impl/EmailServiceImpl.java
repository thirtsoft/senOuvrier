package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Email;
import com.ouvriers.models.Newsletter;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.repository.EmailRepository;
import com.ouvriers.services.EmailService;
import com.ouvriers.services.NewsletterService;
import com.ouvriers.utils.EmailConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender javaMailSender;

    private final NewsletterService newsleterService;

    public EmailServiceImpl(EmailRepository emailRepository,
                            JavaMailSender javaMailSender,
                            NewsletterService newsleterService) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
        this.newsleterService = newsleterService;
    }



    @Override
    public void sendEmailToManager(Email email) throws MailException {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + email.getCustomerName()).append(System.lineSeparator());
        sb.append("\n Subject : " + email.getSubject());
        sb.append("\n Message : " + email.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(EmailConstants.to);
        mail.setFrom(email.getRecipient());
        mail.setSubject(email.getSubject());
        mail.setText(email.getMessage());

        email.setCreateDate(new Date());
        email.setCustomerName(email.getCustomerName());

        System.out.println(email);

        emailRepository.save(email);
    }

    @Override
    public void responseEmailToCustomer(Email email) throws MailException, MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setTo(email.getRecipient());
        mimeMessageHelper.setFrom(EmailConstants.from, EmailConstants.managerName);
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(email.getMessage());

        email.setCreateDate(new Date());

        System.out.println(email);

        javaMailSender.send(mimeMessage);

        emailRepository.save(email);

    }

    @Override
    public void sendEmailToRecruteur(Utilisateur utilisateurDto) throws MailException, MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setTo(utilisateurDto.getEmail());
        mimeMessageHelper.setFrom(EmailConstants.from, EmailConstants.managerName);
        mimeMessageHelper.setSubject(utilisateurDto.getSubject());
        mimeMessageHelper.setText(utilisateurDto.getMessage());

        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailToOuvrier(Ouvrier ouvrier) throws MailException, MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setTo(ouvrier.getEmail());
        mimeMessageHelper.setFrom(EmailConstants.from, EmailConstants.managerName);
        mimeMessageHelper.setSubject(ouvrier.getSubject());
        mimeMessageHelper.setText(ouvrier.getMessage());

        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailToNewsletter(Newsletter newsletterDto) throws MailException, MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setTo(newsletterDto.getEmailVisiteur());
        mimeMessageHelper.setFrom(EmailConstants.from, EmailConstants.managerName);
        mimeMessageHelper.setSubject(newsletterDto.getSubject());
        mimeMessageHelper.setText(newsletterDto.getMessage());

        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendMailToAllNewsletters(Newsletter newsletterDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + newsletterDto.getSubject());
        sb.append("\n Message : " + newsletterDto.getMessage());

        List<Newsletter> newsletterDtos = newsleterService.findAll();

        SimpleMailMessage mail = new SimpleMailMessage();

        for (int i = 0; i < newsletterDtos.size(); i++) {
            Newsletter newsletterDtoResult = newsletterDtos.get(i);
            mail.setTo(newsletterDtoResult.getEmailVisiteur());
            mail.setSubject(newsletterDtoResult.getSubject());
            mail.setText(newsletterDtoResult.getMessage());
            mail.setFrom(EmailConstants.from);
        }

        javaMailSender.send(mail);
    }

    @Override
    public Email findById(Long id) {
        if (id == null) {
            log.error("Notification Id is null");
            return null;
        }

        Optional<Email> optionalEmail = emailRepository.findById(id);

        return Optional.of(optionalEmail.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Email avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<Email> findByOrderByIdDesc() {
        return emailRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal countNumberOfEmailInMonth() {
        return emailRepository.countNumberOfEmail();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Email Id is null");
            return;
        }

        emailRepository.deleteById(id);
    }
}
