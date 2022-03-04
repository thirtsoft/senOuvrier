package com.ouvriers.dtos;

import com.ouvriers.models.Ouvrier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OuvrierDto {

    private Long id;

    private String reference;

    private String firstName;

    private String lastName;

    private String sexe;

    private String addressActuel;

    private String disponibity;

    private boolean selected;

    private String email;

    private String phoneOuvrier;

    private String nbreAnneeExperience;

    private double pretentionSalaire;

    private String mobilite;

    private String photoOuvrier;

    private String cvOuvrier;


    private MetierDto metierDto;

    private AddresseDto addresseDto;

    public static OuvrierDto fromEntityToDto(Ouvrier ouvrier) {
        if (ouvrier == null) {
            return null;
        }

        return OuvrierDto.builder()
                .id(ouvrier.getId())
                .reference(ouvrier.getReference())
                .firstName(ouvrier.getFirstName())
                .lastName(ouvrier.getLastName())
                .sexe(ouvrier.getSexe())
                .email(ouvrier.getEmail())
                .phoneOuvrier(ouvrier.getPhoneOuvrier())
                .nbreAnneeExperience(ouvrier.getNbreAnneeExperience())
                .pretentionSalaire(ouvrier.getPretentionSalaire())
                .addressActuel(ouvrier.getAddressActuel())
                .disponibity(ouvrier.getDisponibity())
                .selected(ouvrier.isSelected())
                .cvOuvrier(ouvrier.getCvOuvrier())
                .mobilite(ouvrier.getMobilite())
                .photoOuvrier(ouvrier.getPhotoOuvrier())
                .metierDto(MetierDto.fromEntityToDto(ouvrier.getMetier()))
                .addresseDto(AddresseDto.fromEntityToDto(ouvrier.getAddresse()))
                .build();

    }

    public static Ouvrier fromDtoToEntity(OuvrierDto ouvrierDto) {
        if (ouvrierDto == null) {
            return null;
        }
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(ouvrierDto.getId());
        ouvrier.setReference(ouvrierDto.getReference());
        ouvrier.setFirstName(ouvrierDto.getFirstName());
        ouvrier.setLastName(ouvrierDto.getLastName());
        ouvrier.setSexe(ouvrierDto.getSexe());
        ouvrier.setEmail(ouvrierDto.getEmail());
        ouvrier.setPhoneOuvrier(ouvrierDto.getPhoneOuvrier());
        ouvrier.setNbreAnneeExperience(ouvrierDto.getNbreAnneeExperience());
        ouvrier.setPretentionSalaire(ouvrierDto.getPretentionSalaire());
        ouvrier.setCvOuvrier(ouvrierDto.getCvOuvrier());
        ouvrier.setAddressActuel(ouvrierDto.getAddressActuel());
        ouvrier.setDisponibity(ouvrierDto.getDisponibity());
        ouvrier.setSelected(ouvrierDto.isSelected());
        ouvrier.setMobilite(ouvrierDto.getMobilite());
        ouvrier.setPhotoOuvrier(ouvrierDto.getPhotoOuvrier());
        ouvrier.setMetier(MetierDto.fromDtoToEntity(ouvrierDto.getMetierDto()));
        ouvrier.setAddresse(AddresseDto.fromDtoToEntity(ouvrierDto.getAddresseDto()));

        return ouvrier;
    }


}
