package com.ouvriers.controllers.api;

import com.ouvriers.models.HistoriqueAnnonce;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface HistoriqueAnnonceApi {

    @PostMapping(value = APP_ROOT + "/historiqueAnnonces/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une HistoriqueAnnonce",
            notes = "Cette méthode permet d'enregistrer une HistoriqueAnnonce", response = HistoriqueAnnonce.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'HistoriqueAnnonce a été crée"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueAnnonce  crée / modifié")

    })
    ResponseEntity<HistoriqueAnnonce> createHistoriqueAnnocne(@RequestBody HistoriqueAnnonce historiqueAnnonce);

    @PutMapping(value = APP_ROOT + "/historiqueAnnonces/update/{idHistoriqueAnnonce}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modofier une HistoriqueAnnonce  par ID",
            notes = "Cette méthode permet de modifier une HistoriqueAnnonce par ID", response = HistoriqueAnnonce.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'HistoriqueAnnonce a été modifié"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueAnnonce  modifié")

    })
    ResponseEntity<HistoriqueAnnonce> updateHistoriqueAnnonce(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce,
                                                @RequestBody HistoriqueAnnonce historiqueAnnonce);

    @GetMapping(value = APP_ROOT + "/historiqueAnnonces/findById/{idHistoriqueAnnonce}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueAnnonce par ID",
            notes = "Cette méthode permet de rechercher une HistoriqueAnnonce par son ID", response = HistoriqueAnnonce.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'HistoriqueAnnonce a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueAnnonce  avec cet ID")

    })
    ResponseEntity<HistoriqueAnnonce> getHistoriqueAnnonceById(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce);

    @GetMapping(value = APP_ROOT + "/historiqueAnnonces/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des HistoriqueAnnonces",
            notes = "Cette méthode permet d'afficher la liste des HistoriqueAnnonces", responseContainer = "List<HistoriqueAnnonce>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueAnnonce a été trouvé"),
            @ApiResponse(code = 400, message = "Liste vide")

    })
    ResponseEntity<List<HistoriqueAnnonce>> getAllHistoriqueAnnonces();

    @GetMapping(value = APP_ROOT + "/historiqueAnnonces/searchHistoriqueAnnonceByIdDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des HistoriqueAnnonces par order Décroissante",
            notes = "Cette méthode permet d'afficher la liste des HistoriqueAnnonces par order Décroissante",
            responseContainer = "List<HistoriqueAnnonce>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueAnnonce a été trouvé"),
            @ApiResponse(code = 400, message = "Liste vide")

    })
    ResponseEntity<List<HistoriqueAnnonce>> getAllHistoriqueAnnoncesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/historiqueAnnonces/NumbersOfhistoriqueAnnonces")
    @ApiOperation(value = "Décompter le nombre d'HistoriqueAnnonces",
            notes = "Cette méthode permet de compter et d'afficher le nombre total d'HistoriqueAnnonces", response = HistoriqueAnnonce.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'HistoriqueAnnonces"),
            @ApiResponse(code = 400, message = "Aucun liste HistoriqueAnnonces")

    })
    long getNumbersOfhistoriqueAnnonces();

    @DeleteMapping(value = APP_ROOT + "/historiqueAnnonces/delete/{idHistoriqueAnnonce}")
    @ApiOperation(value = "Supprimer une HistoriqueAnnonces par son ID",
            notes = "Cette méthode permet de supprimer une HistoriqueAnnonces par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'HistoriqueAnnonces a été supprimé")

    })
    void deleteHistoriqueAnnonce(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce);

}
