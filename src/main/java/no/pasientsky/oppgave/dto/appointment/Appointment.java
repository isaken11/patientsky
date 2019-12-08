package no.pasientsky.oppgave.dto.appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    private UUID id;
    private UUID patient_id;
    private UUID calendar_id;
    private LocalDateTime start;
    private LocalDateTime end;

    public Appointment() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(UUID patient_id) {
        this.patient_id = patient_id;
    }

    public UUID getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(UUID calendar_id) {
        this.calendar_id = calendar_id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", calendar_id=" + calendar_id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
