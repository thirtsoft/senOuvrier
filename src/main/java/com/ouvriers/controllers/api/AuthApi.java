package com.ouvriers.controllers.api;

import com.ouvriers.message.request.LoginForm;
import com.ouvriers.message.request.SignUpForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.ouvriers.utils.Constants.APP_ROOT;

public interface AuthApi {

    @PostMapping(value = APP_ROOT + "/auth/authenticated",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Se connecter",
            notes = "Cette méthode permet à un utilisateur de se connecter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "utilisateur a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun connection utilisateur  établie")
    })
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm);


    @PostMapping(value = APP_ROOT + "/auth/registerUser",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Créer compte utilisateure",
            notes = "Cette méthode permet à un utilisateur de Créer un compte")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "compte a été crée"),
            @ApiResponse(code = 400, message = "Aucun compte  crée")
    })
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpForm);
}
