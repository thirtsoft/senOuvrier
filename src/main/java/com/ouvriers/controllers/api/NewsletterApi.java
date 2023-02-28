package com.ouvriers.controllers.api;

import com.ouvriers.models.Newsletter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface NewsletterApi {

    @PostMapping(value = APP_ROOT + "/newsletters/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Newsleter",
            notes = "Cette méthode permet d'ajouter un nouveau Newsleter", response = Newsletter.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Newsleter a été crée"),
            @ApiResponse(code = 400, message = "Aucun Newsleter  ajoutée")

    })
    ResponseEntity<Newsletter> createNewsleter(@RequestBody Newsletter newsletter);

    @PutMapping(value = APP_ROOT + "/newsletters/update/{idNewsleter}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modofier un Newsleter  par ID",
            notes = "Cette méthode permet de modifier un Newsleter par ID", response = Newsletter.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Newsleter a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Newsleter  modifié")

    })
    ResponseEntity<Newsletter> updateNewsleter(@PathVariable("idNewsleter") Long idNewsleter,
                                                 @RequestBody Newsletter newsletter);

    @GetMapping(value = APP_ROOT + "/newsletters/findById/{idNewsleter}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher un Newsleter par son ID",
            notes = "Cette méthode permet d'afficher un Newsleter par son ID",  response = Newsletter.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La newsletters a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun newsletters")

    })
    ResponseEntity<Newsletter> getNewsletterById(@PathVariable("idNewsleter") Long idNewsleter);

    @GetMapping(value = APP_ROOT + "/newsletters/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des newsletters",
            notes = "Cette méthode permet d'afficher la liste des newsletters", responseContainer = "List<Newsletter>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des newsletters a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste newsletters")

    })
    ResponseEntity<List<Newsletter>> getAllNewsletters();

    @GetMapping(value = APP_ROOT + "/newsletters/searchAllNewsletersOrderByIdDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des Newsleter par ordre Décroissante",
            notes = "Cette méthode permet d'afficher la liste des Newsleter par ordre Décroissante", responseContainer = "List<Newsletter>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des newsletters a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste newsletters")

    })
    ResponseEntity<List<Newsletter>> getListOfNewslettersOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/newsletters/NumbersOfNewsletters")
    @ApiOperation(value = "Décompter le nombre de Newsleter",
            notes = "Cette méthode permet de compter et d'afficher le nombre total de newsletters",
            response = Newsletter.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Newsleter"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    long getNumbersOfnewsletters();

    @DeleteMapping(value = APP_ROOT + "/newsletters/delete/{idNewsleter}")
    @ApiOperation(value = "Supprimer un Newsleter par son ID",
            notes = "Cette méthode permet de supprimer un Newsleter par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Newsleter a été supprimé")

    })
    void delete(@PathVariable("idNewsleter") Long idNewsleter);
}
