package domain.adoption;

public class AdopterCpf {
    private final String value;

    public AdopterCpf(String value) {

        if (value == null ||
                !value.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {

            throw new IllegalArgumentException(
                    "Invalid CPF"
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
