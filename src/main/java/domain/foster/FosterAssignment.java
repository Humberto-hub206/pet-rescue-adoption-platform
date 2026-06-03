package domain.foster;

import domain.pet.Pet;

import java.time.LocalDate;
import java.util.UUID;

public class FosterAssignment {

    private final UUID id;

    private final Pet pet;

    private final LocalDate startDate;

    private final LocalDate endDate;

    public FosterAssignment(
            Pet pet,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (pet == null) {
            throw new IllegalArgumentException(
                    "Pet cannot be null"
            );
        }

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException(
                    "Dates cannot be null"
            );
        }

        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException(
                    "End date must be after start date"
            );
        }

        this.id = UUID.randomUUID();
        this.pet = pet;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}