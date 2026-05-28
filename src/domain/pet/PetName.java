public class PetName {
    private final String value;

    public PetName(String value) {
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException("O nome do Pet não pode ser nulo ou vazio.");
        }
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
