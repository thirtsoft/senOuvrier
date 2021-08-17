package com.ouvriers.dtos;

import com.ouvriers.models.Addresse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddresseDto {
    private Long id;

    private String reference;

    private String quartier;

    private String rue;

    private String codePostal;

    private String ville;

    private String pays;

    public static AddresseDto fromEntityToDto(Addresse addresse) {
        if (addresse == null) {
            return null;
        }

        return AddresseDto.builder()
                .id(addresse.getId())
                .reference(addresse.getReference())
                .quartier(addresse.getQuartier())
                .rue(addresse.getRue())
                .codePostal(addresse.getCodePostal())
                .ville(addresse.getVille())
                .pays(addresse.getPays())
                .build();

    }

    public static Addresse fromDtoToEntity(AddresseDto addresseDto) {
        if (addresseDto == null) {
            return null;
        }
        Addresse addresse = new Addresse();
        addresse.setId(addresseDto.getId());
        addresse.setReference(addresseDto.getReference());
        addresse.setQuartier(addresseDto.getQuartier());
        addresse.setQuartier(addresseDto.getQuartier());
        addresse.setRue(addresseDto.getReference());
        addresse.setCodePostal(addresseDto.getCodePostal());
        addresse.setVille(addresseDto.getVille());
        addresse.setPays(addresseDto.getPays());

        return addresse;
    }


}
