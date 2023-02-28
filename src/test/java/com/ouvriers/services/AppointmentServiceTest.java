package com.ouvriers.services;

import com.ouvriers.models.Appointment;
import com.ouvriers.models.Ouvrier;
import com.ouvriers.models.Utilisateur;
import com.ouvriers.repository.AppointmentRepository;
import com.ouvriers.services.Impl.AppointmentServiceImpl;
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
public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    public void should_save_one_appointment() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setName("Tairou");
        user.setNomEntreprise("Wokite");
        user.setUsername("username");
        user.setPassword("passer123");
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setFirstName("Saliou");
        ouvrier.setLastName("Diallo");
        ouvrier.setDisponibity("Immediate");

        Appointment appointment = new Appointment();
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());
        appointment.setUtilisateur(user);
        appointment.setOuvrier(ouvrier);

        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment appointmentSavedResult = appointmentService.save(appointment);

        verify(appointmentRepository).save(appointment);
        assertThat(appointment).isNotNull();
        assertThat(appointmentSavedResult).isEqualTo(appointment);
        assertThat(appointmentSavedResult.getId()).isEqualTo(appointment.getId());
        assertThat(appointmentSavedResult.getAppointmentDate()).isEqualTo(appointment.getAppointmentDate());
        assertThat(appointmentSavedResult.getReference()).isEqualTo(appointment.getReference());

    }

    @Test
    public void should_find_and_return_one_appointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.of(appointment));

        Appointment appointmentfindResult = appointmentService.findById(appointment.getId());

        verify(appointmentRepository).findById(appointment.getId());
        assertThat(appointment).isNotNull();
        assertThat(appointmentfindResult.getId()).isEqualTo(appointment.getId());
        assertThat(appointmentfindResult.getReference()).isEqualTo(appointment.getReference());
    }

    @Test
    public void should_update_appointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
        Appointment appointmentfindResult = appointmentService.findById(appointment.getId());
        appointment.setReference("MP01");
        Appointment appointmentUpdated = appointmentService.save(appointmentfindResult);
        assertThat(appointmentUpdated).isNull();

    }

    @Test
    public void should_update_status_of_appointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
        Appointment appointmentfindResult = appointmentService.findById(appointment.getId());

        appointment.setStatusOfAppointment("Validated");
        Appointment appointmentUpdated = appointmentService.save(appointmentfindResult);
        assertThat(appointmentUpdated).isNull();

    }


    @Test
    public void should_find_and_return_all_appointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        when(appointmentRepository.findAll()).thenReturn(singletonList(appointment));

        List<Appointment> appointmentList = appointmentService.findAll();

        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(1);
        verify(appointmentRepository).findAll();
        assertThat(appointmentList.get(0)).isEqualTo(appointment);
    }

    @Test
    public void should_find_and_return_all_appointment_by_IdDesc() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        when(appointmentRepository.findByOrderByIdDesc()).thenReturn(singletonList(appointment));

        List<Appointment> appointmentList = appointmentService.findAllAppointmentByIdDesc();

        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(1);
        verify(appointmentRepository).findByOrderByIdDesc();
        assertThat(appointmentList.get(0)).isEqualTo(appointment);
    }

    @Test
    public void should_find_and_return_all_appointment_by_customerId() {

        Long customerId = 1L;
        when(appointmentRepository.findAppointmentsByUserId(customerId)).thenReturn(singletonList(any()));

        List<Appointment> appointmentList = appointmentService.findListAppointmentByCustomerId(customerId);

        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(1);
        verify(appointmentRepository).findAppointmentsByUserId(customerId);
    }

    @Test
    public void should_find_and_return_all_appointment_by_ouvrierId() {
        Long ouvId = 1L;
        when(appointmentRepository.findAppointmentByOuvrierId(ouvId)).thenReturn(singletonList(any()));

        List<Appointment> appointmentList = appointmentService.findListAppointmentByOuvrierId(ouvId);

        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(1);
        verify(appointmentRepository).findAppointmentByOuvrierId(ouvId);
    }

    @Test
    public void should_find_and_return_all_top4_appointment_by_ouvrierId() {
        Long ouvId = 1L;
        when(appointmentRepository.findTop4AppointmentOrderByCreatedDateDesc(ouvId)).thenReturn(singletonList(any()));

        List<Appointment> appointmentList = appointmentService.findTop4AppointmentOrderByCreatedDateDesc(ouvId);

        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(1);
        verify(appointmentRepository).findTop4AppointmentOrderByCreatedDateDesc(ouvId);
    }

    @Test
    public void should_find_and_return_all_top10_appointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        when(appointmentRepository.findTop10PendingAppointmentOrderByCreatedDateDesc()).thenReturn(singletonList(appointment));

        List<Appointment> appointmentList = appointmentService.findTop10PendingAppointmentOrderByCreatedDateDesc();

        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(1);
        verify(appointmentRepository).findTop10PendingAppointmentOrderByCreatedDateDesc();
        assertThat(appointmentList.get(0)).isEqualTo(appointment);
    }

    @Test
    public void should_find_and_return_all_pending_appointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        when(appointmentRepository.findAppointmentsByStatusWaiting()).thenReturn(singletonList(appointment));

        List<Appointment> appointmentList = appointmentService.findListAppointmentByStatusPending();

        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(1);
        verify(appointmentRepository).findAppointmentsByStatusWaiting();
        assertThat(appointmentList.get(0)).isEqualTo(appointment);
    }

    /*
    @Test
    public void should_count_and_return_all_appointment_peer_month() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setReference("APP01");
        appointment.setStatusOfAppointment("waiting");
        appointment.setAppointmentDate(new Date());

        when(appointmentRepository.countNumberOfAppointmentByMonth()).thenReturn(singletonList(anyObject()));

        List<?> appointmentList = appointmentService.countNumberTotalOfAppointmentPeerMonth();

        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(1);
        verify(appointmentRepository).countNumberOfAppointmentByMonth();
        assertThat(appointmentList.get(0)).isEqualTo(appointment);
    }*/

    @Test
    public void should_delete_one_appointment() {
        doNothing().when(appointmentRepository).deleteById(anyLong());

        appointmentService.delete(anyLong());
        verify(appointmentRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(appointmentRepository);
    }


}
