package com.ouvriers.services;

import com.ouvriers.models.Newsletter;
import com.ouvriers.repository.NewsletterRepository;
import com.ouvriers.services.Impl.NewsletterServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class NewsletterServiceTest {

    @InjectMocks
    private NewsletterServiceImpl newsletterService;

    @Mock
    private NewsletterRepository newsletterRepository;

    @Test
    public void should_save_one_newsletter() {
        Newsletter newsletter = new Newsletter();
        newsletter.setId(1L);
        newsletter.setEmailVisiteur("user@gmail.com");
        newsletter.setMessage("Message");
        newsletter.setCreatedDate(new Date());

        when(newsletterRepository.save(any(Newsletter.class))).thenReturn(newsletter);

        Newsletter newsletterResult = newsletterService.save(new Newsletter());

        assertThat(newsletterResult).usingRecursiveComparison().isEqualTo(newsletter);
        verify(newsletterRepository, times(1)).save(any(Newsletter.class));
        verifyNoMoreInteractions(newsletterRepository);

    }

    @Test
    public void should_find_and_return_one_newsletter() {
        Newsletter newsletter = new Newsletter();
        newsletter.setId(1L);
        newsletter.setEmailVisiteur("user@gmail.com");
        newsletter.setMessage("Message");
        newsletter.setCreatedDate(new Date());

        when(newsletterRepository.findById(anyLong())).thenReturn(Optional.of(newsletter));

        Newsletter newsletter_find_Result = newsletterService.findById(anyLong());

        assertThat(newsletter_find_Result).usingRecursiveComparison().isEqualTo(newsletter);
        verify(newsletterRepository, times(1)).findById(anyLong());

    }

    @Test
    public void should_update_newsletter() {
        Newsletter newsletter = new Newsletter();
        newsletter.setId(1L);
        newsletter.setEmailVisiteur("user@gmail.com");
        newsletter.setMessage("Message");
        newsletter.setCreatedDate(new Date());

        when(newsletterRepository.findById(anyLong())).thenReturn(Optional.of(newsletter));

        Newsletter newsletter_find_Result = newsletterService.findById(anyLong());
        newsletter_find_Result.setEmailVisiteur("email@gmail.com");
        Newsletter newsletterUpdated = newsletterService.save(newsletter_find_Result);
        assertThat(newsletterUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_newsletters() {
        when(newsletterRepository.findAll()).thenReturn(singletonList(new Newsletter()));

        assertThat(newsletterService.findAll()).hasSize(1);
        verify(newsletterRepository, times(1)).findAll();
        verifyNoMoreInteractions(newsletterRepository);

    }

    @Test
    public void should_find_and_return_all_newsletters_by_IdDesc() {
        when(newsletterRepository.findListOfNewslettersByOrderByIdDesc()).thenReturn(singletonList(new Newsletter()));

        assertThat(newsletterService.findAllNewslettersByOrderByIdDesc()).hasSize(1);
        verify(newsletterRepository, times(1)).findListOfNewslettersByOrderByIdDesc();
        verifyNoMoreInteractions(newsletterRepository);
    }

    @Test
    public void should_delete_one_newsletter() {
        doNothing().when(newsletterRepository).deleteById(anyLong());

        newsletterService.delete(anyLong());
        verify(newsletterRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(newsletterRepository);
    }


}
