package com.ouvriers.dtos;

import com.ouvriers.models.WhistList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WhistListDto {

    private Long id;

    private float nbreEtoile;

    private String observation;

    private Date createdDate;

    private OuvrierDto ouvrierDto;

    private UtilisateurDto utilisateurDto;

    public static WhistListDto fromEntityToDto(WhistList whistList) {
        if (whistList == null) {
            return null;
        }

        return WhistListDto.builder()
                .id(whistList.getId())
                .nbreEtoile(whistList.getNbreEtoile())
                .observation(whistList.getObservation())
                .ouvrierDto(OuvrierDto.fromEntityToDto(whistList.getOuvrier()))
                .createdDate(whistList.getCreatedDate())
                .utilisateurDto(UtilisateurDto.fromEntityToDto(whistList.getUtilisateur()))
                .build();

    }

    public static WhistList fromDtoToEntity(WhistListDto whistListDto) {
        if (whistListDto == null) {
            return null;
        }
        WhistList whistList = new WhistList();
        whistList.setId(whistListDto.getId());
        whistList.setNbreEtoile(whistListDto.getNbreEtoile());
        whistList.setObservation(whistListDto.getObservation());
        whistList.setCreatedDate(whistListDto.getCreatedDate());
        whistList.setOuvrier(OuvrierDto.fromDtoToEntity(whistListDto.getOuvrierDto()));
        whistList.setUtilisateur(UtilisateurDto.fromDtoToEntity(whistListDto.getUtilisateurDto()));

        return whistList;
    }

}
