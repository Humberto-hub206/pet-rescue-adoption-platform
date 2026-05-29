package domain.pet;

public class PetAge {

    private final int value;

    public PetAge(int value) {

        if (value < 0) {

            throw new IllegalArgumentException(
                    "Pet age cannot be negative"
            );
        }

        this.value = value;
    }

    public int getValue() {

        return value;
    }

}