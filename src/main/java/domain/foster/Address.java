package domain.foster;

public class Address {

    private final String value;

    public Address(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Address cannot be empty"
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}