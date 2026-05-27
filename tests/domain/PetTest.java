import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    @Test
    void rescuedPetShouldStartUnavailableForAdoption() {

        boolean availableForAdoption = false;

        assertFalse(availableForAdoption);

    }

}