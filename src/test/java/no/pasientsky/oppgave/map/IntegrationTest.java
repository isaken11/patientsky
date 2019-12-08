package no.pasientsky.oppgave.map;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import no.pasientsky.oppgave.OppgaveApplication;
import no.pasientsky.oppgave.dto.RequestDto;
import no.pasientsky.oppgave.dto.ResponseDto;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = OppgaveApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void integrationTest() {
        final List<UUID> calendars = new ArrayList<>();
        calendars.add(UUID.fromString("48cadf26-975e-11e5-b9c2-c8e0eb18c1e9"));
        calendars.add(UUID.fromString("452dccfc-975e-11e5-bfa5-c8e0eb18c1e"));
        calendars.add(UUID.fromString("48644c7a-975e-11e5-a090-c8e0eb18c1e9"));

        final Integer duration = 30;
        final LocalDateTime startPeriodToSearch = LocalDateTime.parse("2019-04-24T19:30:00");
        final LocalDateTime endPeriodToSearch = LocalDateTime.parse("2019-04-27T19:30:00");

        final RequestDto requestDto = new RequestDto();
        requestDto.setCalendarIds(calendars);
        requestDto.setDuration(duration);
        requestDto.setStartPeriodToSearch(startPeriodToSearch);
        requestDto.setEndPeriodToSearch(endPeriodToSearch);

        final ResponseEntity<ResponseDto> response = restTemplate.postForEntity(
                "http://localhost:9080/schedule/availabletime/v1",
                requestDto,
                ResponseDto.class
        );
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        final ResponseDto responseDto = response.getBody();
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getAvailableTimes()).isNotNull();
        assertThat(responseDto.getAvailableTimes().size()).isEqualTo(83);
    }
}
