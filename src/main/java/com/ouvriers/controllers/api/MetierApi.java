package com.ouvriers.controllers.api;

import com.ouvriers.dtos.MetierDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface MetierApi {

    @PostMapping(value = APP_ROOT + "/metiers/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Metier",
            notes = "Cette méthode permet d'enregistrer un Metier", response = MetierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Metier a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Metier  crée / modifié")

    })
    ResponseEntity<MetierDto> save(@RequestBody MetierDto metierDto);

    @PutMapping(value = "/metiers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Metier par son ID",
            notes = "Cette méthode permet de modifier un Metier par son ID", response = MetierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Metier a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Metier modifié")
    })
    ResponseEntity<MetierDto> update(@PathVariable("idMetier") Long id, @RequestBody MetierDto metierDto);

    @GetMapping(value = APP_ROOT + "/metiers/{idMetier}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Metier par ID",
            notes = "Cette méthode permet de chercher un Metier par son ID", response = MetierDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Metier a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Metier n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<MetierDto> getMetierById(@PathVariable("idMetier") Long id);

    @GetMapping(value = "metiers/searchMetierByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Metier par Reference",
            notes = "Cette méthode permet de chercher un Metier par son Reference", response = MetierDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Metier a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Metier n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<MetierDto> getMetierByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = "/metiers/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des metiers",
            notes = "Cette méthode permet de chercher et renvoyer la liste des metiers", responseContainer = "List<MetierDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des metiers / une liste vide")
    })
    ResponseEntity<List<MetierDto>> getAllMetiers();

    @GetMapping(value = "/metiers/searchListOfMetierByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des metiers par mot clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des metiers par mot clé",
            responseContainer = "List<MetierDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des metiers / une liste vide")
    })
    ResponseEntity<List<MetierDto>> getListOfMetiersByKeyword(@RequestParam(name = "keyword") String keyword);

    @GetMapping(value = APP_ROOT + "/metiers/NumbersOfmetiers")
    @ApiOperation(value = "Renvoi le nombre total d'metiers",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'metiers de la plateforme",
            responseContainer = "List<MetierDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'metiers / une liste vide")
    })
    BigDecimal getNumbersOfMetiers();

    @GetMapping(value = APP_ROOT + "/metiers/searchmetiersByPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<MetierDto> getListMetierByPageable(@RequestParam(name = "page") int page,
                                            @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/metiers/searchMetierByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<MetierDto> getMetierByLocalityPageables(@RequestParam("id") Long addId,
                                                 @RequestParam(name = "page") int page,
                                                 @RequestParam(name = "size") int size);
}
