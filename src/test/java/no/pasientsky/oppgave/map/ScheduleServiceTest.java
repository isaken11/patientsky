package no.pasientsky.oppgave.map;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.pasientsky.oppgave.config.Configuration;
import no.pasientsky.oppgave.dto.appointment.AvailableTime;
import no.pasientsky.oppgave.service.ScheduleService;
import no.pasientsky.oppgave.service.ScheduleServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleServiceTest {

    private ScheduleService scheduleService;

    @Before
    public void setUp() throws Exception {
        final ObjectMapper objectMapper = new Configuration().objectMapper();
        final AppointmentsMap appointmentsMap = new AppointmentsMap(objectMapper);
        this.scheduleService = new ScheduleServiceImpl(appointmentsMap);
    }

    @Test
    public void findAvailableTimeTest() throws Exception {
        final List<UUID> calendars = new ArrayList<>();
        calendars.add(UUID.fromString("48cadf26-975e-11e5-b9c2-c8e0eb18c1e9"));
        calendars.add(UUID.fromString("452dccfc-975e-11e5-bfa5-c8e0eb18c1e"));
        calendars.add(UUID.fromString("48644c7a-975e-11e5-a090-c8e0eb18c1e9"));
        final Integer duration = 30;
        final LocalDateTime startPeriodToSearch = LocalDateTime.parse("2019-04-23T08:00:00");
        final LocalDateTime endPeriodToSearch = LocalDateTime.parse("2019-04-27T21:45:00");

        final List<AvailableTime> availableTime = scheduleService.findAvailableTime(calendars, duration, startPeriodToSearch, endPeriodToSearch);
        assertThat(availableTime.size()).isEqualTo(142);

        System.out.println("Try booking time between " + startPeriodToSearch + " to " + endPeriodToSearch + " with duration " + duration + " minutes");
        System.out.println("Found: " + availableTime.size());
        for (final AvailableTime time : availableTime) {
            System.out.println("----------------------");
            System.out.println(time);
            System.out.println("----------------------");
        }
    }
}
