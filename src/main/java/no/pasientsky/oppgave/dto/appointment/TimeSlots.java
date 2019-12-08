package no.pasientsky.oppgave.dto.appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public class TimeSlots {

    private UUID id;
    private UUID calendar_id;
    private UUID type_id;
    private LocalDateTime start;
    private LocalDateTime end;

    public TimeSlots() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getType_id() {
        return type_id;
    }

    public void setType_id(UUID type_id) {
        this.type_id = type_id;
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
        return "TimeSlots{" +
                "id=" + id +
                ", calendar_id=" + calendar_id +
                ", type_id=" + type_id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
