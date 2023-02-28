package com.ouvriers.controllers.api;

import com.ouvriers.models.WhistList;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface WhistListApi {

    @PostMapping(value = APP_ROOT + "/whistLists/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un WhistList",
            notes = "Cette méthode permet d'enregistrer un WhistList", response = WhistList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'WhistList a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun WhistList  crée / modifié")

    })
    ResponseEntity<WhistList> save(@RequestBody WhistList whistList);

    @PutMapping(value = "/whistLists/{idWhistList}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un WhistList par son ID",
            notes = "Cette méthode permet de modifier un WhistList par son ID", response = WhistList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'WhistList a été modifié"),
            @ApiResponse(code = 400, message = "Aucun WhistList modifié")
    })
    ResponseEntity<WhistList> update(@PathVariable("idWhistList") Long id, @RequestBody WhistList whistList);

    @GetMapping(value = APP_ROOT + "/whistLists/{idWhistList}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un WhistList par ID",
            notes = "Cette méthode permet de chercher un WhistList par son ID", response = WhistList.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "WhistList a été trouver"),
            @ApiResponse(code = 404, message = "Aucun WhistList n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<WhistList> getWhistListById(@PathVariable("idWhistList") Long id);

    @GetMapping(value = "whistLists/searchWhistListByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un WhistList par Reference",
            notes = "Cette méthode permet de chercher un WhistList par son Reference", response = WhistList.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "WhistList a été trouver"),
            @ApiResponse(code = 404, message = "Aucun WhistList n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<WhistList> getWhistListByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = "/whistLists/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des whistLists",
            notes = "Cette méthode permet de chercher et renvoyer la liste des whistLists", responseContainer = "List<WhistList>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des whistLists / une liste vide")
    })
    ResponseEntity<List<WhistList>> getAllWhistLists();

    @GetMapping(value = "/whistLists/searchListOfWhistListByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des whistLists par mot clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des whistLists par mot clé",
            responseContainer = "List<WhistList>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des whistLists / une liste vide")
    })
    ResponseEntity<List<WhistList>> getListOfWhistListsByKeyword(@RequestParam(name = "keyword") String keyword);

    @GetMapping(value = APP_ROOT + "/whistLists/searchwhistListsByPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Page<WhistList> getListWhistListByPageable(@RequestParam(name = "page") int page,
                                          @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/whistLists/searchWhistListByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<WhistList> getWhistListByLocalityPageables(@RequestParam("id") Long addId,
                                                      @RequestParam(name = "page") int page,
                                                      @RequestParam(name = "size") int size);
}
