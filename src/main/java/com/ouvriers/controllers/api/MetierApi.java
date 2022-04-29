package com.ouvriers.controllers.api;

import com.ouvriers.models.Metier;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface MetierApi {

    @PostMapping(value = APP_ROOT + "/metiers/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Metier",
            notes = "Cette méthode permet d'enregistrer un Metier", response = Metier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Metier a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Metier  crée / modifié")

    })
    ResponseEntity<Metier> save(@RequestBody Metier metier);

    @PutMapping(value = "/metiers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Metier par son ID",
            notes = "Cette méthode permet de modifier un Metier par son ID", response = Metier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Metier a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Metier modifié")
    })
    ResponseEntity<Metier> update(@PathVariable("idMetier") Long id, @RequestBody Metier metier);

    @GetMapping(value = APP_ROOT + "/metiers/findById/{idMetier}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Metier par ID",
            notes = "Cette méthode permet de chercher un Metier par son ID", response = Metier.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Metier a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Metier n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Metier> getMetierById(@PathVariable("idMetier") Long id);

    @GetMapping(value = "/metiers/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des metiers",
            notes = "Cette méthode permet de chercher et renvoyer la liste des metiers", responseContainer = "List<Metier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des metiers / une liste vide")
    })
    ResponseEntity<List<Metier>> getAllMetiers();

    @GetMapping(value = APP_ROOT + "/metiers/searchMetiersOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des Metier par ordre Décroissante",
            notes = "Cette méthode permet d'afficher la liste des Metier par ordre Décroissante",
            responseContainer = "List<Metier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Metier a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste Permis")

    })
    ResponseEntity<List<Metier>> getAllMetiersOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/metiers/NumbersOfmetiers")
    @ApiOperation(value = "Renvoi le nombre total d'metiers",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'metiers de la plateforme",
            responseContainer = "List<Metier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'metiers / une liste vide")
    })
    long getNumbersOfMetiers();

}
