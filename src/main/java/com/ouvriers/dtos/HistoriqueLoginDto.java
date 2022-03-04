package com.ouvriers.dtos;

import com.ouvriers.models.HistoriqueLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoriqueLoginDto {

    private Long id;

    private String action;

    private Date createdDate;

    private UtilisateurDto utilisateurDto;


    public static HistoriqueLoginDto fromEntityToDto(HistoriqueLogin historiqueLogin) {
        if (historiqueLogin == null) {
            return null;
        }

        return HistoriqueLoginDto.builder()
                .id(historiqueLogin.getId())
                .action(historiqueLogin.getAction())
                .createdDate(historiqueLogin.getCreatedDate())
                .utilisateurDto(UtilisateurDto.fromEntityToDto(historiqueLogin.getUtilisateur()))
                .build();

    }

    public static HistoriqueLogin fromDtoToEntity(HistoriqueLoginDto historiqueLoginDto) {
        if (historiqueLoginDto == null) {
            return null;
        }
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setId(historiqueLoginDto.getId());
        historiqueLogin.setAction(historiqueLoginDto.getAction());
        historiqueLogin.setCreatedDate(historiqueLoginDto.getCreatedDate());
        historiqueLogin.setUtilisateur(UtilisateurDto.fromDtoToEntity(historiqueLoginDto.getUtilisateurDto()));

        return historiqueLogin;
    }


}
