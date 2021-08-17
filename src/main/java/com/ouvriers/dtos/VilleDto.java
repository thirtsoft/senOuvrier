package com.ouvriers.dtos;

import com.ouvriers.models.Ville;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VilleDto {

    private Long id;

    private String reference;

    private String nom;

    private String pays;


    public static VilleDto fromEntityToDto(Ville ville) {
        if (ville == null) {
            return null;
        }

        return VilleDto.builder()
                .id(ville.getId())
                .reference(ville.getReference())
                .nom(ville.getNom())
                .pays(ville.getPays())
                .build();

    }

    public static Ville fromDtoToEntity(VilleDto villeDto) {
        if (villeDto == null) {
            return null;
        }
        Ville ville = new Ville();
        ville.setId(villeDto.getId());
        ville.setReference(villeDto.getReference());
        ville.setNom(villeDto.getNom());
        ville.setPays(villeDto.getPays());

        return ville;
    }

}
