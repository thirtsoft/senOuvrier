package com.ouvriers.dtos;

import com.ouvriers.enums.StatusAnnonce;
import com.ouvriers.models.Annonce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceDto {

    private Long id;

    private String reference;

    private String libelle;

    private String lieuPoste;

    private String emailPoste;

    private String salaire;

    private String time;

    private String anneeExperience;

    private String typeContrat;

    private boolean selected;

    private String status;

    private String description;

    private Date dateCandidature;

    private Date dateCloture;

    private StatusAnnonce statusAnnonce;

    private MetierDto metierDto;

    //   private RecruteurDto recruteurDto;

    //   private VilleDto villeDto;

    private AddresseDto addresseDto;

    private UtilisateurDto utilisateurDto;

    public static AnnonceDto fromEntityToDto(Annonce annonce) {
        if (annonce == null) {
            return null;
        }

        return AnnonceDto.builder()
                .id(annonce.getId())
                .reference(annonce.getReference())
                .libelle(annonce.getLibelle())
                .lieuPoste(annonce.getLieuPoste())
                .salaire(annonce.getSalaire())
                .emailPoste(annonce.getEmailPoste())
                .time(annonce.getTime())
                .anneeExperience(annonce.getAnneeExperience())
                .typeContrat(annonce.getTypeContrat())
                .selected(annonce.isSelected())
                .status(annonce.getStatus())
                .description(annonce.getDescription())
                .dateCandidature(annonce.getCreatedDate())
                .dateCloture(annonce.getDateCloture())
                .statusAnnonce(annonce.getStatusAnnonce())
                .metierDto(MetierDto.fromEntityToDto(annonce.getMetier()))
                .utilisateurDto(UtilisateurDto.fromEntityToDto(annonce.getUtilisateur()))
                .addresseDto(AddresseDto.fromEntityToDto(annonce.getAddresse()))
                .build();

    }

    public static Annonce fromDtoToEntity(AnnonceDto annonceDto) {
        if (annonceDto == null) {
            return null;
        }
        Annonce annonce = new Annonce();
        annonce.setId(annonceDto.getId());
        annonce.setReference(annonceDto.getReference());
        annonce.setLibelle(annonceDto.getLibelle());
        annonce.setLieuPoste(annonceDto.getLieuPoste());
        annonce.setSalaire(annonceDto.getSalaire());
        annonce.setTime(annonceDto.getTime());
        annonce.setEmailPoste(annonceDto.getEmailPoste());
        annonce.setAnneeExperience(annonce.getAnneeExperience());
        annonce.setTypeContrat(annonceDto.getTypeContrat());
        annonce.setSelected(annonceDto.isSelected());
        annonce.setStatus(annonceDto.getStatus());
        annonce.setDescription(annonceDto.getDescription());
        annonce.setCreatedDate(annonceDto.getDateCandidature());
        annonce.setDateCloture(annonceDto.getDateCloture());
        annonce.setStatusAnnonce(annonceDto.getStatusAnnonce());
        annonce.setMetier(MetierDto.fromDtoToEntity(annonceDto.getMetierDto()));
        annonce.setUtilisateur(UtilisateurDto.fromDtoToEntity(annonceDto.getUtilisateurDto()));
        annonce.setAddresse(AddresseDto.fromDtoToEntity(annonceDto.getAddresseDto()));


        return annonce;
    }
}
