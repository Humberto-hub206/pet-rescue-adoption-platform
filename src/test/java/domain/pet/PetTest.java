package domain.pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    @Test
    void rescuedPetShouldStartUnavailableForAdoption() {

        Pet pet = new Pet( new PetName("Bolt"), Species.DOG, new PetAge(2));

        assertFalse(pet.isAvailableForAdoption());
        
    }
    @Test
    void shouldAllowAdoptionForHealthyAndVaccinatedPets(){
        Pet pet = new Pet(new PetName("Mia"), Species.CAT, new PetAge(3));

        pet.updateHealthStatus(HealthStatus.HEALTHY);
        pet.vaccinate();
        pet.makeAvailableForAdoption();
        assertTrue(pet.isAvailableForAdoption());
    }
        
    @Test
    void shouldNotAllowAdoptionForSickPets(){
        Pet pet = new Pet(new PetName("Thor"), Species.DOG, new PetAge(4));

        pet.vaccinate();
        assertThrows(IllegalStateException.class, pet::makeAvailableForAdoption);
    }

    @Test
    void shouldAdoptAvailablePet(){
        Pet pet = new Pet(new PetName("Luna"), Species.CAT, new PetAge(1));

        pet.updateHealthStatus(HealthStatus.HEALTHY);
        pet.vaccinate();
        pet.makeAvailableForAdoption();
        pet.adopt();
        assertTrue(pet.isAdopted());
    }
    @Test
    void shouldNotAllowNegativeAge(){
        assertThrows(IllegalArgumentException.class, () -> new PetAge(-1));
    }
}