package domain.pet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {

    private final List<MedicalEvent> events;

    public MedicalRecord() {
        this.events = new ArrayList<>();
    }

    public void addEvent(
            String description,
            LocalDate date
    ) {

        events.add(
                new MedicalEvent(
                        description,
                        date
                )
        );
    }

    public List<MedicalEvent> getEvents() {
        return List.copyOf(events);
    }
}