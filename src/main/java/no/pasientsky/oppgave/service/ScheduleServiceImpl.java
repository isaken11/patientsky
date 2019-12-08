package no.pasientsky.oppgave.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import no.pasientsky.oppgave.dto.appointment.AvailableTime;
import no.pasientsky.oppgave.dto.appointment.TimeSlots;
import no.pasientsky.oppgave.dto.appointment.Timeslottypes;
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
        for (final TimeSlots timeSlot : appointmentsMap.getTimeslotByCalendarUuid(calendarIds)) {
            if (isAvailableTime(startPeriodToSearch, endPeriodToSearch, timeSlot.getStart(), timeSlot.getEnd(), duration)) {
                final Timeslottypes timeslottype = appointmentsMap.getAllTimeslottypes().get(timeSlot.getType_id());
                availableTimes.add(new AvailableTime(timeSlot.getCalendar_id(), timeSlot.getStart(), timeSlot.getEnd(), timeslottype));
            }
        }
        return availableTimes;
    }

    private boolean isAvailableTime(final LocalDateTime startPeriodToSearch, final LocalDateTime endPeriodToSearch,
                                    final LocalDateTime start, final LocalDateTime end, final Integer duration) {
        return isBeforeOrEqual(startPeriodToSearch, start)
                && isNotAfterOrEqual(endPeriodToSearch, end)
                && isLongEnoughToBook(start, end, duration);
    }

    private boolean isBeforeOrEqual(final LocalDateTime periodToSearch, final LocalDateTime start) {
        return periodToSearch.isBefore(start) || periodToSearch.isEqual(start);
    }

    private boolean isNotAfterOrEqual(final LocalDateTime periodToSearchWithDuration, final LocalDateTime end) {
        return end.isEqual(periodToSearchWithDuration) || end.isBefore(periodToSearchWithDuration);
    }

    private boolean isLongEnoughToBook(final LocalDateTime start, final LocalDateTime end, final Integer duration) {
        final LocalDateTime startWithDuration = start.plusMinutes(duration);
        return startWithDuration.isBefore(end) || startWithDuration.isEqual(end);
    }
}
