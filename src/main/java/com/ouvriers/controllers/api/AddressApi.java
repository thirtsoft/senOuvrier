package com.ouvriers.controllers.api;

import com.ouvriers.models.Address;
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

public interface AddressApi {

    @PostMapping(value = APP_ROOT + "/addresses/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Address",
            notes = "Cette méthode permet d'enregistrer un Address", response = Address.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Address a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Address  crée / modifié")

    })
    ResponseEntity<Address> save(@RequestBody Address Address);

    @PutMapping(value = APP_ROOT +  "/addresses/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Address par son ID",
            notes = "Cette méthode permet de modifier un Address par son ID", response = Address.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Address a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Address modifié")
    })
    ResponseEntity<Address> update(@PathVariable("idAddress") Long id, @RequestBody Address Address);

    @GetMapping(value = APP_ROOT + "/addresses/findById/{idAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Address par ID",
            notes = "Cette méthode permet de chercher un Address par son ID", response = Address.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Address a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Address n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Address> getAddressById(@PathVariable("idAddress") Long id);

    @GetMapping(value = "addresses/searchAddressByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Address par Reference",
            notes = "Cette méthode permet de chercher un Address par son Reference", response = Address.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Address a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Address n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<Address> getAddressByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = APP_ROOT +  "/addresses/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des addresses",
            notes = "Cette méthode permet de chercher et renvoyer la liste des addresses", responseContainer = "List<Address>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des addresses / une liste vide")
    })
    ResponseEntity<List<Address>> getAllAddresses();

    @GetMapping(value = APP_ROOT +  "/addresses/searchListOfAddressByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des addresses par mot clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des addresses par mot clé",
            responseContainer = "List<Address>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des addresses / une liste vide")
    })
    ResponseEntity<List<Address>> getListOfaddressesByKeyword(@RequestParam(name = "keyword") String keyword);

    @GetMapping(value = APP_ROOT + "/addresses/NumbersOfaddresses")
    @ApiOperation(value = "Renvoi le nombre total d'addresses",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total d'addresses de la plateforme",
            responseContainer = "List<Address>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'addresses / une liste vide")
    })
    BigDecimal getNumbersOfAddresses();

    @GetMapping(value = APP_ROOT + "/addresses/searchaddressesByPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Address> getListAddressByPageable(@RequestParam(name = "page") int page,
                                               @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/addresses/searchAddressByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Address> getAddressByLocalityPageables(@RequestParam("id") Long addId,
                                                    @RequestParam(name = "page") int page,
                                                    @RequestParam(name = "size") int size);

    @DeleteMapping(value = APP_ROOT + "/addresses/delete/{idAdd}")
    @ApiOperation(value = "Supprimer une Addresse par son ID",
            notes = "Cette méthode permet de supprimer un Addresse par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Metier a été supprimé")

    })
    void delete(@PathVariable("idAdd") Long idAdd);
}
