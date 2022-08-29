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

    @PostMapping(value = APP_ROOT + "/ouvriers/createWithFilesInFolder")
    @ApiOperation(value = "Enregistrer un Ouvrier avec une photo et un cv dans le dossier webapp",
            notes = "Cette méthode permet d'ajouter un Ouvrier avec sa photo et son cv dans le dossier webapp")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Ouvrier a été crée"),
            @ApiResponse(code = 400, message = "Aucun Ouvrier  crée / modifié")

    })
    ResponseEntity<Ouvrier> saveOuvrierrWithFilesInContextFolder(
            @RequestParam(name = "ouvrier") String ouvrier,
            @RequestParam(name = "photoOuvrier") MultipartFile photoOuvrier,
            @RequestParam(name = "cvOuvrier") MultipartFile cvOuvrier) throws IOException;

    @PutMapping(value = APP_ROOT + "/ouvriers/update/{idOuvrier}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = APP_ROOT + "ouvriers/searchOuvrierByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Ouvrier par Reference",
            notes = "Cette méthode permet de chercher un Ouvrier par son Reference", response = Ouvrier.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ouvrier a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Ouvrier n'existe avec cette reference pas dans la BD")

    })
    ResponseEntity<Ouvrier> getOuvrierByRerefence(@RequestParam(name = "ref") String reference);

    @GetMapping(value = APP_ROOT + "/ouvriers/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers", responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getAllOuvriers();

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers par ordres Decroissant",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers par ordres Decroissant",
            responseContainer = "List<ChauffeurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getdAllOuvriersOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierBySelectedIsTrue", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des Ouvriers selectionnés",
            notes = "Cette méthode permet de rechercher et d'afficher liste des Ouvriers selectionnés")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers selectionnés")

    })
    ResponseEntity<List<Ouvrier>> getListOfOuvriersBySelected();

    @GetMapping(value = APP_ROOT + "/ouvriers/searchListOfOuvrierByMetier/{metierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers", responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getListOfOuvriersByMetiers(@PathVariable("metierId") Long pId);

    @GetMapping(value = APP_ROOT + "/ouvriers/searchListOfOuvrierByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers par mot clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers par mot clé",
            responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getListOfOuvriersByKeyword(@RequestParam(name = "keyword") String keyword);

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvriersByDisponibilite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Ouvriers par disponibilit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Ouvriers par mot clé",
            responseContainer = "List<Ouvrier>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvriers / une liste vide")
    })
    ResponseEntity<List<Ouvrier>> getListOfOuvriersByDisponibility(@RequestParam(name = "disponible") String disponibility);

    @GetMapping(value = APP_ROOT + "/ouvriers/numberOfOuvriersPeerMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter les Ouvrier inscrit par mois",
            notes = "Cette méthode permet de recuperer et d'afficher le nombre de Ouvrier inscrit par mois sur un graphe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Ouvrier / Mois")

    })
    List<?> countNumberOfOuvrierPeerMonth();

    @GetMapping(value = APP_ROOT + "/ouvriers/numberOfOuvrierPeerYeer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter les Ouvrier inscrit par années",
            notes = "Cette méthode permet de recuperer et d'afficher le nombre de Ouvrier inscrit par années sur un graphe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Ouvrier / Années")

    })
    List<?> countNumberOfOuvrierPeerYear();

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
    @ApiOperation(value = "Recuperer la photo d'un Ouvrier",
            notes = "Cette méthode permet de recuperer et d'afficher la photo d'un Ouvrier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été recuperer")

    })
    byte[] getPhotoOuvrier(@PathVariable("idOuvrier") Long id) throws Exception;

    @GetMapping(value = APP_ROOT + "/ouvriers/photoOuvrierInFolder/{idOuvrier}")
    @ApiOperation(value = "Recuperer la photo d'un Ouvrier depuis un dossier webapp",
            notes = "Cette méthode permet de recuperer et d'afficher la photo d'un Ouvrier depuis le dossier webapp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été recuperer depuis le dossier webapp")

    })
    byte[] getPhotoOuvrierInContextFolder(@PathVariable("idOuvrier") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/ouvriers/uploadOuvrierPhoto/{idOuvrier}")
    @ApiOperation(value = "Enregistrer la photo d'un chauffeur dans webapp",
            notes = "Cette méthode permet d'enregistrer la photo d'un chauffeur dans un dossier webapp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier webapp")

    })
    void uploadPhotoOuvrier(@RequestParam(name = "photoOuvrier") MultipartFile photoOuvrier,
                            @PathVariable("idOuvrier") Long idOuvrier) throws IOException;

    @PostMapping(path = APP_ROOT + "/ouvriers/uploadPhotoOfOuvrierInFolder/{id}")
    @ApiOperation(value = "Enregistrer la photo d'un Ouvrier dans webapp",
            notes = "Cette méthode permet d'enregistrer la photo d'un Ouvrier dans un dossier webapp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier webapp")

    })
    void uploadPhotoOuvrierInFolder(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @GetMapping(value = APP_ROOT + "/ouvriers/cvOuvrier/{idOuvrier}")
    @ApiOperation(value = "Recuperer le cv d'un Ouvrier",
            notes = "Cette méthode permet de recuperer et d'afficher le cv d'un Ouvrier depuis un dossier wabapp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le cv a été recuperer depuis le dossier wabapp")

    })
    byte[] getCvOuvrier(@PathVariable("idOuvrier") Long id) throws Exception;

    @GetMapping(value = APP_ROOT + "/ouvriers/cvOuvrierInFolder/{idOuvrier}")
    @ApiOperation(value = "Recuperer le cv d'un Ouvrier",
            notes = "Cette méthode permet de recuperer et d'afficher le cv d'un Ouvrier depuis un dossier wabapp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le cv a été recuperer depuis le dossier wabapp")

    })
    byte[] getCvOuvrierInFolder(@PathVariable("idOuvrier") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/ouvriers/uploadOuvrierCv/{idOuvrier}")
    @ApiOperation(value = "Enregistrer le cv d'un Ouvrier",
            notes = "Cette méthode permet d'enregistrer le cv d'un Ouvrier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le cv a été enregistré")

    })
    void uploadCvOuvrier(@RequestParam(name = "cvOuvrier") MultipartFile cvOuvrier,
                         @PathVariable("idOuvrier") Long idOuvrier) throws IOException;

    @PostMapping(path = APP_ROOT + "/ouvriers/uploadCvOfOuvrierInFolder/{id}")
    @ApiOperation(value = "Enregistrer le cv d'un Ouvrier dans un dossier webapp",
            notes = "Cette méthode permet d'enregistrer le cv d'un Ouvrier dans un dossier webapp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le cv a été enregistré dans le dossier webapp")

    })
    void uploadCvOuvrierInFolder(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @RequestMapping(value = APP_ROOT + "/ouvriers/downloadContratFile/{fileName:.+}")
    @ApiOperation(value = "Télécharger le cv d'un Ouvrier",
            notes = "Cette méthode permet de télécharger le cv d'un Ouvrier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le cv a été télécharger")

    })
    void downloadOuvrierFile(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("fileName") String fileName) throws IOException;

    @RequestMapping(value = APP_ROOT + "/ouvriers/downloadCvFileOfOuvrier/{fileName:.+}")
    @ApiOperation(value = "Télécharger le cv d'un Ouvrier depuis le dossier webapp",
            notes = "Cette méthode permet de télécharger le cv d'un Ouvrier depuis un dossier webapp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le cv a été télécharger depuis le dossier webapp")

    })
    void downloadCvFileOfOuvrier(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("fileName") String fileName) throws IOException;

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierByDisponibityByPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une liste d'ouvriers disponible sur des pages",
            notes = "Cette méthode permet de rechercher une liste d'ouvriers par mot clé sur la plateforme")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ouvriers est")

    })
    Page<Ouvrier> getOuvrierByKeywordByPageable(@RequestParam(name = "dispo") String mc,
                                                @RequestParam(name = "page") int page,
                                                @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher des ouvriers par address",
            notes = "Cette méthode permet de rechercher et d'afficher des ouvriers par address sur la plateforme")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ouvriers est")

    })
    Page<Ouvrier> getOuvrierByLocalityPageables(@RequestParam("id") Long addId,
                                                @RequestParam(name = "page") int page,
                                                @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierByLocalityIdByPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher des ouvriers par localité",
            notes = "Cette méthode permet de rechercher et d'afficher des ouvriers par localité sur la plateforme")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ouvriers est")

    })
    Page<Ouvrier> getOuvriersByLocalityIdByPageables(@RequestParam("id") Long locId,
                                                     @RequestParam(name = "page") int page,
                                                     @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/searchOuvrierByMetierPageables",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher des ouvriers par metiers",
            notes = "Cette méthode permet de rechercher et d'afficher des ouvriers par type de metiers sur la plateforme")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ouvriers est")

    })
    Page<Ouvrier> getOuvrierByMetierPageables(@RequestParam("id") Long metierId,
                                              @RequestParam(name = "page") int page,
                                              @RequestParam(name = "size") int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/allOuvriers")
    @ApiOperation(value = "Afficher la listes des Ouvrier par pages",
            notes = "Cette méthode permet d'afficher des Ouvriers par pages")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvrier est")
    })
    ResponseEntity<List<Ouvrier>> getAllOuvriers(@RequestParam int page, @RequestParam int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/address")
    @ApiOperation(value = "Afficher la listes des Ouvrier par adresse par pages",
            notes = "Cette méthode permet d'afficher des Ouvrier par address par pages")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvrier est")
    })
    ResponseEntity<List<Ouvrier>> getAllOuvriersByAddressId(@RequestParam Long id, @RequestParam int page, @RequestParam int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/locality")
    @ApiOperation(value = "Afficher la listes des Ouvrier par localité par pages",
            notes = "Cette méthode permet d'afficher la liste des Ouvriers par localité par pages")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvrier est")
    })
    ResponseEntity<List<Ouvrier>> getAllOuvriersByLocalityId(@RequestParam Long id, @RequestParam int page, @RequestParam int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/searchAllOuvriersByMetiersByPageable")
    @ApiOperation(value = "Afficher la listes des Ouvriers par metier par pages",
            notes = "Cette méthode permet d'afficher des Ouvriers par metier par pages")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvrier est")
    })
    ResponseEntity<List<Ouvrier>> getAllOuvriersByMetierId(@RequestParam Long id, @RequestParam int page, @RequestParam int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/ouvrierKey")
    @ApiOperation(value = "Afficher la listes des Ouvriers par mot clé par pages",
            notes = "Cette méthode permet d'afficher des Ouvriers par mot clé par pages")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ouvrier est")

    })
    ResponseEntity<List<Ouvrier>> getOuvrierDtosByKeyWord(@RequestParam String disponibility, @RequestParam int page, @RequestParam int size);

    @GetMapping(value = APP_ROOT + "/ouvriers/ouvrierDtoSize")
    @ApiOperation(value = "Calculer la longueur des Ouvrier",
            notes = "Cette méthode permet de calculer la taille des Ouvriers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La taille des Ouvriers")

    })
    long ouvrierSize();

    @GetMapping(value = APP_ROOT + "/ouvriers/ctaddressIdSize")
    @ApiOperation(value = "Calculer la longueur des Ouvriers par Id addresse",
            notes = "Cette méthode permet de calculer la taille des Ouvriers par Id addresse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La taille est")

    })
    long getOuvriersByIdAddressSize(@RequestParam Long id);

    @GetMapping(value = APP_ROOT + "/ouvriers/ctLocaloityIdSize")
    @ApiOperation(value = "Calculer la longueur des Ouvriers par Id de la locality",
            notes = "Cette méthode permet de calculer la taille des Ouvriers par Id de la locality")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La taille est")

    })
    long getOuvriersByLocalityIdSize(@RequestParam Long id);

    @GetMapping(value = APP_ROOT + "/ouvriers/ctmetierIdSize")
    @ApiOperation(value = "Calculer la longueur des Ouvriers par Id metier",
            notes = "Cette méthode permet de calculer la taille des Ouvriers par Id metier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La taille est")

    })
    long getOuvriersByIdMetiersSize(@RequestParam Long id);

    @GetMapping(value = APP_ROOT + "/ouvriers/keySize")
    @ApiOperation(value = "Calculer la longueur des Ouvriers par mot clé",
            notes = "Cette méthode permet de calculer la taille des Ouvriers par mot clé")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La taille des Ouvriers par mot clé est")

    })
    long sizeOfOuvriersByKey(@RequestParam String disponibility);

    @DeleteMapping(value = APP_ROOT + "/ouvriers/delete/{idOuvrier}")
    @ApiOperation(value = "Supprimer un ouvrier par ID",
            notes = "Cette méthode permet de supprimer un ouvrier par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le chauffeur a été supprimé")

    })
    void deleteOuvrier(@PathVariable("idOuvrier") Long id);


}
