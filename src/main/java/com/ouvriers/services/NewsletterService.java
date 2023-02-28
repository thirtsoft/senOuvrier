package com.ouvriers.services;

import com.ouvriers.models.Newsletter;

import java.util.List;

public interface NewsletterService {

    Newsletter save(Newsletter newsletter);

    Newsletter update(Long id, Newsletter newsletter);

    Newsletter findById(Long id);

    List<Newsletter> findAll();

    List<Newsletter> findAllNewslettersByOrderByIdDesc();

    long countNumbersOfNewsletters();

    void delete(Long id);
}
