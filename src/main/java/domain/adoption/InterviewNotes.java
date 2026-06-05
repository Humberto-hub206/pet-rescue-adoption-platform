package domain.adoption;

public class InterviewNotes {
    private final String value;

    public InterviewNotes(String value) {

        if (value != null && value.length() > 1000) {

            throw new IllegalArgumentException(
                    "Notes too long"
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
