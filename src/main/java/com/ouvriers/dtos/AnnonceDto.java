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

    private String modeCandidature;

    private String time;

    private int anneeExperience;

    private String description;

    private Date dateCandidature;

    private Date dateCloture;

    private StatusAnnonce statusAnnonce;

    private MetierDto metierDto;

    private RecruteurDto recruteurDto;

    private VilleDto villeDto;

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
                .description(annonce.getDescription())
                .dateCandidature(annonce.getCreatedDate())
                .dateCloture(annonce.getDateCloture())
                .statusAnnonce(annonce.getStatusAnnonce())
                .metierDto(MetierDto.fromEntityToDto(annonce.getMetier()))
                .recruteurDto(RecruteurDto.fromEntityToDto(annonce.getRecruteur()))
                .villeDto(VilleDto.fromEntityToDto(annonce.getVille()))
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
        annonce.setDescription(annonceDto.getDescription());
        annonce.setCreatedDate(annonceDto.getDateCandidature());
        annonce.setDateCloture(annonceDto.getDateCloture());
        annonce.setStatusAnnonce(annonceDto.getStatusAnnonce());
        annonce.setMetier(MetierDto.fromDtoToEntity(annonceDto.getMetierDto()));
        annonce.setRecruteur(RecruteurDto.fromDtoToEntity(annonceDto.getRecruteurDto()));
        annonce.setVille(VilleDto.fromDtoToEntity(annonceDto.getVilleDto()));

        return annonce;
    }
}
