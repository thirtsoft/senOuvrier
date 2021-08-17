package com.ouvriers.dtos;

import com.ouvriers.models.Metier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetierDto {

    private Long id;

    private String reference;

    private String designation;

    private String description;

    public static MetierDto fromEntityToDto(Metier metier) {
        if (metier == null) {
            return null;
        }

        return MetierDto.builder()
                .id(metier.getId())
                .reference(metier.getReference())
                .designation(metier.getDesignation())
                .description(metier.getDescription())
                .build();

    }

    public static Metier fromDtoToEntity(MetierDto metierDto) {
        if (metierDto == null) {
            return null;
        }
        Metier metier = new Metier();
        metier.setId(metierDto.getId());
        metier.setReference(metierDto.getReference());
        metier.setDesignation(metierDto.getDesignation());
        metier.setDescription(metierDto.getDescription());

        return metier;
    }

}
