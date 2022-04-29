package com.ouvriers.controllers.api;

import com.ouvriers.models.Ouvrier;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface OuvrierApi {

    @PostMapping(value = APP_ROOT + "/ouvriers/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un ouvrier",
            notes = "Cette méthode permet d'enregistrer un ouvrier", response = Ouvrier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'ouvrier a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun ouvrier  crée / modifié")

    })
    ResponseEntity<Ouvrier> save(@RequestBody Ouvrier ouvrier);

    @PostMapping(value = APP_ROOT + "/ouvriers/createWithFiles")
    @ApiOperation(value = "Enregistrer un ouvrier avec une photo et un cv",
            notes = "Cette méthode permet d'enregistrer un ouvrier avec sa photo et son cv", response = Ouvrier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'ouvrier a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun ouvrier  crée / modifié")

    })
    ResponseEntity<Ouvrier> saveOuvrierWithFiles(
            @RequestPart(name = "ouvrier") String ouvrier,
            @RequestParam(name = "photoOuvrier") MultipartFile photoOuvrier,
            @RequestParam(name = "cvOuvrier") MultipartFile cvOuvrier) throws IOException;


    /*
    @PostMapping(value = APP_ROOT + "/ouvriers/createWithFiles")
    @ApiOperation(value = "Enregistrer un ouvrier avec une photo et un cv",
            notes = "Cette méthode permet d'enregistrer un ouvrier avec sa photo et son cv", response = Ouvrier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'ouvrier a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun ouvrier  crée / modifié")

    })
    ResponseEntity<Ouvrier> saveOuvrierWithPhotoAndCv(
            @RequestPart(name = "ouvrier") String Ouvrier,
            @RequestParam(name = "photoOuvrier") MultipartFile photoOuvrier,
            @RequestParam(name = "cvOuvrier") MultipartFile cvOuvrier) throws IOException;
*/

    @PutMapping(value = "/ouvriers/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un ouvrier par son ID",
            notes = "Cette méthode permet de modifier un ouvrier par son ID", response = Ouvrier.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'ouvrier a été modifié"),
            @ApiResponse(code = 400, message = "Aucun ouvrier modifié")
    })
    ResponseEntity<Ouvrier> update(@PathVariable("idOuvrier") Long id, @RequestBody Ouvrier Ouvrier);

    @GetMapping(value = APP_ROOT + "/ouvriers/findById/{idOuvrier}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Ouvrier par ID",
            notes = "Cette méthode permet de chercher un Ouvrier par son ID", response = Ouvrier.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ouvrier a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Ouvrier n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Ouvrier> getOuvrierById(@PathVariable("idOuvrier") Long id);

    @GetMapping(value = "ouvriers/findByReference/searchOuvrierByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Ouvrier par Reference",
            notes = "Cette méthode permet de chercher un Ouvrier par son Reference", response = Ouvrier.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ouvrier a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Ouvrier n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<Ouvrier> getOuvrierByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = "/ouvriers/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers", responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getAllOuvriers();

    @GetMapping(value = "/ouvriers/searchListOfOuvrierByMetier/{metierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers", responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getListOfOuvriersByMetiers(@PathVariable("metierId") Long pId);

    @GetMapping(value = "/ouvriers/searchListOfOuvrierByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers par mot clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers par mot clé",
            responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getListOfOuvriersByKeyword(@RequestParam(name = "keyword") String keyword);

    @GetMapping(value = "/ouvriers/searchListOfOuvrierByDisponibility", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers par disponibilit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers par mot clé",
            responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getListOfOuvriersByDisponibility(@RequestParam(name = "disponibility") String disponibility);

    @GetMapping(value = APP_ROOT + "/ouvriers/NumbersOfOuvriers")
    @ApiOperation(value = "Renvoi le nombre total d'ouvriers",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'Ouvriers de la plateforme",
            responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'Ouvriers / une liste vide")
    })
    BigDecimal getNumbersOfOuvriers();

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvriersByPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Ouvrier> getListOuvrierByPageable(@RequestParam(name = "page") int page,
                                              @RequestParam(name = "size") int size);


    @GetMapping(value = APP_ROOT + "/ouvriers/photoOuvrier/{idOuvrier}")
    byte[] getPhotoOuvrier(@PathVariable("idOuvrier") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/ouvriers/uploadOuvrierPhoto/{idOuvrier}")
    void uploadPhotoOuvrier(@RequestParam(name = "photoArticle") MultipartFile photoOuvrier,
                            @PathVariable("idOuvrier") Long idOuvrier) throws IOException;

    @GetMapping(value = APP_ROOT + "/ouvriers/cvOuvrier/{idOuvrier}")
    byte[] getCvOuvrier(@PathVariable("idOuvrier") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/ouvriers/uploadOuvrierCv/{idOuvrier}")
    void uploadCvOuvrier(@RequestParam(name = "cvOuvrier") MultipartFile cvOuvrier,
                         @PathVariable("idOuvrier") Long idOuvrier) throws IOException;

    @RequestMapping(value = APP_ROOT + "/ouvriers/downloadContratFile/{fileName:.+}")
    void downloadOuvrierFile(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("fileName") String fileName) throws IOException;

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierByDisponibityByPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Ouvrier> getOuvrierByKeywordByPageable(@RequestParam(name = "dispo") String mc,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Ouvrier> getOuvrierByLocalityPageables(@RequestParam("id") Long addId,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierByMetierPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Ouvrier> getOuvrierByMetierPageables(
            @RequestParam("id") Long permisId,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size);


}