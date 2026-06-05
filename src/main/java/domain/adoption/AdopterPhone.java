package domain.adoption;

public class AdopterPhone {
    private final String value;

    public AdopterPhone(String value) {

        if (value == null ||
                !value.matches("\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}")) {

            throw new IllegalArgumentException(
                    "Invalid phone number"
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
