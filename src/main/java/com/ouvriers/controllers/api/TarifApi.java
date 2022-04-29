package com.ouvriers.controllers.api;

import com.ouvriers.models.Tarif;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface TarifApi {

    @PostMapping(value = APP_ROOT + "/tarifs/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Tarif",
            notes = "Cette méthode permet d'enregistrer un Tarif", response = Tarif.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Tarif a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Tarif  crée / modifié")

    })
    ResponseEntity<Tarif> save(@RequestBody Tarif tarif);

    @PutMapping(value = "/tarifs/{idTarif}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Tarif par son ID",
            notes = "Cette méthode permet de modifier un Tarif par son ID", response = Tarif.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Tarif a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Tarif modifié")
    })
    ResponseEntity<Tarif> update(@PathVariable("idTarif") Long id, @RequestBody Tarif tarif);

    @GetMapping(value = APP_ROOT + "/tarifs/{idTarif}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Tarif par ID",
            notes = "Cette méthode permet de chercher un Tarif par son ID", response = Tarif.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarif a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Tarif n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Tarif> getTarifById(@PathVariable("idTarif") Long id);

    @GetMapping(value = "/tarifs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des tarifs",
            notes = "Cette méthode permet de chercher et renvoyer la liste des tarifs", responseContainer = "List<Tarif>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des tarifs / une liste vide")
    })
    ResponseEntity<List<Tarif>> getAllTarifs();

    @GetMapping(value = "/tarifs/searchAllTarifsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des tarifs",
            notes = "Cette méthode permet de chercher et renvoyer la liste des tarifs", responseContainer = "List<Tarif>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des tarifs / une liste vide")
    })
    ResponseEntity<List<Tarif>> getAllTarifsOrderByIdDesc();


    @DeleteMapping(value = APP_ROOT + "/tarifs/delete/{idTaf}")
    @ApiOperation(value = "Supprimer un Notification par son ID",
            notes = "Cette méthode permet de supprimer un Notification par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Notification a été supprimé")

    })
    void delete(@PathVariable("idTaf") Long id);



}
