package no.pasientsky.oppgave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.pasientsky.oppgave.dto.RequestDto;
import no.pasientsky.oppgave.dto.ResponseDto;
import no.pasientsky.oppgave.dto.appointment.AvailableTime;
import no.pasientsky.oppgave.service.ScheduleService;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(final ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping(
            path = "/availabletime/v1",
            produces = "application/json"
    )
    public ResponseDto findAvailableTime(@RequestBody final RequestDto requestDto){
        final List<AvailableTime> availableTimes = scheduleService.findAvailableTime(
                requestDto.getCalendarIds(),
                requestDto.getDuration(),
                requestDto.getStartPeriodToSearch(),
                requestDto.getEndPeriodToSearch()
        );
        final ResponseDto responseDto = new ResponseDto();
        responseDto.setAvailableTimes(availableTimes);
        return responseDto;
    }
}
