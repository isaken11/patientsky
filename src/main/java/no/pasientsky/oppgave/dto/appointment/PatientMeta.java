package no.pasientsky.oppgave.dto.appointment;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import no.pasientsky.oppgave.deserialize.PatientMetaDeserializer;

@JsonDeserialize(using = PatientMetaDeserializer.class)
public class PatientMeta {

    private Map<UUID, Patient> patients;

    public PatientMeta() {
    }

    public Map<UUID, Patient> getPatients() {
        return patients;
    }

    public void setPatients(Map<UUID, Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "PatientMeta{" +
                "patients=" + patients +
                '}';
    }
}
