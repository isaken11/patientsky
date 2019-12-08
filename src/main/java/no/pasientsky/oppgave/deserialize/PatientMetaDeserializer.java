package no.pasientsky.oppgave.deserialize;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.pasientsky.oppgave.config.Configuration;
import no.pasientsky.oppgave.dto.appointment.Patient;
import no.pasientsky.oppgave.dto.appointment.PatientMeta;

public class PatientMetaDeserializer extends JsonDeserializer<PatientMeta> {

    private ObjectMapper objectMapper;

    public PatientMetaDeserializer() {
        this.objectMapper = new Configuration().objectMapper();
    }

    @Override
    public PatientMeta deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        final Iterator<Map.Entry<String, JsonNode>> elements = jsonNode.fields();
        final HashMap<UUID, Patient> patientMap = new HashMap<>();

        while(elements.hasNext()){
            final Map.Entry<String, JsonNode> entry = elements.next();
            final Patient patient = objectMapper.convertValue(entry.getValue(), Patient.class);
            patientMap.put(UUID.fromString(entry.getKey()), patient);
        }

        final PatientMeta patientMeta = new PatientMeta();
        patientMeta.setPatients(patientMap);
        return patientMeta;
    }
}
