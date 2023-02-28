package com.ouvriers.controllers.api;

import com.ouvriers.models.HistoriqueLogin;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface HistoriqueLoginApi {

    @PostMapping(value = APP_ROOT + "/historiqueLogins/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une HistoriqueLogin",
            notes = "Cette méthode permet d'enregistrer une HistoriqueLogin", response = HistoriqueLogin.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'HistoriqueLogin a été crée"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueLogin  crée / modifié")

    })
    ResponseEntity<HistoriqueLogin> createHistoriqueLogin(@RequestBody HistoriqueLogin historiqueLogin);

    @PutMapping(value = APP_ROOT + "/historiqueLogins/update/{idHistoriqueLogin}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modofier une HistoriqueLogin  par ID",
            notes = "Cette méthode permet de modifier une HistoriqueLogin par ID", response = HistoriqueLogin.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'HistoriqueLogin a été modifié"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueLogin  modifié")

    })
    ResponseEntity<HistoriqueLogin> updateHistoriqueLogin(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin,
                                              @RequestBody HistoriqueLogin historiqueLogin);

    @GetMapping(value = APP_ROOT + "/historiqueLogins/findById/{idHistoriqueLogin}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueLogin par ID",
            notes = "Cette méthode permet de rechercher une HistoriqueLogin par son ID", response = HistoriqueLogin.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'HistoriqueLogin a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueLogin  avec cet ID")

    })
    ResponseEntity<HistoriqueLogin> getHistoriqueLoginById(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin);

    @GetMapping(value = APP_ROOT + "/historiqueLogins/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des HistoriqueLogins",
            notes = "Cette méthode permet d'afficher la liste des HistoriqueLogins",
            responseContainer = "List<HistoriqueLogin>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueLogins a été trouvé"),
            @ApiResponse(code = 400, message = "Liste vide")

    })
    ResponseEntity<List<HistoriqueLogin>> getAllHistoriqueLogins();

    @GetMapping(value = APP_ROOT + "/historiqueLogins/searchHistoriqueLoginByIdDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des HistoriqueLogins par order Décroissante",
            notes = "Cette méthode permet d'afficher la liste des HistoriqueLogins par order Décroissante",
            responseContainer = "List<HistoriqueLogin>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueLogins a été trouvé"),
            @ApiResponse(code = 400, message = "Liste vide")

    })
    ResponseEntity<List<HistoriqueLogin>> getAllHistoriqueLoginOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/historiqueLogins/searchTop30HistoriqueLoginByIdDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des HistoriqueLogins par order Décroissante",
            notes = "Cette méthode permet d'afficher la liste des HistoriqueLogins par order Décroissante",
            responseContainer = "List<HistoriqueLogin>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueLogins a été trouvé"),
            @ApiResponse(code = 400, message = "Liste vide")

    })
    ResponseEntity<List<HistoriqueLogin>> getTop30HistoriqueLoginOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/historiqueLogins/NumbersOfHistoriqueLogins")
    @ApiOperation(value = "Décompter le nombre d'HistoriqueLogin",
            notes = "Cette méthode permet de compter et d'afficher le nombre total d'HistoriqueLogins",
            response = HistoriqueLogin.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'HistoriqueLogin"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    long getNumbersOfHistoriqueLogins();

    @DeleteMapping(value = APP_ROOT + "/historiqueLogins/delete/{idHistoriqueLogin}")
    @ApiOperation(value = "Supprimer une HistoriqueLogin par son ID",
            notes = "Cette méthode permet de supprimer une HistoriqueLogin par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'HistoriqueLogin a été supprimé")

    })
    void deleteHistoriqueLogin(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin);

}
