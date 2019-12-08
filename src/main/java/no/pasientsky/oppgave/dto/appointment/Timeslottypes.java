package no.pasientsky.oppgave.dto.appointment;

import java.util.UUID;

public class Timeslottypes {

    private UUID id;
    private String name;
    private Integer slot_size;
    private UUID clinic_id;

    public Timeslottypes() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSlot_size() {
        return slot_size;
    }

    public void setSlot_size(Integer slot_size) {
        this.slot_size = slot_size;
    }

    public UUID getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(UUID clinic_id) {
        this.clinic_id = clinic_id;
    }

    @Override
    public String toString() {
        return "Timeslottypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slot_size=" + slot_size +
                ", clinic_id=" + clinic_id +
                '}';
    }

    /*
          "id": "452935de-975e-11e5-ae1a-c8e0eb18c1e9",
      "name": "Vanlig",
      "slot_size": 15,
      "public_bookable": true,
      "color": "#ccc",
      "icon": "icon-home",
      "clinic_id": "00000000-0000-4000-a002-000000000002",
      "deleted": null,
      "out_of_office": false,
      "enabled": false
     */
}
