package no.pasientsky.oppgave.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.pasientsky.oppgave.dto.appointment.Appointment;
import no.pasientsky.oppgave.dto.appointment.Appointments;
import no.pasientsky.oppgave.dto.appointment.Patient;
import no.pasientsky.oppgave.dto.appointment.TimeSlots;
import no.pasientsky.oppgave.dto.appointment.Timeslottypes;

@Component
public class AppointmentsMap {

    private final HashMap<UUID, Appointment> appointmentMap; // <AppointmentId, Appointment>
    private final HashMap<UUID, HashMap<UUID, Appointment>> calendarMap; // <CalendarId, <AppointmentId, Appointment>>
    private final HashMap<UUID, HashMap<UUID, TimeSlots>> timeslotMap; // <CalendarId, <TimeSlotsId, <TimeSlots>>
    private final HashMap<UUID, Timeslottypes> timeslottypesMap; // <TimeSlotsId, TimeSlots>
    private final HashMap<UUID, Patient> patientMap;
    private final ObjectMapper objectMapper;

    public AppointmentsMap(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.appointmentMap = new HashMap<>();
        this.calendarMap = new HashMap<>();
        this.timeslotMap = new HashMap<>();
        this.timeslottypesMap = new HashMap<>();
        this.patientMap = new HashMap<>();
    }

    public HashMap<UUID, Appointment> getAllAppointments() {
        if (appointmentMap.isEmpty()) {
            loadMap();
        }
        return appointmentMap;
    }

    public HashMap<UUID, HashMap<UUID, Appointment>> getAllCalendars() {
        if (calendarMap.isEmpty()) {
            loadMap();
        }
        return calendarMap;
    }

    public List<TimeSlots> getTimeslotByCalendarUuid(final List<UUID> uuids) {
        final List<TimeSlots> timeSlotsList = new ArrayList<>();
        for (final UUID uuid : uuids) {
            if (getAllTimeslots().containsKey(uuid)) {
                timeSlotsList.addAll(getAllTimeslots().get(uuid).values());
            }
        }
        return timeSlotsList;
    }

    public HashMap<UUID, HashMap<UUID, TimeSlots>> getAllTimeslots() {
        if (timeslotMap.isEmpty()) {
            loadMap();
        }
        return timeslotMap;
    }

    public HashMap<UUID, Timeslottypes> getAllTimeslottypes() {
        if (timeslottypesMap.isEmpty()) {
            loadMap();
        }
        return timeslottypesMap;
    }

    public HashMap<UUID, Patient> getAllPatients() {
        if (patientMap.isEmpty()) {
            loadMap();
        }
        return patientMap;
    }

    private void loadMap() {
        try {
            final Appointments appointmentsFileOne = loadFromFile("Danny_boy.json");
            final Appointments appointmentsFileTwo = loadFromFile("Emma_Win.json");
            final Appointments appointmentsFileThree = loadFromFile("Joanna_Hef.json");

            // Appointments //
            loadAppointments(appointmentsFileOne);
            loadAppointments(appointmentsFileTwo);
            loadAppointments(appointmentsFileThree);

            // Timeslots //
            loadTimeslots(appointmentsFileOne);
            loadTimeslots(appointmentsFileTwo);
            loadTimeslots(appointmentsFileThree);

            // Timeslottypes //
            loadTimeslottypes(appointmentsFileOne);
            loadTimeslottypes(appointmentsFileTwo);
            loadTimeslottypes(appointmentsFileThree);

            // patient //
            loadPatients(appointmentsFileOne);
            loadPatients(appointmentsFileTwo);
            loadPatients(appointmentsFileThree);

        } catch (final IOException e) {
            System.out.println("Failed under loading from file :(");
            System.out.println(e);
        }
    }

    private void loadPatients(final Appointments appointments) {
        patientMap.putAll(appointments.getPatient_meta().getPatients());
    }

    private void loadTimeslottypes(final Appointments appointments) {
        for (final Timeslottypes timeslottypes : appointments.getTimeslottypes()) {
            timeslottypesMap.put(timeslottypes.getId(), timeslottypes);
        }
    }

    private void loadTimeslots(final Appointments appointments) {
        for (final TimeSlots timeSlots : appointments.getTimeslots()) {
            if (!timeslotMap.containsKey(timeSlots.getCalendar_id())) {
                final HashMap<UUID, TimeSlots> subMap = new HashMap<>();
                subMap.put(timeSlots.getId(), timeSlots);
                timeslotMap.put(timeSlots.getCalendar_id(), subMap);
            } else {
                final HashMap<UUID, TimeSlots> subMap = timeslotMap.get(timeSlots.getCalendar_id());
                subMap.put(timeSlots.getId(), timeSlots);
                timeslotMap.put(timeSlots.getCalendar_id(), subMap);
            }
        }
    }

    private void loadAppointments(final Appointments appointments) {
        for (final Appointment appointment : appointments.getAppointments()) {
            appointmentMap.put(appointment.getId(), appointment);
            loadCalendarMap(appointment);
        }
    }

    private void loadCalendarMap(final Appointment appointment) {
        if (!calendarMap.containsKey(appointment.getCalendar_id())) {
            final HashMap<UUID, Appointment> subMap = new HashMap<>();
            subMap.put(appointment.getId(), appointment);
            calendarMap.put(appointment.getCalendar_id(), subMap);
        } else {
            final HashMap<UUID, Appointment> subMap = calendarMap.get(appointment.getCalendar_id());
            subMap.put(appointment.getId(), appointment);
            calendarMap.put(appointment.getCalendar_id(), subMap);
        }
    }

    private Appointments loadFromFile(final String filename) throws IOException {
        return objectMapper.readValue(new ClassPathResource(filename).getFile(), Appointments.class);
    }
}