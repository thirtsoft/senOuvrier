package com.ouvriers.controllers.api;

import com.ouvriers.models.Email;
import com.ouvriers.models.Newsletter;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Utilisateur;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface EmailApi {

    @PostMapping(value = APP_ROOT + "/emails/sendMailToManager")
    @ApiOperation(value = "Envoyer un email au Manager du site",
            notes = "Cette méthode permet d'envoyer un email au Manager du site",
            response = Email.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Email> sendEmailToManager(@RequestBody Email email);

    @PostMapping(value = APP_ROOT + "/emails/responseMailToCustomer")
    @ApiOperation(value = "Repondre à un email client",
            notes = "Cette méthode permet de répondre au client",
            response = Email.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Email> responseEmailToCustomer(@RequestBody Email email);

    @PostMapping(value = APP_ROOT + "/emails/sendToRecruteur")
    @ApiOperation(value = "Envoyer un email à un Recruteur",
            notes = "Cette méthode permet d'envoyer un email à un Recruteur",
            response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Utilisateur> sendMailToRecruteur(@RequestBody Utilisateur utilisateur);

    @PostMapping(value = APP_ROOT + "/emails/sendToOuvrier")
    @ApiOperation(value = "Envoyer un email à un Chauffeur",
            notes = "Cette méthode permet d'envoyer un email à un Chauffeur",
            response = Ouvrier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Ouvrier> sendMailToOuvrier(@RequestBody Ouvrier ouvrier);

    @PostMapping(value = APP_ROOT + "/emails/sendToNewsletter")
    @ApiOperation(value = "Envoyer un email à un visiteur",
            notes = "Cette méthode permet d'envoyer un email à un visiteur",
            response = Email.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Newsletter> sendMailToCustomer(@RequestBody Newsletter newsletter);

    @PostMapping(value = APP_ROOT + "/emails/sendMailToAllCustomers")
    @ApiOperation(value = "Envoyer un email à plusieurs Clients",
            notes = "Cette méthode permet d'envoyer un email à plusieurs Clients",
            response = Newsletter.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Newsletter> sendMailToAllCustomers(@RequestBody Newsletter newsletter);


    @GetMapping(value = APP_ROOT + "/emails/findById/{idEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Email par ID",
            notes = "Cette méthode permet de chercher un Email par son ID", response = Email.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Email a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Email n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<Email> getEmailById(@PathVariable("idEmail") Long id);

    @GetMapping(value = APP_ROOT + "/emails/searchAllEmailsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Emails par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Emails par ordre descroissante",
            responseContainer = "List<Email>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Emails  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<Email>> getAllEmailsOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/emails/countNumberOfEmailInMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Email dans le moi",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Email / le nombre est nulle")
    })
    BigDecimal countNumberOfEmail();

    @DeleteMapping(value = APP_ROOT + "/emails/delete/{idEmail}")
    @ApiOperation(value = "Supprimer un Email par son ID",
            notes = "Cette méthode permet de supprimer une Email par son ID", response = Email.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Notification a été supprimé")
    })
    void delete(@PathVariable("idEmail") Long id);


}
