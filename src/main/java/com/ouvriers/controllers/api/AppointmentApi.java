package com.ouvriers.controllers.api;

import com.ouvriers.models.Appointment;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface AppointmentApi {

    @PostMapping(value = APP_ROOT + "/appointments/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Appointment",
            notes = "Cette méthode permet d'enregistrer un Appointment", response = Appointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Appointment a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Appointment  crée / modifié")

    })
    ResponseEntity<Appointment> create(@RequestBody Appointment appointment);

    @PostMapping(value = APP_ROOT + "/appointments/createAppointmentToOuvrier",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Reserver un ouvrier par un employeur",
            notes = "Cette méthode permet d'ajouter une nouvelle une réservation pour un ouvrier par un employeur",
            response = Appointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Réservation a été crée"),
            @ApiResponse(code = 400, message = "Aucun Réservation  ajoutée")

    })
    ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment, @RequestParam Long idOuv, @RequestParam Long id);

    @PutMapping(value = APP_ROOT + "/appointments/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Appointment par son ID",
            notes = "Cette méthode permet de modifier un Appointment par son ID", response = Appointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Appointment a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Appointment modifié")
    })
    ResponseEntity<Appointment> update(@PathVariable("idAppointment") Long id, @RequestBody Appointment appointment);

    @PatchMapping(value = APP_ROOT + "/appointments/updateStatusOfAppointment/{id}")
    @ApiOperation(value = "Modifier le status d'un Appointment",
            notes = "Cette méthode permet de modifier une Appointment par son Status", response = Appointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de la Appointment a été modifié")
    })
    ResponseEntity<Appointment> updateStatusOfAppointment(@RequestParam("status") String status, @PathVariable("id") String id);

    @GetMapping(value = APP_ROOT + "/appointments/findById/{idAppointment}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Appointment par ID",
            notes = "Cette méthode permet de chercher un Appointment par son ID", response = Appointment.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Appointment a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Appointment n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Appointment> getAppointmentById(@PathVariable("idAppointment") Long id);

    @GetMapping(value = APP_ROOT + "/appointments/searchAppointmentByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Appointment par Reference",
            notes = "Cette méthode permet de chercher un Appointment par son Reference", response = Appointment.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Appointment a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Appointment n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<Appointment> getAppointmentByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = APP_ROOT + "/appointments/numbersOfAppointments")
    @ApiOperation(value = "Renvoi le nombre total d'Appointments",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'Appointments de la plateforme",
            responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'Appointments / une liste vide")
    })
    long getNumbersOfAppointments();

    @GetMapping(value = APP_ROOT + "/appointments/numbersOfAppointmentsInMonth")
    @ApiOperation(value = "Renvoi le nombre d'Appointments dans le moi",
            notes = "Cette méthode permet de chercher et renvoyer le nombre d'Appointments dans le moi",
            responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'Appointments dans le moi/ une liste vide")
    })
    BigDecimal getNumbersOfAppointmentsInMonth();

    @GetMapping(value = APP_ROOT + "/appointments/numbersOfAppointmentsByStatusPending")
    @ApiOperation(value = "Renvoi le nombre total d'Appointments encours",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'Appointments encours",
            responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'Appointments encours / une liste vide")
    })
    BigDecimal getNumberOfAppointmentByStatusPending();

    @GetMapping(value = APP_ROOT + "/appointments/numbersOfAppointmentsByStatusAccepted")
    @ApiOperation(value = "Renvoi le nombre total d'Appointments accepted",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'Appointments accepted")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'Appointments accepted / une liste vide")
    })
    BigDecimal getNumberOfAppointmentByStatusAccepted();

    @GetMapping(value = APP_ROOT + "/appointments/numbersOfAcceptedAppointmentsInYear")
    @ApiOperation(value = "Renvoi le nombre total d'Appointments accepted dans une annees",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'Appointments accepted dans une annee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'Appointments accepted dans une annee / une liste vide")
    })
    BigDecimal getNumberOfAcceptedAppointmentInYear();

    @GetMapping(value = APP_ROOT + "/appointments/countNumberOfAppointmentByOuvrierId/{idOuv}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter le nombre de demande de rv pour un ouvrier",
            notes = "Cette méthode permet de compter et d'afficher le nombre total de demande de rv pour un ouvrier",
            response = Appointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de demande de rv"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    BigDecimal getNumberOfAppointmentByOuvrierId(@PathVariable("idOuv") Long id);

    @GetMapping(value = APP_ROOT + "/appointments/countNumberOfAppointmentByCustomerId/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter le nombre de demande de rv d'un utilisateur",
            notes = "Cette méthode permet de compter et d'afficher le nombre total de demande de rv d'un utilisateur",
            response = Appointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de demande de rv"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    BigDecimal getNumberOfAppointmentByCustomerId(@PathVariable("userId") Long id);

    @GetMapping(value = APP_ROOT + "/appointments/countNumberOfAppointmentByCustomerIdAndStatusAccepted/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter le nombre de demande de rv accepter d'un utilisateur",
            notes = "Cette méthode permet de compter et d'afficher le nombre total de demande de rv accepter d'un utilisateur",
            response = Appointment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de demande de rv accepter"),
            @ApiResponse(code = 400, message = "nombre null")

    })
    BigDecimal getNumberOfAppointmentByCustomerIdAndStatusAccepted(@PathVariable("userId") Long userId);

    @GetMapping(value = APP_ROOT + "/appointments/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Appointments",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Appointments", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Appointments / une liste vide")
    })
    ResponseEntity<List<Appointment>> getAllAppointments();

    @GetMapping(value = APP_ROOT + "/appointments/searchAllAppointmentOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Appointments",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Appointments", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Appointments / une liste vide")
    })
    ResponseEntity<List<Appointment>> getAllAppointmentsOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/appointments/searchAllAppointmentsByStatusPending", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Appointments encours",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Appointments encours", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Appointments encours / une liste vide")
    })
    ResponseEntity<List<Appointment>> getAllAppointmentsByStatusPending();

    @GetMapping(value = APP_ROOT + "/appointments/searchAllAppointmentsByStatusAccepted", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Appointments accepted",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Appointments accepted", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Appointments accepted / une liste vide")
    })
    ResponseEntity<List<Appointment>> getAllAppointmentByStatusAccepted();

    @GetMapping(value = APP_ROOT + "/appointments/searchAllAppointmentsByStatusRefused", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Appointments refused",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Appointments refused", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Appointments refused / une liste vide")
    })
    ResponseEntity<List<Appointment>> getAllAppointmentByStatusRefused();

    @GetMapping(value = APP_ROOT + "/appointments/searchAllAppointmentsByCustomerId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Appointments par l'ID de l'utilisateur",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Appointments par l'ID de l'utilisateur", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Appointments par l'ID de l'utilisateur / une liste vide")
    })
    ResponseEntity<List<Appointment>> getAllAppointmentsByCustomerId(@PathVariable(name = "userId") Long userId);

    @GetMapping(value = APP_ROOT + "/appointments/searchAllAppointmentsByOuvrierId/{ouvId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Appointments par l'ID de l'ouvrier",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Appointments par l'ID de l'ouvrier", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Appointments par l'ID de l'ouvrier / une liste vide")
    })
    ResponseEntity<List<Appointment>> getAllAppointmentsByOuvrierId(@PathVariable(name = "ouvId") Long ouvId);

    @GetMapping(value = APP_ROOT + "/appointments/searchTop4AppointmentsByOuvrierId/{ouvId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Appointments par l'ID de l'ouvrier",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Appointments par l'ID de l'ouvrier", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Appointments par l'ID de l'ouvrier / une liste vide")
    })
    ResponseEntity<List<Appointment>> getTop4AppointmentByOuvrierIdOrderByCreatedDateDesc(@PathVariable(name = "ouvId") Long ouvId);

    @GetMapping(value = APP_ROOT + "/appointments/countNumberTotalOfAppointmentsPeerMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre total d'Appointments par mois",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'Appointments par mois", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le nombre total d'Appointments par mois / une liste vide")
    })
    List<?> getNumberTotalOfAppointmentPeerMonth();

    @GetMapping(value = APP_ROOT + "/appointments/countNumberTotalOfAppointmentsPeerYear", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre total d'Appointments par annees",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'Appointments par annees", responseContainer = "List<Appointment>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le nombre total d'Appointments par annees / une liste vide")
    })
    List<?> getNumberTotalOfAppointmentPeerYear();

    @DeleteMapping(value = APP_ROOT + "/appointments/delete/{idAppointment}")
    @ApiOperation(value = "Supprimer un Appointment par son ID",
            notes = "Cette méthode permet de supprimer un Appointment par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Appointment a été supprimé")

    })
    void delete(@PathVariable("idAppointment") Long id);


}
