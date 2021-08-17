package com.ouvriers.dtos;

import com.ouvriers.models.Metier;
import com.ouvriers.models.WhistList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WhistListDto {

    private Long id;

    private String reference;

    private String nbreEtoile;

    private String observation;

    private OuvrierDto ouvrierDto;


    public static WhistListDto fromEntityToDto(WhistList whistList) {
        if (whistList == null) {
            return null;
        }

        return WhistListDto.builder()
                .id(whistList.getId())
                .reference(whistList.getReference())
                .nbreEtoile(whistList.getNbreEtoile())
                .observation(whistList.getObservation())
                .ouvrierDto(OuvrierDto.fromEntityToDto(whistList.getOuvrier()))
                .build();

    }

    public static WhistList fromDtoToEntity(WhistListDto whistListDto) {
        if (whistListDto == null) {
            return null;
        }
        WhistList whistList = new WhistList();
        whistList.setId(whistListDto.getId());
        whistList.setReference(whistListDto.getReference());
        whistList.setNbreEtoile(whistListDto.getNbreEtoile());
        whistList.setObservation(whistListDto.getObservation());
        whistList.setOuvrier(OuvrierDto.fromDtoToEntity(whistListDto.getOuvrierDto()));

        return whistList;
    }

}
