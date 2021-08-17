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

    private String email;

    private String phoneChauffeur;

    private Integer nbreAnneeExperience;

    private Double pretentionSalaire;

    private String cvChauffeur;

    private String mobilite;

    private String photoChauffeur;

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
                .phoneChauffeur(ouvrier.getPhoneOuvrier())
                .nbreAnneeExperience(ouvrier.getNbreAnneeExperience())
                .pretentionSalaire(ouvrier.getPretentionSalaire())
                .addressActuel(ouvrier.getAddressActuel())
                .disponibity(ouvrier.getDisponibity())
                .cvChauffeur(ouvrier.getCvOuvrier())
                .mobilite(ouvrier.getMobilite())
                .photoChauffeur(ouvrier.getPhotoOuvrier())
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
        ouvrier.setPhoneOuvrier(ouvrierDto.getPhoneChauffeur());
        ouvrier.setNbreAnneeExperience(ouvrierDto.getNbreAnneeExperience());
        ouvrier.setPretentionSalaire(ouvrierDto.getPretentionSalaire());
        ouvrier.setCvOuvrier(ouvrierDto.getCvChauffeur());
        ouvrier.setAddressActuel(ouvrierDto.getAddressActuel());
        ouvrier.setDisponibity(ouvrierDto.getDisponibity());
        ouvrier.setMobilite(ouvrierDto.getMobilite());
        ouvrier.setPhotoOuvrier(ouvrierDto.getPhotoChauffeur());
        ouvrier.setMetier(MetierDto.fromDtoToEntity(ouvrierDto.getMetierDto()));
        ouvrier.setAddresse(AddresseDto.fromDtoToEntity(ouvrierDto.getAddresseDto()));

        return ouvrier;
    }


}
