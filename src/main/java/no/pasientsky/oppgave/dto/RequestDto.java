package no.pasientsky.oppgave.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RequestDto {

    private List<UUID> calendarIds;
    private Integer duration;
    private LocalDateTime startPeriodToSearch;
    private LocalDateTime endPeriodToSearch;

    public RequestDto() {
    }

    public List<UUID> getCalendarIds() {
        return calendarIds;
    }

    public void setCalendarIds(List<UUID> calendarIds) {
        this.calendarIds = calendarIds;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartPeriodToSearch() {
        return startPeriodToSearch;
    }

    public void setStartPeriodToSearch(LocalDateTime startPeriodToSearch) {
        this.startPeriodToSearch = startPeriodToSearch;
    }

    public LocalDateTime getEndPeriodToSearch() {
        return endPeriodToSearch;
    }

    public void setEndPeriodToSearch(LocalDateTime endPeriodToSearch) {
        this.endPeriodToSearch = endPeriodToSearch;
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "calendarIds=" + calendarIds +
                ", duration=" + duration +
                ", startPeriodToSearch=" + startPeriodToSearch +
                ", endPeriodToSearch=" + endPeriodToSearch +
                '}';
    }
}
