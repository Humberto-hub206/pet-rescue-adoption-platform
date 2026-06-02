package domain.foster;

import java.util.UUID;

public class FosterHomeId {

    private final UUID value;

    public FosterHomeId() {
        this.value = UUID.randomUUID();
    }

    public UUID getValue() {
        return value;
    }
}