package com.ouvriers.controllers.api;

import com.ouvriers.models.Annonce;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface AnnonceApi {

    @PostMapping(value = APP_ROOT + "/annonces/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Annonce",
            notes = "Cette méthode permet d'enregistrer un Annonce", response = Annonce.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Annonce a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Annonce  crée / modifié")

    })
    ResponseEntity<Annonce> save(@RequestBody Annonce Annonce);

    @PutMapping(value = "/annonces/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Annonce par son ID",
            notes = "Cette méthode permet de modifier un Annonce par son ID", response = Annonce.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Annonce a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Annonce modifié")
    })
    ResponseEntity<Annonce> update(@PathVariable("idAnnonce") Long id, @RequestBody Annonce Annonce);

    @GetMapping(value = APP_ROOT + "/annonces/findById/{idAnnonce}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Annonce par ID",
            notes = "Cette méthode permet de chercher un Annonce par son ID", response = Annonce.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Annonce a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Annonce n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Annonce> getAnnonceById(@PathVariable("idAnnonce") Long id);

    @GetMapping(value = "annonces/searchAnnonceByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Annonce par Reference",
            notes = "Cette méthode permet de chercher un Annonce par son Reference", response = Annonce.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Annonce a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Annonce n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<Annonce> getAnnonceByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = "/annonces/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des annonces",
            notes = "Cette méthode permet de chercher et renvoyer la liste des annonces", responseContainer = "List<Annonce>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des annonces / une liste vide")
    })
    ResponseEntity<List<Annonce>> getAllAnnonces();

    @GetMapping(value = "/annonces/searchListOfAnnonceByMetier/{metierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des annonces",
            notes = "Cette méthode permet de chercher et renvoyer la liste des annonces", responseContainer = "List<Annonce>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des annonces / une liste vide")
    })
    ResponseEntity<List<Annonce>> getListOfAnnoncesByMetiers(@PathVariable("metierId") Long pId);

    @GetMapping(value = "/annonces/searchListOfAnnonceByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des annonces par mot clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des annonces par mot clé",
            responseContainer = "List<Annonce>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des annonces / une liste vide")
    })
    ResponseEntity<List<Annonce>> getListOfAnnoncesByKeyword(@RequestParam(name = "keyword") String keyword);

    @GetMapping(value = APP_ROOT + "/annonces/NumbersOfannonces")
    @ApiOperation(value = "Renvoi le nombre total d'annonces",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'annonces de la plateforme",
            responseContainer = "List<Annonce>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'annonces / une liste vide")
    })
    BigDecimal getNumbersOfAnnonces();

    @GetMapping(value = APP_ROOT + "/annonces/searchAnnoncesByPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Annonce> getListAnnonceByPageable(@RequestParam(name = "page") int page,
                                           @RequestParam(name = "size") int size);


    @GetMapping(value = APP_ROOT + "/annonces/photoAnnonce/{idAnnonce}")
    byte[] getPhotoAnnonce(@PathVariable("idAnnonce") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/annonces/uploadAnnoncePhoto/{idAnnonce}")
    void uploadPhotoAnnonce(@RequestParam(name = "photoArticle") MultipartFile photoAnnonce,
                            @PathVariable("idAnnonce") Long idAnnonce) throws IOException;

    @GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Annonce> getAnnonceByLocalityPageables(@RequestParam("id") Long addId,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByMetierPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Annonce> getAnnonceByMetierPageables(
            @RequestParam("id") Long metierId,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size);


}
