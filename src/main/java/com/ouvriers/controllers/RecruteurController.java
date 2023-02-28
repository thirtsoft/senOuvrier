package com.ouvriers.controllers;

import com.ouvriers.controllers.api.RecruteurApi;
import com.ouvriers.services.RecruteurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class RecruteurController implements RecruteurApi {

    private final RecruteurService recruteurService;


}
