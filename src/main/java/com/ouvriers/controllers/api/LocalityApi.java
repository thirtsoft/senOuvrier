package com.ouvriers.controllers.api;

import com.ouvriers.models.Locality;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface LocalityApi {

    @PostMapping(value = APP_ROOT + "/localities/create",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Locality",
            notes = "Cette méthode permet d'ajouter un Locality", response = Locality.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Locality a été crée"),
            @ApiResponse(code = 400, message = "Aucun State  crée / modifié")

    })
    ResponseEntity<Locality> saveLocality(@RequestBody Locality locality);

    @PutMapping(value = APP_ROOT + "/localities/update/{locId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Locality",
            notes = "Cette méthode permet de modifier une Locality", response = Locality.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Locality a été crée"),
            @ApiResponse(code = 400, message = "Aucun Locality  crée / modifié")

    })
    ResponseEntity<Locality> updateLocality(@PathVariable(value = "locId") Long locId, @RequestBody Locality locality);

    @GetMapping(value = APP_ROOT + "/localities/findById/{locId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Chercher une Locality",
            notes = "Cette méthode permet de chercher et de renvoyer une Locality", response = Locality.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le State a été trouvé")

    })
    ResponseEntity<Locality> getLocalityById(@PathVariable(value = "stateId") Long locId);

    @GetMapping(value = APP_ROOT + "/localities/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des State",
            notes = "Cette méthode permet de chercher et renvoyer la liste des State", responseContainer = "List<Locality>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des State / une liste vide")
    })
    ResponseEntity<List<Locality>> getAllLocalities();

    @GetMapping(value = APP_ROOT + "/localities/searchAlllocalitiesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des State par ordre decroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des State par ordre decroissante", responseContainer = "List<Locality>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des State / une liste vide")
    })
    ResponseEntity<List<Locality>> getAllLocalitiesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/localities/searchLocalityByAddressCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des localities",
            notes = "Cette méthode permet de chercher et renvoyer la liste des localities", responseContainer = "List<Locality>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des localities / une liste vide")
    })
    ResponseEntity<List<Locality>> getAllLocalityByCountryCode(@RequestParam(name = "code") String code);

    @DeleteMapping(value = APP_ROOT + "/localities/delete/{stateId}")
    @ApiOperation(value = "Supprimer un Locality par son ID",
            notes = "Cette méthode permet de supprimer une Country par son ID", response = Locality.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le State a été supprimé")
    })
    void deleteState(@PathVariable(name = "stateId") Long stateId);


}
