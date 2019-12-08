package no.pasientsky.oppgave.dto;

import java.util.List;

import no.pasientsky.oppgave.dto.appointment.AvailableTime;

public class ResponseDto {

    private List<AvailableTime> availableTimes;

    public ResponseDto() {
    }

    public List<AvailableTime> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<AvailableTime> availableTimes) {
        this.availableTimes = availableTimes;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "availableTimes=" + availableTimes +
                '}';
    }
}
