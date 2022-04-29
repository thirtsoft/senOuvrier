package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.Newsletter;
import com.ouvriers.repository.NewsletterRepository;
import com.ouvriers.services.NewsletterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public Newsletter save(Newsletter newsletter) {
        return newsletterRepository.save(newsletter);
    }

    @Override
    public Newsletter update(Long id, Newsletter newsletter) {
        if (!newsletterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Newsletter not found");
        }

        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(id);

        if (!optionalNewsletter.isPresent()) {
            throw new ResourceNotFoundException("Newsletter not found");
        }

        Newsletter newsletterResult = optionalNewsletter.get();
        newsletterResult.setEmailVisiteur(newsletter.getEmailVisiteur());
        newsletterResult.setCreatedDate(newsletter.getCreatedDate());

        return newsletterRepository.save(newsletterResult);
    }

    @Override
    public Newsletter findById(Long id) {
        if (id == null) {
            log.error("Newsletter Id is null");
            return null;
        }

        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(id);

        return Optional.of(optionalNewsletter.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Newsletter avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<Newsletter> findAll() {
        return newsletterRepository.findAll();
    }

    @Override
    public List<Newsletter> findAllNewslettersByOrderByIdDesc() {
        return newsletterRepository.findListOfNewslettersByOrderByIdDesc();
    }

    @Override
    public long countNumbersOfNewsletters() {
        return newsletterRepository.count();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Newsletter not found");
            return;
        }
        newsletterRepository.deleteById(id);
    }
}
