package domain.foster;

public class PhoneNumber {

    private final String value;

    public PhoneNumber(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Phone number cannot be empty"
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}