package com.ouvriers.controllers.api;

import com.ouvriers.models.HistoriqueAppointment;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface HistoriqueAppointmentApi {

    @PostMapping(value = APP_ROOT + "/historiqueAppointment/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une HistoriqueAppointment",
            notes = "Cette méthode permet d'enregistrer une HistoriqueAppointment", response = HistoriqueAppointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'HistoriqueAppointment a été crée"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueAppointment  crée / modifié")

    })
    ResponseEntity<HistoriqueAppointment> create(@RequestBody HistoriqueAppointment historiqueAppointment);

    @PutMapping(value = APP_ROOT + "/historiqueAppointment/update/{idHistoriqueAppointment}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modofier une HistoriqueAppointment  par ID",
            notes = "Cette méthode permet de modifier une HistoriqueAppointment par ID", response = HistoriqueAppointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'HistoriqueAppointment a été modifié"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueAppointment  modifié")

    })
    ResponseEntity<HistoriqueAppointment> update(@PathVariable("idHistoriqueAppointment") Long idHistoriqueAppointment, @RequestBody HistoriqueAppointment historiqueAppointment);

    @GetMapping(value = APP_ROOT + "/historiqueAppointment/findById/{idHistoriqueAppointment}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueAppointment par ID",
            notes = "Cette méthode permet de rechercher une HistoriqueAppointment par son ID", response = HistoriqueAppointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'HistoriqueAppointment a été trouvé"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueAppointment  avec cet ID")

    })
    ResponseEntity<HistoriqueAppointment> getHistoriqueAppointmentById(@PathVariable("idHistoriqueAppointment") Long idHistoriqueAppointment);

    @GetMapping(value = APP_ROOT + "/historiqueAppointment/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des historiqueAppointment",
            notes = "Cette méthode permet d'afficher la liste des historiqueAppointment", responseContainer = "List<HistoriqueAppointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueAppointment a été trouvé"),
            @ApiResponse(code = 400, message = "Liste vide")

    })
    ResponseEntity<List<HistoriqueAppointment>> getAllHistoriqueAppointments();

    @GetMapping(value = APP_ROOT + "/historiqueAppointment/searchAllHistoriqueAppointmentByIdDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Afficher la liste des historiqueAppointment par order Décroissante",
            notes = "Cette méthode permet d'afficher la liste des historiqueAppointment par order Décroissante",
            responseContainer = "List<HistoriqueAppointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueAppointment a été trouvé"),
            @ApiResponse(code = 400, message = "Liste vide")

    })
    ResponseEntity<List<HistoriqueAppointment>> getAllHistoriqueAppointmentOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/historiqueAppointment/delete/{idHistoriqueAppointment}")
    @ApiOperation(value = "Supprimer une historiqueAppointment par son ID",
            notes = "Cette méthode permet de supprimer une historiqueAppointment par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'historiqueAppointment a été supprimé")

    })
    void deleteHistoriqueAppointment(@PathVariable("idHistoriqueAppointment") Long idHistoriqueAppointment);

}
