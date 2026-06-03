package domain.foster;

public class CaretakerName {

    private final String value;

    public CaretakerName(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Caretaker name cannot be empty"
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}