package com.ouvriers.controllers.api;

import com.ouvriers.dtos.AddresseDto;
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

public interface LocalityApi {

    @PostMapping(value = APP_ROOT + "/localities/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Address",
            notes = "Cette méthode permet d'enregistrer un Address", response = AddresseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Address a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Address  crée / modifié")

    })
    ResponseEntity<AddresseDto> save(@RequestBody AddresseDto addresseDto);

    @PutMapping(value = "/localities/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Address par son ID",
            notes = "Cette méthode permet de modifier un Address par son ID", response = AddresseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Address a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Address modifié")
    })
    ResponseEntity<AddresseDto> update(@PathVariable("idAddress") Long id, @RequestBody AddresseDto AddresseDto);

    @GetMapping(value = APP_ROOT + "/localities/{idAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Address par ID",
            notes = "Cette méthode permet de chercher un Address par son ID", response = AddresseDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Address a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Address n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<AddresseDto> getAddressById(@PathVariable("idAddress") Long id);

    @GetMapping(value = "localities/searchAddressByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Address par Reference",
            notes = "Cette méthode permet de chercher un Address par son Reference", response = AddresseDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Address a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Address n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<AddresseDto> getAddressByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = "/localities/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des localities",
            notes = "Cette méthode permet de chercher et renvoyer la liste des localities", responseContainer = "List<AddresseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des localities / une liste vide")
    })
    ResponseEntity<List<AddresseDto>> getAllLocalities();

    @GetMapping(value = "/localities/searchListOfAddressByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des localities par mot clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des localities par mot clé",
            responseContainer = "List<AddresseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des localities / une liste vide")
    })
    ResponseEntity<List<AddresseDto>> getListOfLocalitiesByKeyword(@RequestParam(name = "keyword") String keyword);

    @GetMapping(value = APP_ROOT + "/localities/NumbersOflocalities")
    @ApiOperation(value = "Renvoi le nombre total d'localities",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'localities de la plateforme",
            responseContainer = "List<AddresseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'localities / une liste vide")
    })
    BigDecimal getNumbersOfLocalities();

    @GetMapping(value = APP_ROOT + "/localities/searchlocalitiesByPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<AddresseDto> getListAddressByPageable(@RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/localities/searchAddressByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<AddresseDto> getAddressByLocalityPageables(@RequestParam("id") Long addId,
                                                              @RequestParam(name = "page") int page,
                                                              @RequestParam(name = "size") int size);
}
