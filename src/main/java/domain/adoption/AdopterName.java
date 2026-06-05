package domain.adoption;

public class AdopterName {
    private final String value;

    public AdopterName(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Name cannot be empty"
            );
        }

        if (value.length() < 3) {
            throw new IllegalArgumentException(
                    "Name must have at least 3 characters"
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
