package com.ouvriers.services;

import com.ouvriers.enums.RoleName;
import com.ouvriers.models.Role;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.repository.RoleRepository;
import com.ouvriers.repository.UtilisateurRepository;
import com.ouvriers.services.Impl.UtilisateurServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UtilisateurServiceTest {

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void should_save_one_utilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName("Tairou");
        utilisateur.setUsername("User01");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setAddressRecruteur("Address");
        utilisateur.setEmail("user@gmail.com");
        utilisateur.setMobile("779440310");

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);

        Utilisateur utilisateurResult = utilisateurService.save(new Utilisateur());

        assertThat(utilisateurResult).usingRecursiveComparison().isEqualTo(utilisateur);
        verify(utilisateurRepository, times(1)).save(any(Utilisateur.class));
        verifyNoMoreInteractions(utilisateurRepository);

    }

    @Test
    public void should_find_and_return_one_utilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName("Tairou");
        utilisateur.setUsername("User01");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setAddressRecruteur("Address");
        utilisateur.setEmail("user@gmail.com");
        utilisateur.setMobile("779440310");

        when(utilisateurRepository.findById(anyLong())).thenReturn(Optional.of(utilisateur));

        Utilisateur utilisateur_find_Result = utilisateurService.findById(anyLong());

        assertThat(utilisateur_find_Result).usingRecursiveComparison().isEqualTo(utilisateur);
        verify(utilisateurRepository, times(1)).findById(anyLong());

    }

    @Test
    public void should_find_and_return_username_of_utilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName("Tairou");
        utilisateur.setUsername("User01");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setAddressRecruteur("Address");
        utilisateur.setEmail("user@gmail.com");
        utilisateur.setMobile("779440310");

        when(utilisateurRepository.findByUsername(utilisateur.getUsername())).thenReturn(Optional.of(utilisateur));

        Utilisateur utilisateur_find_Result = utilisateurService.findByUsername(utilisateur.getUsername());

        assertThat(utilisateur_find_Result).usingRecursiveComparison().isEqualTo(utilisateur);
        verify(utilisateurRepository, times(1)).findByUsername(utilisateur.getUsername());

    }

    @Test
    public void should_activated_utilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName("Tairou");
        utilisateur.setUsername("User01");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setAddressRecruteur("Address");
        utilisateur.setEmail("user@gmail.com");
        utilisateur.setMobile("779440310");

        String isActivated = "activated";

        when(utilisateurRepository.findById(anyLong())).thenReturn(Optional.of(utilisateur));

        utilisateurService.activatedUser(isActivated, String.valueOf(utilisateur.getId()));
        assertThat(utilisateur.isActive()).isNotEqualTo(true);

    }

    /*
    @Test
    public void should_add_role_to_utilisateur() {
        Role role = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(role);
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName("Tairou");
        utilisateur.setUsername("User01");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setAddressRecruteur("Address");
        utilisateur.setEmail("user@gmail.com");
        utilisateur.setMobile("779440310");

        when(utilisateurRepository.findByUsername(utilisateur.getUsername())).thenReturn(Optional.of(utilisateur));

        utilisateurService.addRoleToUser(utilisateur.getUsername(), RoleName.ROLE_ADMIN);
        assertThat(role.getName()).isEqualTo(utilisateur.getRoles());

    }
     */

    @Test
    public void should_update_utilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName("Tairou");
        utilisateur.setUsername("User01");
        utilisateur.setNomEntreprise("Wokite");
        utilisateur.setAddressRecruteur("Address");
        utilisateur.setEmail("user@gmail.com");
        utilisateur.setMobile("779440310");

        when(utilisateurRepository.findById(anyLong())).thenReturn(Optional.of(utilisateur));

        Utilisateur utilisateur_find_Result = utilisateurService.findById(anyLong());
        utilisateur_find_Result.setUsername("PREST004");
        Utilisateur utilisateurUpdated = utilisateurService.save(utilisateur_find_Result);
        assertThat(utilisateurUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_utilisateurs() {
        when(utilisateurRepository.findAll()).thenReturn(singletonList(new Utilisateur()));

        assertThat(utilisateurService.findAll()).hasSize(1);
        verify(utilisateurRepository, times(1)).findAll();
        verifyNoMoreInteractions(utilisateurRepository);

    }

    @Test
    public void should_find_and_return_all_utilisateurs_by_IdDesc() {
        when(utilisateurRepository.findByOrderByIdDesc()).thenReturn(singletonList(new Utilisateur()));

        assertThat(utilisateurService.findByOrderByIdDesc()).hasSize(1);
        verify(utilisateurRepository, times(1)).findByOrderByIdDesc();
        verifyNoMoreInteractions(utilisateurRepository);

    }

    @Test
    public void should_find_and_return_all_newsRegister_in_month_order_by_idDesc() {
        when(utilisateurRepository.findUtilisateursByOrderByIdDesc()).thenReturn(singletonList(new Utilisateur()));

        assertThat(utilisateurService.findNewsRegisterInMonthByOrderByIdDesc()).hasSize(1);
        verify(utilisateurRepository, times(1)).findUtilisateursByOrderByIdDesc();
        verifyNoMoreInteractions(utilisateurRepository);
    }

    @Test
    public void should_delete_one_utilisateur() {
        doNothing().when(utilisateurRepository).deleteById(anyLong());

        utilisateurService.delete(anyLong());
        verify(utilisateurRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(utilisateurRepository);
    }


}
