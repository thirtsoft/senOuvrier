package com.ouvriers.dtos;

import com.ouvriers.models.Jeton;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JetonDto {

    private Long id;

    private float montant;

    private String etat;

    private Date createdDate;

    private UtilisateurDto utilisateurDto;

    public static JetonDto fromEntityToDto(Jeton jeton) {
        if (jeton == null) {
            return null;
        }

        return JetonDto.builder()
                .id(jeton.getId())
                .montant(jeton.getMontant())
                .etat(jeton.getEtat())
                .createdDate(jeton.getCreatedDate())
                .utilisateurDto(UtilisateurDto.fromEntityToDto(jeton.getUtilisateur()))
                .build();

    }

    public static Jeton fromDtoToEntity(JetonDto jetonDto) {
        if (jetonDto == null) {
            return null;
        }

        Jeton jeton = new Jeton();
        jeton.setId(jetonDto.getId());
        jeton.setMontant(jetonDto.getMontant());
        jeton.setEtat(jetonDto.getEtat());
        jeton.setCreatedDate(jetonDto.getCreatedDate());
        jeton.setUtilisateur(UtilisateurDto.fromDtoToEntity(jetonDto.getUtilisateurDto()));

        return jeton;
    }


}
