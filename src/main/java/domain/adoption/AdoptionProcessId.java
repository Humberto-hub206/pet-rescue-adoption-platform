package domain.adoption;

import java.util.UUID;

public class AdoptionProcessId {
    private final UUID value;

    public AdoptionProcessId() {
        this.value = UUID.randomUUID();
    }

    public UUID getValue() {
        return value;
    }
}
