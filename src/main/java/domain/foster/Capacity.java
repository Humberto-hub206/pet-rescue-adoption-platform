package domain.foster;

public class Capacity {

    private final int value;

    public Capacity(int value) {

        if (value <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be greater than zero"
            );
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}