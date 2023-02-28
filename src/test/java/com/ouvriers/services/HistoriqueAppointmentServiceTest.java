package com.ouvriers.services;

import com.ouvriers.models.Appointment;
import com.ouvriers.models.HistoriqueAppointment;
import com.ouvriers.repository.HistoriqueAppointmentRepository;
import com.ouvriers.services.Impl.HistoriqueAppointmentServiceImpl;
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
public class HistoriqueAppointmentServiceTest {

    @InjectMocks
    private HistoriqueAppointmentServiceImpl historiqueAppointmentService;

    @Mock
    private HistoriqueAppointmentRepository historiqueAppointmentRepository;

    @Test
    public void should_save_one_historique_appointment() {
        Appointment appointment = new Appointment();
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setAction("Ajout");
        historiqueAppointment.setAppointment(appointment);
        historiqueAppointment.setCreatedDate(new Date());

        when(historiqueAppointmentRepository.save(historiqueAppointment)).thenReturn(historiqueAppointment);

        HistoriqueAppointment historiqueAppointmentSavedResult = historiqueAppointmentService.save(historiqueAppointment);

        verify(historiqueAppointmentRepository).save(historiqueAppointment);
        assertThat(historiqueAppointment).isNotNull();
        assertThat(historiqueAppointmentSavedResult).isEqualTo(historiqueAppointment);
        assertThat(historiqueAppointmentSavedResult.getId()).isEqualTo(historiqueAppointment.getId());
        assertThat(historiqueAppointmentSavedResult.getAction()).isEqualTo(historiqueAppointment.getAction());
        assertThat(historiqueAppointmentSavedResult.getAppointment()).isEqualTo(historiqueAppointment.getAppointment());

    }

    @Test
    public void should_find_and_return_one_historique_appointment() {
        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setId(1L);
        historiqueAppointment.setAction("Ajout");
        historiqueAppointment.setCreatedDate(new Date());

        when(historiqueAppointmentRepository.findById(historiqueAppointment.getId())).thenReturn(Optional.of(historiqueAppointment));

        HistoriqueAppointment historique_Appointment_find_Result = historiqueAppointmentService.findById(historiqueAppointment.getId());

        verify(historiqueAppointmentRepository).findById(historiqueAppointment.getId());
        assertThat(historiqueAppointment).isNotNull();
        assertThat(historique_Appointment_find_Result.getId()).isEqualTo(historiqueAppointment.getId());
        assertThat(historique_Appointment_find_Result.getAction()).isEqualTo(historiqueAppointment.getAction());
    }

    @Test
    public void should_update_historique_appointment() {
        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setId(1L);
        historiqueAppointment.setAction("Ajout");
        historiqueAppointment.setCreatedDate(new Date());

        when(historiqueAppointmentRepository.findById(anyLong())).thenReturn(Optional.of(historiqueAppointment));
        HistoriqueAppointment historiqueAppointmentfindResult = historiqueAppointmentService.findById(historiqueAppointment.getId());
        historiqueAppointment.setAction("Updated");
        HistoriqueAppointment historiqueAppointmentUpdated = historiqueAppointmentService.save(historiqueAppointmentfindResult);
        assertThat(historiqueAppointmentUpdated).isNull();

    }

    @Test
    public void should_find_and_return_all_historique_appointment() {
        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setId(1L);
        historiqueAppointment.setAction("Ajout");
        historiqueAppointment.setCreatedDate(new Date());

        when(historiqueAppointmentRepository.findAll()).thenReturn(singletonList(historiqueAppointment));

        List<HistoriqueAppointment> historiqueAppointmentList = historiqueAppointmentService.findAll();

        assertThat(historiqueAppointmentList).isNotNull();
        assertThat(historiqueAppointmentList.size()).isEqualTo(1);
        verify(historiqueAppointmentRepository).findAll();
        assertThat(historiqueAppointmentList.get(0)).isEqualTo(historiqueAppointment);
    }

    @Test
    public void should_find_and_return_all_historique_appointment_by_IdDesc() {
        HistoriqueAppointment historiqueAppointment = new HistoriqueAppointment();
        historiqueAppointment.setId(1L);
        historiqueAppointment.setAction("Ajout");
        historiqueAppointment.setCreatedDate(new Date());

        when(historiqueAppointmentRepository.findHistoriqueAppointmentByOrderByIdDesc()).thenReturn(singletonList(historiqueAppointment));

        List<HistoriqueAppointment> historiqueAppointmentList = historiqueAppointmentService.findHistoriqueAppointmentByOrderByIdDesc();

        assertThat(historiqueAppointmentList).isNotNull();
        assertThat(historiqueAppointmentList.size()).isEqualTo(1);
        verify(historiqueAppointmentRepository).findHistoriqueAppointmentByOrderByIdDesc();
        assertThat(historiqueAppointmentList.get(0)).isEqualTo(historiqueAppointment);
    }


    @Test
    public void should_delete_one_historique_appointment() {
        doNothing().when(historiqueAppointmentRepository).deleteById(anyLong());

        historiqueAppointmentService.delete(anyLong());
        verify(historiqueAppointmentRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(historiqueAppointmentRepository);
    }


}
