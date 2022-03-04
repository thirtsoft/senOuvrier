package com.ouvriers.services;

import com.ouvriers.dtos.NewsletterDto;

import java.math.BigDecimal;
import java.util.List;

public interface NewsletterService {

    NewsletterDto save(NewsletterDto newsletterDto);

    NewsletterDto update(Long id, NewsletterDto newsletterDto);

    NewsletterDto findById(Long id);

    List<NewsletterDto> findAll();

    List<NewsletterDto> findAllNewslettersByOrderByIdDesc();

    BigDecimal countNumbersOfNewsletters();

    void delete(Long id);
}
