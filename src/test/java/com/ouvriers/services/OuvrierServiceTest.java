package com.ouvriers.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouvriers.models.Locality;
import com.ouvriers.models.Metier;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.repository.OuvrierRepository;
import com.ouvriers.services.Impl.OuvrierServiceImpl;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OuvrierServiceTest {

    @InjectMocks
    private OuvrierServiceImpl ouvrierService;

    @Mock
    private OuvrierRepository ouvrierRepository;

    @Test
    public void should_save_one_ouvrier() {
        Metier metier = new Metier();
        metier.setId(1L);
        metier.setReference("MET01");
        metier.setDesignation("Plombier");
        Locality locality = new Locality();
        locality.setCodePostal("4789456");
        locality.setName("Mariste");
        locality.setRue("874596");
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrier.setDisponibity("Immediate");
        ouvrier.setMetier(metier);
        ouvrier.setLocality(locality);

        when(ouvrierRepository.save(ouvrier)).thenReturn(ouvrier);

        Ouvrier ouvrierSavedResult = ouvrierService.save(ouvrier);

        verify(ouvrierRepository).save(ouvrier);
        assertThat(ouvrier).isNotNull();
        assertThat(ouvrierSavedResult).isEqualTo(ouvrier);
        assertThat(ouvrierSavedResult.getId()).isEqualTo(ouvrier.getId());
        assertThat(ouvrierSavedResult.getFirstName()).isEqualTo(ouvrier.getFirstName());
        assertThat(ouvrierSavedResult.getLastName()).isEqualTo(ouvrier.getLastName());
    }

    /*
    @Test
    public void should_save_one_ouvrier_with_files() throws IOException {
        MultipartFile photoOuvrier = null;
        MultipartFile cvOuvrier = null;

        Ouvrier ouvrierMapper = new ObjectMapper().readValue(new String(), Ouvrier.class);

        ouvrierMapper.setPhotoOuvrier(photoOuvrier.getOriginalFilename());

        ouvrierMapper.setCvOuvrier(cvOuvrier.getOriginalFilename());

        when(ouvrierRepository.save(ouvrierMapper)).thenReturn(ouvrierMapper);

        Ouvrier ouvrierSavedResult = ouvrierService.save(ouvrierMapper);

        verify(ouvrierRepository).save(ouvrierMapper);
        assertThat(ouvrierMapper).isNotNull();
        assertThat(ouvrierSavedResult).isEqualTo(ouvrierMapper);
        assertThat(ouvrierSavedResult.getId()).isEqualTo(ouvrierMapper.getId());
        assertThat(ouvrierSavedResult.getFirstName()).isEqualTo(ouvrierMapper.getFirstName());
        assertThat(ouvrierSavedResult.getLastName()).isEqualTo(ouvrierMapper.getLastName());
    }
*

     */
    @Test
    public void should_find_and_return_one_ouvrier() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrier.setDisponibity("Immediate");

        when(ouvrierRepository.findById(ouvrier.getId())).thenReturn(Optional.of(ouvrier));

        Ouvrier ouvrierSavedResult = ouvrierService.findById(ouvrier.getId());

        verify(ouvrierRepository).findById(ouvrier.getId());
        assertThat(ouvrier).isNotNull();
        assertThat(ouvrierSavedResult).isEqualTo(ouvrier);
        assertThat(ouvrierSavedResult.getId()).isEqualTo(ouvrier.getId());

    }

    @Test
    public void should_find_and_return_reference_ouvrier() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrier.setDisponibity("Immediate");

        when(ouvrierRepository.findByReference(ouvrier.getReference())).thenReturn(Optional.of(ouvrier));

        Ouvrier ouvrierSavedResult = ouvrierService.findByReference(ouvrier.getReference());

        verify(ouvrierRepository).findByReference(ouvrier.getReference());
        assertThat(ouvrier).isNotNull();
        assertThat(ouvrierSavedResult).isEqualTo(ouvrier);
        assertThat(ouvrierSavedResult.getId()).isEqualTo(ouvrier.getId());

    }

    @Test
    public void should_update_ouvrier() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrier.setDisponibity("Immediate");

        when(ouvrierRepository.findById(anyLong())).thenReturn(Optional.of(ouvrier));

        Ouvrier ouvrier_find_Result = ouvrierService.findById(anyLong());
        ouvrier_find_Result.setReference("OUV012345");
        Ouvrier ouvrierUpdated = ouvrierService.save(ouvrier_find_Result);
        assertThat(ouvrierUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_ouvriers() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrier.setDisponibity("Immediate");

        when(ouvrierRepository.findAll()).thenReturn(singletonList(ouvrier));

        List<Ouvrier> ouvrierList = ouvrierService.findAll();

        assertThat(ouvrierList).isNotNull();
        assertThat(ouvrierList.size()).isEqualTo(1);
        verify(ouvrierRepository).findAll();
        assertThat(ouvrierList.get(0)).isEqualTo(ouvrier);
    }

    @Test
    public void should_find_and_return_all_ouvriers_by_IdDesc() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrier.setDisponibity("Immediate");

        when(ouvrierRepository.findOuvrierByOrderByIdDesc()).thenReturn(singletonList(ouvrier));

        List<Ouvrier> ouvrierList = ouvrierService.findAllOuvriersByIdDesc();

        assertThat(ouvrierList).isNotNull();
        assertThat(ouvrierList.size()).isEqualTo(1);
        verify(ouvrierRepository).findOuvrierByOrderByIdDesc();
        assertThat(ouvrierList.get(0)).isEqualTo(ouvrier);
    }

    @Test
    public void should_find_and_return_all_selected_ouvrier() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setReference("OUV01");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrier.setDisponibity("Immediate");

        when(ouvrierRepository.findOuvrierBySelected()).thenReturn(singletonList(ouvrier));

        List<Ouvrier> ouvrierList = ouvrierService.findListOuvrierBySelected();

        assertThat(ouvrierList).isNotNull();
        assertThat(ouvrierList.size()).isEqualTo(1);
        verify(ouvrierRepository).findOuvrierBySelected();
        assertThat(ouvrierList.get(0)).isEqualTo(ouvrier);
    }

    @Test
    public void should_find_and_return_ouvriers_by_metierId() {
        Long idMetier = 1L;
        when(ouvrierRepository.findListOuvriersByMetier(idMetier)).thenReturn(singletonList(new Ouvrier()));

        List<Ouvrier> ouvrierList = ouvrierService.findListOfOuvriersByMetier(idMetier);

        assertThat(ouvrierList).isNotNull();
        assertThat(ouvrierList.size()).isEqualTo(1);
        verify(ouvrierRepository).findListOuvriersByMetier(idMetier);
        assertThat(ouvrierList.get(0)).isEqualTo(new Ouvrier());
    }

    @Test
    public void should_find_and_return_ouvriers_by_keyword() {
        String keyword = "mc";
        when(ouvrierRepository.findListOfOuvriersByKeyword(keyword)).thenReturn(singletonList(new Ouvrier()));

        List<Ouvrier> ouvrierList = ouvrierService.findListOfOuvriersByKeyword(keyword);

        assertThat(ouvrierList).isNotNull();
        assertThat(ouvrierList.size()).isEqualTo(1);
        verify(ouvrierRepository).findListOfOuvriersByKeyword(keyword);
        assertThat(ouvrierList.get(0)).isEqualTo(new Ouvrier());
    }

    @Test
    public void should_find_and_return_ouvriers_by_disponibility() {
        String disponibility = "immediate";
        when(ouvrierRepository.findListOfOuvrierByDisponibility(disponibility)).thenReturn(singletonList(new Ouvrier()));

        List<Ouvrier> ouvrierList = ouvrierService.findListOfOuvriersByDisponibility(disponibility);

        assertThat(ouvrierList).isNotNull();
        assertThat(ouvrierList.size()).isEqualTo(1);
        verify(ouvrierRepository).findListOfOuvrierByDisponibility(disponibility);
        assertThat(ouvrierList.get(0)).isEqualTo(new Ouvrier());
    }

    /*
    @Test
    public void should_find_and_return_number_of_ouvriers_peer_month() {
        when(ouvrierRepository.countNumberOfOuvrierPeerMonth(anylong())).thenReturn(anyList());

        List<?> ouvrierList = ouvrierService.countNumberOfOuvrierByMonth();

        assertThat(ouvrierList).isNotNull();
        assertThat(ouvrierList.size()).isEqualTo(1);
        verify(ouvrierRepository).countNumberOfOuvrierPeerMonth(anyLong());
        assertThat(ouvrierList.get(0)).isEqualTo(new Ouvrier());
    }

    @Test
    public void should_find_and_return_number_of_ouvriers_peer_year() {
        when(ouvrierRepository.countNumberOfOuvrierPeerMonth(anyLong())).thenReturn(anyList());

        List<?> ouvrierList = ouvrierService.countNumberOfOuvrierByMonth();

        assertThat(ouvrierList).isNotNull();
        assertThat(ouvrierList.size()).isEqualTo(1);
        verify(ouvrierRepository).countNumberOfOuvrierPeerMonth(anyLong());
        assertThat(ouvrierList.get(0)).isEqualTo(new Ouvrier());
    }
    */



}
