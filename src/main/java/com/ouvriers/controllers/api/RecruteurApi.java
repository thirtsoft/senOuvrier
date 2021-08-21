package com.ouvriers.controllers.api;

import com.ouvriers.dtos.RecruteurDto;
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

public interface RecruteurApi {

    @PostMapping(value = APP_ROOT + "/recruteurs/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Recruteur",
            notes = "Cette méthode permet d'enregistrer un Recruteur", response = RecruteurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Recruteur a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Recruteur  crée / modifié")

    })
    ResponseEntity<RecruteurDto> save(@RequestBody RecruteurDto recruteurDto);

    @PutMapping(value = "/recruteurs/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Recruteur par son ID",
            notes = "Cette méthode permet de modifier un Recruteur par son ID", response = RecruteurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Recruteur a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Recruteur modifié")
    })
    ResponseEntity<RecruteurDto> update(@PathVariable("idRecruteur") Long id, @RequestBody RecruteurDto recruteurDto);

    @GetMapping(value = APP_ROOT + "/recruteurs/{idRecruteur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Recruteur par ID",
            notes = "Cette méthode permet de chercher un Recruteur par son ID", response = RecruteurDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recruteur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Recruteur n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<RecruteurDto> getRecruteurById(@PathVariable("idRecruteur") Long id);

    @GetMapping(value = "recruteurs/searchRecruteurByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Recruteur par Reference",
            notes = "Cette méthode permet de chercher un Recruteur par son Reference", response = RecruteurDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recruteur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Recruteur n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<RecruteurDto> getRecruteurByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = "/recruteurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des recruteurs",
            notes = "Cette méthode permet de chercher et renvoyer la liste des recruteurs", responseContainer = "List<RecruteurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des recruteurs / une liste vide")
    })
    ResponseEntity<List<RecruteurDto>> getAllRecruteurs();

    @GetMapping(value = "/recruteurs/searchListOfRecruteurByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des recruteurs par mot clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des recruteurs par mot clé",
            responseContainer = "List<RecruteurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des recruteurs / une liste vide")
    })
    ResponseEntity<List<RecruteurDto>> getListOfrecruteursByKeyword(@RequestParam(name = "keyword") String keyword);

    @GetMapping(value = APP_ROOT + "/recruteurs/NumbersOfrecruteurs")
    @ApiOperation(value = "Renvoi le nombre total d'recruteurs",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'recruteurs de la plateforme",
            responseContainer = "List<RecruteurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'recruteurs / une liste vide")
    })
    BigDecimal getNumbersOfRecruteurs();

    @GetMapping(value = APP_ROOT + "/recruteurs/searchrecruteursByPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<RecruteurDto> getListRecruteurByPageable(@RequestParam(name = "page") int page,
                                              @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/recruteurs/searchRecruteurByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<RecruteurDto> getRecruteurByLocalityPageables(@RequestParam("id") Long addId,
                                                          @RequestParam(name = "page") int page,
                                                          @RequestParam(name = "size") int size);


}
