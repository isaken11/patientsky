package no.pasientsky.oppgave.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import no.pasientsky.oppgave.dto.appointment.AvailableTime;

public interface ScheduleService {

    List<AvailableTime> findAvailableTime(final List<UUID> calendarIds, final Integer duration,
                                          final LocalDateTime startPeriodToSearch, final LocalDateTime endPeriodToSearch);
}
