package no.pasientsky.oppgave.dto.appointment;

import java.util.List;

public class Appointments {
    private List<Appointment> appointments;
    private List<TimeSlots> timeslots;
    private List<Timeslottypes> timeslottypes;
    private PatientMeta patient_meta;

    public Appointments() {
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<TimeSlots> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List<TimeSlots> timeslots) {
        this.timeslots = timeslots;
    }

    public List<Timeslottypes> getTimeslottypes() {
        return timeslottypes;
    }

    public void setTimeslottypes(List<Timeslottypes> timeslottypes) {
        this.timeslottypes = timeslottypes;
    }

    public PatientMeta getPatient_meta() {
        return patient_meta;
    }

    public void setPatient_meta(PatientMeta patient_meta) {
        this.patient_meta = patient_meta;
    }

    @Override
    public String toString() {
        return "Appointments{" +
                "appointments=" + appointments +
                ", timeslots=" + timeslots +
                ", timeslottypes=" + timeslottypes +
                ", patient_meta=" + patient_meta +
                '}';
    }
}
