package com.ouvriers.controllers;

import com.ouvriers.controllers.api.AuthApi;
import com.ouvriers.message.request.LoginForm;
import com.ouvriers.message.request.SignUpForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@AllArgsConstructor
public class AuthController implements AuthApi {

    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginForm) {
        return null;
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpForm) {
        return null;
    }
}
