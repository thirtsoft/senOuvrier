package com.ouvriers.repository;

import com.ouvriers.models.Newsletter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NewsletterRepositoryTest {

    @Autowired
    private NewsletterRepository newsletterRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveNeswletterTest() {
        Newsletter newsletter = new Newsletter();
        newsletter.setEmailVisiteur("newsleter@gmail.com");
        newsletter.setMessage("Messgae");
        newsletter.setCreatedDate(new Date());
        newsletterRepository.save(newsletter);

        Assertions.assertThat(newsletter.getId()).isNotNull();

        String email = "newsleter@gmail.com";

        Assertions.assertThat(email).isEqualTo(newsletter.getEmailVisiteur());
    }

    @Test
    @Order(2)
    public void getNewsletterByIdTest() {
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(1L);
        if (optionalNewsletter.isPresent()) {
            assertThat(optionalNewsletter.get()).isNotNull();
        }
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void updateNewsletterTest() {
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(1L);
        if (optionalNewsletter.isPresent()) {
            Newsletter newsletter = optionalNewsletter.get();
            newsletter.setEmailVisiteur("news@gmail.com");

            Newsletter newsletterUpdated = newsletterRepository.save(newsletter);

            Assertions.assertThat(newsletterUpdated.getEmailVisiteur()).isEqualTo("news@gmail.com");
        }
    }

    @Test
    @Order(4)
    public void getListOfNewslettersTest() {
        List<Newsletter> newsletterList = newsletterRepository.findAll();
        assertThat(newsletterList.isEmpty()).isFalse();
    }

    @Test
    @Order(5)
    public void getAllNewslettersByOrderByIdDescTest() {
        List<Newsletter> newsletterList = newsletterRepository.findListOfNewslettersByOrderByIdDesc();
        assertThat(newsletterList.size()).isLessThan(3);
    }

    @Test
    @Order(6)
    public void getNumbersOfNewslettersTest() {
        long numberOfNewsletter = newsletterRepository.count();
        assertThat(numberOfNewsletter).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(7)
    @Rollback(value = false)
    public void deleteNewsletterTest() {
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(1L);
        if (optionalNewsletter.isPresent()) {
            newsletterRepository.delete(optionalNewsletter.get());
        }
        Newsletter newsletter = null;
        Optional<Newsletter> optionalNewsletter1 = newsletterRepository.findById(1L);
        if (optionalNewsletter1.isPresent()) {
            newsletter = optionalNewsletter1.get();
        }
        Assertions.assertThat(newsletter).isNull();
    }


}
