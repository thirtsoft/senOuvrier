package com.ouvriers.repository;

import com.ouvriers.models.Email;
import com.ouvriers.utils.EmailConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailRepositoryTest {

    @Autowired
    private EmailRepository emailRepository;


    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmail() {
        Email email = new Email();
        email.setCustomerName("Customer");
        email.setRecipient("customer@gmail.com");
        email.setSubject("Subject01");
        email.setMessage("Message01");
        email.setCreateDate(new Date());
        emailRepository.save(email);

        assertThat(email.getId()).isGreaterThan(0);
        assertThat(email.getRecipient()).isEqualTo("customer@gmail.com");
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void sendEmailToMangerTest() {
        Email email = new Email();
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
        emailRepository.save(email);

        assertThat(email.getId()).isGreaterThan(0);
    }


    @Test
    @Order(3)
    public void getEmailByIdTest() {
        Optional<Email> optionalEmail = emailRepository.findById(1L);
        if (optionalEmail.isPresent()) {
            assertThat(optionalEmail.get()).isNotNull();
        }
    }

    @Test
    @Order(4)
    public void getAllEmailOrderByIdDescTest() {
        List<Email> emailList = emailRepository.findByOrderByIdDesc();
        assertThat(emailList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(5)
    public void getNumberOfEmailsInMonthTest() {
        BigDecimal number = emailRepository.countNumberOfEmail();
        BigDecimal val = BigDecimal.valueOf(2000000.5);
        assertThat(number).isLessThan(val);
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteEmailTest() {
        Optional<Email> optionalEmail = emailRepository.findById(1L);
        if (optionalEmail.isPresent()) {
            emailRepository.delete(optionalEmail.get());
        }
        Email email = null;
        Optional<Email> optionalEmail1 = emailRepository.findById(1L);
        if (optionalEmail1.isPresent()) {
            email = optionalEmail1.get();
        }
        Assertions.assertThat(email).isNull();
    }
}
