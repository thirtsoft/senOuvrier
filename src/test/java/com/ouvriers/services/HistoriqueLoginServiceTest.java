package com.ouvriers.services;


import com.ouvriers.models.HistoriqueLogin;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.repository.HistoriqueLoginRepository;
import com.ouvriers.services.Impl.HistoriqueLoginServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class HistoriqueLoginServiceTest {

    @InjectMocks
    private HistoriqueLoginServiceImpl historiqueLoginService;

    @Mock
    private HistoriqueLoginRepository historiqueLoginRepository;

    @Test
    public void should_save_one_historique_Login() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setName("Tairou");
        user.setNomEntreprise("Wokite");
        user.setUsername("username");
        user.setPassword("passer123");

        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setId(1L);
        historiqueLogin.setAction("Se loger");
        historiqueLogin.setUtilisateur(user);
        historiqueLogin.setCreatedDate(new Date());

        when(historiqueLoginRepository.save(historiqueLogin)).thenReturn(historiqueLogin);

        HistoriqueLogin historiqueLoginSavedResult = historiqueLoginService.save(historiqueLogin);

        verify(historiqueLoginRepository).save(historiqueLogin);
        assertThat(historiqueLogin).isNotNull();
        assertThat(historiqueLoginSavedResult).isEqualTo(historiqueLogin);
        assertThat(historiqueLoginSavedResult.getId()).isEqualTo(historiqueLogin.getId());
        assertThat(historiqueLoginSavedResult.getAction()).isEqualTo(historiqueLogin.getAction());
        assertThat(historiqueLoginSavedResult.getUtilisateur()).isEqualTo(historiqueLogin.getUtilisateur());

    }

    @Test
    public void should_find_and_return_one_historique_login() {
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setId(1L);
        historiqueLogin.setAction("Se loger");
        historiqueLogin.setCreatedDate(new Date());

        when(historiqueLoginRepository.findById(historiqueLogin.getId())).thenReturn(Optional.of(historiqueLogin));

        HistoriqueLogin historique_Login_find_Result = historiqueLoginService.findById(historiqueLogin.getId());

        verify(historiqueLoginRepository).findById(historiqueLogin.getId());
        assertThat(historiqueLogin).isNotNull();
        assertThat(historique_Login_find_Result.getId()).isEqualTo(historiqueLogin.getId());
        assertThat(historique_Login_find_Result.getAction()).isEqualTo(historiqueLogin.getAction());
    }

    @Test
    public void should_update_historique_login() {
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setId(1L);
        historiqueLogin.setAction("Se loger");
        historiqueLogin.setCreatedDate(new Date());

        when(historiqueLoginRepository.findById(anyLong())).thenReturn(Optional.of(historiqueLogin));

        HistoriqueLogin historiqueLoginfindResult = historiqueLoginService.findById(historiqueLogin.getId());
        historiqueLoginfindResult.setAction("Updated");
        HistoriqueLogin historiqueLoginUpdated = historiqueLoginService.save(historiqueLoginfindResult);
        assertThat(historiqueLoginUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_historique_logins() {
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setId(1L);
        historiqueLogin.setAction("Se loger");
        historiqueLogin.setCreatedDate(new Date());

        when(historiqueLoginRepository.findAll()).thenReturn(singletonList(historiqueLogin));

        List<HistoriqueLogin> historiqueLoginList = historiqueLoginService.findAll();

        assertThat(historiqueLoginList).isNotNull();
        assertThat(historiqueLoginList.size()).isEqualTo(1);
        verify(historiqueLoginRepository).findAll();
        assertThat(historiqueLoginList.get(0)).isEqualTo(historiqueLogin);
    }

    @Test
    public void should_find_and_return_all_historique_logins_by_IdDesc() {
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setId(1L);
        historiqueLogin.setAction("Se loger");
        historiqueLogin.setCreatedDate(new Date());

        when(historiqueLoginRepository.findHistoriqueLoginByOrderByIdDesc()).thenReturn(singletonList(historiqueLogin));

        List<HistoriqueLogin> historiqueLoginList = historiqueLoginService.findHistoriqueLoginByOrderByIdDesc();

        assertThat(historiqueLoginList).isNotNull();
        assertThat(historiqueLoginList.size()).isEqualTo(1);
        verify(historiqueLoginRepository).findHistoriqueLoginByOrderByIdDesc();
        assertThat(historiqueLoginList.get(0)).isEqualTo(historiqueLogin);
    }

    @Test
    public void should_find_and_return_top30_historique_logins_by_IdDesc() {
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setId(1L);
        historiqueLogin.setAction("Se loger");
        historiqueLogin.setCreatedDate(new Date());

        when(historiqueLoginRepository.findTop30ByOrderByIdDesc()).thenReturn(singletonList(historiqueLogin));

        List<HistoriqueLogin> historiqueLoginList = historiqueLoginService.findTop30HistoriqueLoginsOrderByCreatedDateDesc();

        assertThat(historiqueLoginList).isNotNull();
        assertThat(historiqueLoginList.size()).isEqualTo(1);
        verify(historiqueLoginRepository).findTop30ByOrderByIdDesc();
        assertThat(historiqueLoginList.get(0)).isEqualTo(historiqueLogin);
    }

    @Test
    public void should_delete_one_historique_login() {
        doNothing().when(historiqueLoginRepository).deleteById(anyLong());

        historiqueLoginService.delete(anyLong());
        verify(historiqueLoginRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(historiqueLoginRepository);
    }

}
