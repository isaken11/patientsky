package no.pasientsky.oppgave.dto.appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public class AvailableTime {
    private final UUID calendarId;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public AvailableTime(final UUID calendarId, final LocalDateTime start, final LocalDateTime end) {
        this.calendarId = calendarId;
        this.start = start;
        this.end = end;
    }

    public UUID getCalendarId() {
        return calendarId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "AvailableTime{" +
                "calendarId=" + calendarId +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
