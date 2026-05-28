import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    @Test
    void rescuedPetShouldStartUnavailableForAdoption() {

        Pet pet = new Pet( new PetName("Bolt"));

        assertFalse(pet.isAvailableForAdoption());
        
    }
    @Test
    void shouldAllowAdoptionForHealthyAndVaccinatedPets(){
        Pet pet = new Pet(new PetName("Mel"));

        pet.updateHealthStatus(HealthStatus.HEALTHY);
        pet.vaccinate();
        pet.makeAvailableForAdoption();
        assertTrue(pet.isAvailableForAdoption());
    }
        
    @Test
    void shouldNotAllowAdoptionForSickPets(){
        Pet pet = new Pet(new PetName("Thor"));

        pet.vaccinate();
        assertThrows(IllegalStateException.class, pet::makeAvailableForAdoption);
    }

    @Test
    void shouldAdoptiAvailablePet(){
        Pet pet = new Pet(new PetName("Luna"));

        pet.updateHealthStatus(HealthStatus.HEALTHY);
        pet.vaccinate();
        pet.makeAvailableForAdoption();
        pet.adopt();
        assertTrue(pet.isAdopted());
    }
}