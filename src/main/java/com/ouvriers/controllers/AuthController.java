package com.ouvriers.controllers;

import com.ouvriers.controllers.api.AuthApi;
import com.ouvriers.enums.RoleName;
import com.ouvriers.message.request.LoginForm;
import com.ouvriers.message.request.SignUpForm;
import com.ouvriers.message.response.JwtResponse;
import com.ouvriers.message.response.ResponseMessage;
import com.ouvriers.models.HistoriqueLogin;
import com.ouvriers.models.Role;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.repository.RoleRepository;
import com.ouvriers.repository.UtilisateurRepository;
import com.ouvriers.security.jwt.JwtsProvider;
import com.ouvriers.security.service.UserPrinciple;
import com.ouvriers.services.HistoriqueLoginService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@AllArgsConstructor
public class AuthController implements AuthApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtsProvider jwtProvider;
    @Autowired
    private HistoriqueLoginService historiqueLoginService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginForm) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generatedJwtToken(authentication);
        //    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Optional<Utilisateur> optionalUtilisateur = userRepository.findById(userDetails.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setUtilisateur(utilisateur);
        historiqueLogin.setAction("SE CONNECTER");
        historiqueLogin.setCreatedDate(new Date());
        historiqueLoginService.save(historiqueLogin);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpForm) {
        if (userRepository.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Utilisateur user = new Utilisateur(signUpForm.getName(),
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()));

        // Set<String> strRoles = signUpForm.getRole();
        String[] strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = (roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
            roles.add(userRole);

        }

        for (String role : strRoles) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
                    break;

                case "manager":
                    roles.add(roleRepository.findByName(RoleName.ROLE_MANAGER).get());
                    break;

                case "user":
                    roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
                    break;

                default:
                    roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
            }
        }

        user.setRoles(roles);
        user.setActive(true);
        user.setDateInscription(new Date());

        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.CREATED);
    }
}
