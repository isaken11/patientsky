package no.pasientsky.oppgave.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import no.pasientsky.oppgave.dto.appointment.Appointment;
import no.pasientsky.oppgave.dto.appointment.AvailableTime;
import no.pasientsky.oppgave.map.AppointmentsMap;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private AppointmentsMap appointmentsMap;

    public ScheduleServiceImpl(final AppointmentsMap appointmentsMap) {
        this.appointmentsMap = appointmentsMap;
    }

    @Override
    public List<AvailableTime> findAvailableTime(final List<UUID> calendarIds,
                                                 final Integer duration,
                                                 final LocalDateTime startPeriodToSearch,
                                                 final LocalDateTime endPeriodToSearch) {
        final List<AvailableTime> availableTimes = new ArrayList<>();
        final HashMap<UUID, Appointment> calendars = appointmentsMap.getCalendarsByUUIDS(calendarIds);
        if (!calendars.isEmpty()){
            for (final Map.Entry<UUID, Appointment> entry : calendars.entrySet()) {
                if (isAvailableTime(startPeriodToSearch, endPeriodToSearch, entry.getValue().getStart(), entry.getValue().getEnd(), duration)){
                    availableTimes.add(new AvailableTime(entry.getValue().getCalendar_id(), entry.getValue().getStart(), entry.getValue().getEnd()));
                }
            }
        }
        return availableTimes;
    }

    private boolean isAvailableTime(final LocalDateTime startPeriodToSearch, final LocalDateTime endPeriodToSearch,
                                    final LocalDateTime start, final LocalDateTime end, final Integer duration){
        final LocalDateTime endPeriodToSearchWithDuration = endPeriodToSearch.plusMinutes(duration);
        return isBeforeOrEqual(startPeriodToSearch, start)
                && isNotAfterOrEqual(endPeriodToSearchWithDuration, end)
                && isLongEnoughToBook(start, end, duration);
    }

    private boolean isBeforeOrEqual(final LocalDateTime periodToSearch, final LocalDateTime start){
        return start.isBefore(periodToSearch) || start.isEqual(periodToSearch);
    }

    private boolean isNotAfterOrEqual(final LocalDateTime periodToSearchWithDuration, final LocalDateTime end){
        return end.isEqual(periodToSearchWithDuration) || end.isBefore(periodToSearchWithDuration);
    }

    private boolean isLongEnoughToBook(final LocalDateTime start, final LocalDateTime end, final Integer duration){
        final LocalDateTime startWithDuration = start.plusMinutes(duration);
        return startWithDuration.isBefore(end) || startWithDuration.isEqual(end);
    }
}
