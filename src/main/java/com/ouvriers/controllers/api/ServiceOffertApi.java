package com.ouvriers.controllers.api;

import com.ouvriers.models.ServiceOffert;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface ServiceOffertApi {

    @PostMapping(value = APP_ROOT + "/serviceOfferts/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un ServiceOffert",
            notes = "Cette méthode permet d'enregistrer un ServiceOffert", response = ServiceOffert.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'ServiceOffert a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun ServiceOffert  crée / modifié")

    })
    ResponseEntity<ServiceOffert> save(@RequestBody ServiceOffert serviceOffert);

    @PutMapping(value = APP_ROOT + "/serviceOfferts/update/{idServiceOffert}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un ServiceOffert par son ID",
            notes = "Cette méthode permet de modifier un ServiceOffert par son ID", response = ServiceOffert.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'ServiceOffert a été modifié"),
            @ApiResponse(code = 400, message = "Aucun ServiceOffert modifié")
    })
    ResponseEntity<ServiceOffert> update(@PathVariable("idServiceOffert") Long id, @RequestBody ServiceOffert serviceOffert);

    @GetMapping(value = APP_ROOT + "/serviceOfferts/findById/{idServiceOffert}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un ServiceOffert par ID",
            notes = "Cette méthode permet de chercher un ServiceOffert par son ID", response = ServiceOffert.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ServiceOffert a été trouver"),
            @ApiResponse(code = 404, message = "Aucun ServiceOffert n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<ServiceOffert> getServiceOffertById(@PathVariable("idServiceOffert") Long id);

    @GetMapping(value = APP_ROOT + "/serviceOfferts/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des serviceOfferts",
            notes = "Cette méthode permet de chercher et renvoyer la liste des serviceOfferts", responseContainer = "List<ServiceOffert>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des serviceOfferts / une liste vide")
    })
    ResponseEntity<List<ServiceOffert>> getAllServiceOfferts();

    @GetMapping(value = APP_ROOT + "/serviceOfferts/searchAllServiceOffertsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des serviceOfferts",
            notes = "Cette méthode permet de chercher et renvoyer la liste des serviceOfferts", responseContainer = "List<ServiceOffert>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des serviceOfferts / une liste vide")
    })
    ResponseEntity<List<ServiceOffert>> getAllServiceOffertsOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/serviceOfferts/searchAllServiceOffertsByOuvrierId/{ouvId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des serviceOfferts par l'ID de l'ouvrier",
            notes = "Cette méthode permet de chercher et renvoyer la liste des serviceOfferts par l'ID de l'ouvrier", responseContainer = "List<ServiceOffert>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des prestations par l'ID de l'ouvrier / une liste vide")
    })
    ResponseEntity<List<ServiceOffert>> getAllServiceOffertByOuvrierId(@PathVariable(name = "ouvId") Long ouvId);


    @DeleteMapping(value = APP_ROOT + "/serviceOfferts/delete/{idServiceOffert}")
    @ApiOperation(value = "Supprimer un Notification par son ID",
            notes = "Cette méthode permet de supprimer un Notification par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Notification a été supprimé")

    })
    void delete(@PathVariable("idServiceOffert") Long id);

}
