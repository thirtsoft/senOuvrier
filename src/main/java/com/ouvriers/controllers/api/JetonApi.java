package com.ouvriers.controllers.api;

import com.ouvriers.models.Jeton;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface JetonApi {

    @PostMapping(value = APP_ROOT + "/jetons/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Jeton",
            notes = "Cette méthode permet d'ajouter un nouveau Jeton", response = Jeton.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Jeton a été crée"),
            @ApiResponse(code = 400, message = "Aucun Jeton  crée")

    })
    ResponseEntity<Jeton> createJeton(@RequestBody Jeton jeton);

    /*
    @PutMapping(value = APP_ROOT + "/jetons/update/{idJeton}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    */
    @PatchMapping(value = APP_ROOT + "/jetons/update/{idJeton}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modofier un Jeton  par ID",
            notes = "Cette méthode permet de modifier un Jeton par ID", response = Jeton.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Jeton a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Jeton  modifié")

    })
    ResponseEntity<Jeton> updateJeton(@PathVariable("idJeton") Long idJeton, @RequestBody Jeton jeton);

    @PatchMapping(value = APP_ROOT + "/jetons/updateEtatOfJeton/{id}")
    @ApiOperation(value = "Modofier l'etat d'un Jeton",
            notes = "Cette méthode permet de modifier le status une Jeton", response = Jeton.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le satus de Jeton a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Jeton  modifié")

    })
    ResponseEntity<Jeton> updateEtatOfJeton(@RequestParam("etat") String etat, @PathVariable("id") String id);

    @GetMapping(value = APP_ROOT + "/jetons/findById/{idJeton}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Jeton par ID",
            notes = "Cette méthode permet de rechercher un Jeton par son ID", response = Jeton.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Jeton a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun Jeton  avec cet ID")

    })
    ResponseEntity<Jeton> getJetonById(@PathVariable("idJeton") Long idJeton);

    @GetMapping(value = APP_ROOT + "/jetons/sumTotalOfJetonInYear", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher le montant total des Jeton dans une annee",
            notes = "Cette méthode permet de rechercher le montant total des Jeton dans une annee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le montant total des Jeton dans une annee est "),
            @ApiResponse(code = 400, message = "Aucun Jeton  avec cet ID")

    })
    BigDecimal getSumTotalOfJetonInYear();

    @GetMapping(value = APP_ROOT + "/jetons/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des Jetons",
            notes = "Cette méthode permet d'afficher la liste des Jetonsr", responseContainer = "List<Jeton>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Jetons a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste Jetons")

    })
    ResponseEntity<List<Jeton>> getAllJetons();

    @GetMapping(value = APP_ROOT + "/jetons/searchJetonsByIdDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des Jetons par ordre Décroissante",
            notes = "Cette méthode permet d'afficher la liste des Jetons par ordre Décroissante", responseContainer = "List<Jeton>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Jetons a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste Jetons")

    })
    ResponseEntity<List<Jeton>> getAllJetonOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/jetons/searchJetonsByCustomerId/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des Jetons d'un employeur",
            notes = "Cette méthode permet d'afficher la liste des Jetons par employeur", responseContainer = "List<Jeton>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Jetons a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste Jetons")

    })
    ResponseEntity<List<Jeton>> getAllJetonByCustomerIdOrderIdDesc(@PathVariable("userId") Long userId);


    @GetMapping(value = APP_ROOT + "/jetons/NumbersOfjetons")
    @ApiOperation(value = "Décompter le nombre de Jeton",
            notes = "Cette méthode permet de compter et d'afficher le nombre total de Jetons",
            response = Jeton.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Jeton"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    long getNumbersOfjetons();

    @GetMapping(value = APP_ROOT + "/jetons/sumTotalOfJetonPeerMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant total de Jeton par mois",
            notes = "Cette méthode permet de chercher et renvoyer le montant total de Jeton par mois", responseContainer = "List<Jeton>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le montant total de Jeton par mois / une liste vide")
    })
    List<?> getSumTotalOfJetonPeerMonth();

    @GetMapping(value = APP_ROOT + "/jetons/sumTotalOfJetonPeerYear", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant total de Jeton par annee",
            notes = "Cette méthode permet de chercher et renvoyer le montant total de Jeton par annee", responseContainer = "List<Jeton>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le montant total de Jeton par annee / une liste vide")
    })
    List<?> getSumTotalOfJetonPeerYear();

    @DeleteMapping(value = APP_ROOT + "/jetons/delete/{idJeton}")
    @ApiOperation(value = "Supprimer un Jeton par son ID",
            notes = "Cette méthode permet de supprimer un Jeton par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Jeton a été supprimé")

    })
    void delete(@PathVariable("idJeton") Long idJeton);

}
