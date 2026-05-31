package domain.pet;

import java.time.LocalDate;

public class MedicalEvent {

    private final String description;

    private final LocalDate date;

    public MedicalEvent(
            String description,
            LocalDate date
    ) {

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                    "Description cannot be empty"
            );
        }

        if (date == null) {
            throw new IllegalArgumentException(
                    "Date cannot be null"
            );
        }

        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}