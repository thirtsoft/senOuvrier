package com.ouvriers.controllers.api;

import com.ouvriers.models.Rating;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface RatingApi {

    @PostMapping(value = APP_ROOT + "/ratings/create",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Rating",
            notes = "Cette méthode permet d'ajouter un nouveau Rating", response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Rating a été crée"),
            @ApiResponse(code = 400, message = "Aucun Rating  ajoutée")

    })
    ResponseEntity<Rating> save(@RequestBody Rating rating);

    @PostMapping(value = APP_ROOT + "/ratings/createWithOuvrier/{idNotification}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Rating à un ouvrier",
            notes = "Cette méthode permet d'ajouter un nouveau Rating à un ouvrier", response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Notification a été crée"),
            @ApiResponse(code = 400, message = "Aucun Notification  ajoutée")

    })
    ResponseEntity<Rating> saveNoteToOuvrier(@PathVariable("idNotification") Long id,
                                                        @RequestBody Rating rating);

    @PutMapping(value = APP_ROOT + "/ratings/update/{idNotification}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modofier un Rating  par ID",
            notes = "Cette méthode permet de modifier un Rating par ID", response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Notification a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Notification  modifié")

    })
    ResponseEntity<Rating> update(@PathVariable("idNotification") Long id, @RequestBody Rating rating);

    @PostMapping(value = APP_ROOT + "/ratings/createRatingToOuvrier",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Rating à un ouvrier par un employeur",
            notes = "Cette méthode permet d'ajouter un nouveau Rating à un ouvrier par un employeur",
            response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Notification a été crée"),
            @ApiResponse(code = 400, message = "Aucun Notification  ajoutée")

    })
    ResponseEntity<Rating> saveRating(@RequestBody Rating rating,
                                               @RequestParam Long idOuv, @RequestParam Long id);


    @GetMapping(value = APP_ROOT + "/ratings/findById/{idNotification}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher un Rating par son ID",
            notes = "Cette méthode permet d'afficher un Rating par son ID",  response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Rating a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun Notification")

    })
    ResponseEntity<Rating> getRatingById(@PathVariable("idNotification") Long id);

    @GetMapping(value = APP_ROOT + "/ratings/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des ratings",
            notes = "Cette méthode permet d'afficher la liste des ratings", responseContainer = "List<Rating>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ratings a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste ratings")

    })
    ResponseEntity<List<Rating>> getAllRatings();

    @GetMapping(value = APP_ROOT + "/ratings/searchAllRatingsOrderByIdDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des ratings par ordre Décroissante",
            notes = "Cette méthode permet d'afficher la liste des ratings par ordre Décroissante",
            responseContainer = "List<Rating>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ratings a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste ratings")

    })
    ResponseEntity<List<Rating>> getAllRatingsOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/ratings/searchTop3RatingOrderByCreatedDateDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des trois dernieres ratings",
            notes = "Cette méthode permet d'afficher la liste des trois dernieres ratings",
            responseContainer = "List<Rating>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ratings a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste ratings")

    })
    ResponseEntity<List<Rating>> getTop3ByOrderByCreatedDateDesc();

    @GetMapping(value = APP_ROOT + "/ratings/searchTop4RatingOrderByCreatedDateDescByOuvrierId/{idChauff}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des quatre dernieres ratings d'un chauffeur",
            notes = "Cette méthode permet d'afficher la liste des quatre dernieres ratings d'un chauffeur",
            responseContainer = "List<Rating>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ratings a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun liste ratings")

    })
    ResponseEntity<List<Rating>> getTop4ByOrderByCreatedDateDescByOuvrierId(@PathVariable("idChauff") Long id);

    @GetMapping(value = APP_ROOT + "/ratings/countNumberOfRatingByOuvrierId/{idOuv}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter le nombre de ratings d'un chauffeur",
            notes = "Cette méthode permet de compter et d'afficher le nombre total de ratings d'un chauffeur",
            response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de ratings"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    BigDecimal countNumberOfNotificationByOuvrierId(@PathVariable("idOuv") Long id);

    @GetMapping(value = APP_ROOT + "/ratings/countNumberOfRatings",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter le nombre de ratings",
            notes = "Cette méthode permet de compter et d'afficher le nombre total de ratings",
            response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de ratings"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    BigDecimal countNumberOfNotification();

    @DeleteMapping(value = APP_ROOT + "/ratings/delete/{idNotification}")
    @ApiOperation(value = "Supprimer un Notification par son ID",
            notes = "Cette méthode permet de supprimer un Notification par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Notification a été supprimé")

    })
    void delete(@PathVariable("idNotification") Long id);

}
