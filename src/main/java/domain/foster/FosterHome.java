package domain.foster;

import domain.pet.Pet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FosterHome {

    private final FosterHomeId id;

    private final CaretakerName caretakerName;

    private final Address address;

    private final PhoneNumber phoneNumber;

    private final Capacity capacity;

    private final List<FosterAssignment> assignments;

    public FosterHome(
            CaretakerName caretakerName,
            Address address,
            PhoneNumber phoneNumber,
            Capacity capacity
    ) {
        if (caretakerName == null) {
            throw new IllegalArgumentException(
                    "Caretaker name cannot be null"
            );
        }

        if (address == null) {
            throw new IllegalArgumentException(
                    "Address cannot be null"
            );
        }

        if (phoneNumber == null) {
            throw new IllegalArgumentException(
                    "Phone number cannot be null"
            );
        }

        if (capacity == null) {
            throw new IllegalArgumentException(
                    "Capacity cannot be null"
            );
        }

        this.id = new FosterHomeId();
        this.caretakerName = caretakerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.assignments = new ArrayList<>();
    }

    public void assignPet(
            Pet pet,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (!hasCapacity()) {
            throw new IllegalStateException(
                    "Foster home has reached maximum capacity"
            );
        }

        if (pet.isAdopted()) {
            throw new IllegalStateException(
                    "Adopted pet cannot be sent to foster"
            );
        }

        assignments.add(
                new FosterAssignment(pet, startDate, endDate)
        );
    }

    public void removePet(Pet pet) {

        boolean removed = assignments.removeIf(
                a -> a.getPet().getId()
                        .getValue()
                        .equals(pet.getId().getValue())
        );

        if (!removed) {
            throw new IllegalStateException(
                    "Pet is not assigned to this foster home"
            );
        }
    }

    public boolean hasCapacity() {
        return assignments.size() < capacity.getValue();
    }

    // GETTERS

    public FosterHomeId getId() {
        return id;
    }

    public String getCaretakerName() {
        return caretakerName.getValue();
    }

    public String getAddress() {
        return address.getValue();
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }

    public int getCapacity() {
        return capacity.getValue();
    }

    public List<FosterAssignment> getAssignments() {
        return List.copyOf(assignments);
    }
}