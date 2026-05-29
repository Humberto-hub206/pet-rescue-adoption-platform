package domain.pet;

import java.util.UUID;

public class PetId {

    private final UUID value;

    public PetId() {

        this.value = UUID.randomUUID();
    }

    public UUID getValue() {

        return value;
    }

}