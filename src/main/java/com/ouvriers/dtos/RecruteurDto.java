package com.ouvriers.dtos;


import com.ouvriers.models.Recruteur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruteurDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String nomEntreprise;

    private String addressRecruteur;

    private String email;

    private String phoneRecruteur;

    private String website;

    private String secteurActivite;

    private String villeRecruteur;

    private String information;

    public static RecruteurDto fromEntityToDto(Recruteur recruteur) {
        if (recruteur == null) {
            return null;
        }

        return RecruteurDto.builder()
                .id(recruteur.getId())
                .firstName(recruteur.getFirstName())
                .lastName(recruteur.getLastName())
                .addressRecruteur(recruteur.getAddressRecruteur())
                .phoneRecruteur(recruteur.getPhoneRecruteur())
                .email(recruteur.getEmail())
                .nomEntreprise(recruteur.getNomEntreprise())
                .secteurActivite(recruteur.getSecteurActivite())
                .website(recruteur.getWebsite())
                .villeRecruteur(recruteur.getVilleRecruteur())
                .information(recruteur.getInformation())

                .build();

    }

    public static Recruteur fromDtoToEntity(RecruteurDto recruteurDto) {
        if (recruteurDto == null) {
            return null;
        }
        Recruteur recruteur = new Recruteur();
        recruteur.setId(recruteurDto.getId());
        recruteur.setFirstName(recruteurDto.getFirstName());
        recruteur.setLastName(recruteurDto.getLastName());
        recruteur.setAddressRecruteur(recruteurDto.getAddressRecruteur());
        recruteur.setPhoneRecruteur(recruteurDto.getPhoneRecruteur());
        recruteur.setEmail(recruteurDto.getEmail());
        recruteur.setNomEntreprise(recruteurDto.getNomEntreprise());
        recruteur.setSecteurActivite(recruteurDto.getSecteurActivite());
        recruteur.setWebsite(recruteurDto.getWebsite());
        recruteur.setVilleRecruteur(recruteurDto.getVilleRecruteur());
        recruteur.setInformation(recruteurDto.getInformation());

        return recruteur;
    }

}
