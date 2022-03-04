package com.ouvriers.dtos;

import com.ouvriers.models.Newsletter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsletterDto {

    private Long id;

    private String emailVisiteur;

    private Date createdDate;

    public static NewsletterDto fromEntityToDto(Newsletter newsletter) {
        if (newsletter == null) {
            return null;
        }
        return NewsletterDto.builder()
                .id(newsletter.getId())
                .emailVisiteur(newsletter.getEmailVisiteur())
                .createdDate(newsletter.getCreatedDate())
                .build();
    }

    public static Newsletter fromDtoToEntity(NewsletterDto newsletterDto) {
        if (newsletterDto == null) {
            return null;
        }
        Newsletter newslleter = new Newsletter();
        newslleter.setId(newsletterDto.getId());
        newslleter.setEmailVisiteur(newsletterDto.getEmailVisiteur());
        newslleter.setCreatedDate(newsletterDto.createdDate);

        return newslleter;
    }


}
