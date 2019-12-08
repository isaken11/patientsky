package no.pasientsky.oppgave.map;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.pasientsky.oppgave.config.Configuration;
import no.pasientsky.oppgave.dto.appointment.Appointment;
import no.pasientsky.oppgave.dto.appointment.Patient;
import no.pasientsky.oppgave.dto.appointment.TimeSlots;
import no.pasientsky.oppgave.dto.appointment.Timeslottypes;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentsMapTest {

    private static AppointmentsMap appointmentsMap;

    @Before
    public void setUp() throws Exception {
        final ObjectMapper objectMapper = new Configuration().objectMapper();
        appointmentsMap = new AppointmentsMap(objectMapper);
    }

    @Test
    public void testLoadAppointments() {
        final HashMap<UUID, Appointment> list = appointmentsMap.getAllAppointments();
        assertThat(list.size()).isEqualTo(142);
    }

    @Test
    public void testLoadCalendars() {
        final HashMap<UUID, HashMap<UUID, Appointment>> list = appointmentsMap.getAllCalendars();
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(UUID.fromString("48cadf26-975e-11e5-b9c2-c8e0eb18c1e9")).size()).isEqualTo(39);
        assertThat(list.get(UUID.fromString("48644c7a-975e-11e5-a090-c8e0eb18c1e9")).size()).isEqualTo(47);
        assertThat(list.get(UUID.fromString("452dccfc-975e-11e5-bfa5-c8e0eb18c1e9")).size()).isEqualTo(56);
        for (final Map.Entry<UUID, HashMap<UUID, Appointment>> entry : list.entrySet()) {
            System.out.println("Appointment id" + entry.getKey());
            System.out.println(entry.getValue().size());
            System.out.println("-----------");

        }
    }

    @Test
    public void testLoadTimeslots() throws Exception {
        final HashMap<UUID, TimeSlots> list = appointmentsMap.getAllTimeslots();
        assertThat(list.size()).isEqualTo(493);
        for (final Map.Entry<UUID, TimeSlots> entry : list.entrySet()) {
            System.out.println(entry);
        }
    }

    @Test
    public void testTimeslottypes() throws Exception {
        final HashMap<UUID, Timeslottypes> list = appointmentsMap.getAllTimeslottypes();
        assertThat(list.size()).isEqualTo(4);
        for (final Map.Entry<UUID, Timeslottypes> entry : list.entrySet()) {
            System.out.println(entry);
        }
    }

    @Test
    public void testPatients() throws Exception {
        final HashMap<UUID, Patient> list = appointmentsMap.getAllPatients();
        assertThat(list.size()).isEqualTo(21);
        for (final Map.Entry<UUID, Patient> entry : list.entrySet()) {
            System.out.println(entry);
        }
    }
}
