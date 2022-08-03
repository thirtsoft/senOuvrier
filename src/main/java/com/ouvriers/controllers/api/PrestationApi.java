package com.ouvriers.controllers.api;

import com.ouvriers.models.Prestation;
import com.ouvriers.models.Prestation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface PrestationApi {

    @PostMapping(value = APP_ROOT + "/prestations/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Prestation",
            notes = "Cette méthode permet d'enregistrer un Prestation", response = Prestation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Prestation a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Prestation  crée / modifié")

    })
    ResponseEntity<Prestation> create(@RequestBody Prestation prestation);

    @PutMapping(value = "/prestations/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Prestation par son ID",
            notes = "Cette méthode permet de modifier un Prestation par son ID", response = Prestation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Prestation a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Prestation modifié")
    })
    ResponseEntity<Prestation> update(@PathVariable("idPrestation") Long id, @RequestBody Prestation prestation);

    @GetMapping(value = APP_ROOT + "/prestations/findById/{idPrestation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Prestation par ID",
            notes = "Cette méthode permet de chercher un Prestation par son ID", response = Prestation.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Prestation a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Prestation n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Prestation> getPrestationById(@PathVariable("idPrestation") Long id);

    @GetMapping(value = APP_ROOT + "/prestations/countNumberOfPrestationByOuvrierId/{idOuv}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter le nombre de Prestations d'un chauffeur",
            notes = "Cette méthode permet de compter et d'afficher le nombre total de Prestations d'un chauffeur",
            response = Prestation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Prestations"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    BigDecimal getNumberOfPrestationByOuvrierId(@PathVariable("idOuv") Long id);

    @GetMapping(value = APP_ROOT + "/prestations/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des prestations",
            notes = "Cette méthode permet de chercher et renvoyer la liste des prestations", responseContainer = "List<Prestation>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des prestations / une liste vide")
    })
    ResponseEntity<List<Prestation>> getAllPrestations();

    @GetMapping(value = APP_ROOT + "/prestations/searchAllPrestationOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des prestations",
            notes = "Cette méthode permet de chercher et renvoyer la liste des prestations", responseContainer = "List<Prestation>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des prestations / une liste vide")
    })
    ResponseEntity<List<Prestation>> getAllPrestationsOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/prestations/searchAllPrestationsByOuvrierId/{ouvId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des prestations par l'ID de l'ouvrier",
            notes = "Cette méthode permet de chercher et renvoyer la liste des prestations par l'ID de l'ouvrier", responseContainer = "List<Prestation>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des prestations par l'ID de l'ouvrier / une liste vide")
    })
    ResponseEntity<List<Prestation>> getAllPrestationsByOuvrierId(@PathVariable(name = "ouvId") Long ouvId);

    @GetMapping(value = APP_ROOT + "/prestations/searchTop4PrestationsByOuvrierId/{ouvId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des prestations par l'ID de l'ouvrier",
            notes = "Cette méthode permet de chercher et renvoyer la liste des prestations par l'ID de l'ouvrier", responseContainer = "List<Prestation>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des prestations par l'ID de l'ouvrier / une liste vide")
    })
    ResponseEntity<List<Prestation>> getTop4PrestationByOuvrierIdOrderByCreatedDateDesc(@PathVariable(name = "ouvId") Long ouvId);

    @DeleteMapping(value = APP_ROOT + "/prestations/delete/{idPrestation}")
    @ApiOperation(value = "Supprimer un Prestation par son ID",
            notes = "Cette méthode permet de supprimer un Prestation par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Prestation a été supprimé")

    })
    void delete(@PathVariable("idPrestation") Long id);

}
