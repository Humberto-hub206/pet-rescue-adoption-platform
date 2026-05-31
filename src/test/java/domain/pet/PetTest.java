package domain.pet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    @Test
    void rescuedPetShouldStartUnavailableForAdoption() {

        Pet pet = new Pet(
                new PetName("Bolt"),
                Species.DOG,
                new PetAge(2)
        );

        assertFalse(pet.isAvailableForAdoption());
    }

    @Test
    void shouldBeEligibleForAdoptionWhenHealthyAndVaccinated() {

        Pet pet = new Pet(
                new PetName("Mia"),
                Species.CAT,
                new PetAge(3)
        );

        pet.vaccinate();

        assertTrue(pet.isEligibleForAdoption());
    }

    @Test
    void shouldNotBeEligibleForAdoptionWhenSick() {

        Pet pet = new Pet(
                new PetName("Thor"),
                Species.DOG,
                new PetAge(4)
        );

        pet.vaccinate();
        pet.markAsSick();

        assertFalse(pet.isEligibleForAdoption());
    }

    @Test
    void shouldMakePetAvailableForAdoption() {

        Pet pet = new Pet(
                new PetName("Luna"),
                Species.CAT,
                new PetAge(1)
        );

        pet.vaccinate();

        pet.makeAvailableForAdoption();

        assertTrue(pet.isAvailableForAdoption());
    }

    @Test
    void shouldNotMakeSickPetAvailableForAdoption() {

        Pet pet = new Pet(
                new PetName("Thor"),
                Species.DOG,
                new PetAge(4)
        );

        pet.vaccinate();
        pet.markAsSick();

        assertThrows(
                IllegalStateException.class,
                pet::makeAvailableForAdoption
        );
    }

    @Test
    void shouldAdoptAvailablePet() {

        Pet pet = new Pet(
                new PetName("Luna"),
                Species.CAT,
                new PetAge(1)
        );

        pet.vaccinate();

        pet.makeAvailableForAdoption();

        pet.adopt();

        assertTrue(pet.isAdopted());
    }

    @Test
    void shouldNotAdoptPetThatIsNotAvailable() {

        Pet pet = new Pet(
                new PetName("Luna"),
                Species.CAT,
                new PetAge(1)
        );

        assertThrows(
                IllegalStateException.class,
                pet::adopt
        );
    }

    @Test
    void shouldNotAllowNegativeAge() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new PetAge(-1)
        );
    }

    @Test
    void shouldNotVaccinateAdoptedPet() {

        Pet pet = new Pet(
                new PetName("Max"),
                Species.DOG,
                new PetAge(5)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();
        pet.adopt();

        assertThrows(
                IllegalStateException.class,
                pet::vaccinate
        );
    }

    @Test
    void shouldNotStartTreatmentForAdoptedPet() {

        Pet pet = new Pet(
                new PetName("Bella"),
                Species.CAT,
                new PetAge(2)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();
        pet.adopt();

        assertThrows(
                IllegalStateException.class,
                pet::startTreatment
        );
    }

    @Test
    void shouldNotMakeAdoptedPetAvailableForAdoption() {

        Pet pet = new Pet(
                new PetName("Charlie"),
                Species.DOG,
                new PetAge(3)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();
        pet.adopt();

        assertThrows(
                IllegalStateException.class,
                pet::makeAvailableForAdoption
        );
    }

    @Test
    void shouldStartTreatmentWhenPetIsSick() {

        Pet pet = new Pet(
                new PetName("Bob"),
                Species.DOG,
                new PetAge(3)
        );

        pet.markAsSick();

        pet.startTreatment();

        assertEquals(
                HealthStatus.RECOVERING,
                pet.getHealthStatus()
        );
    }

    @Test
    void shouldNotStartTreatmentWhenPetIsHealthy() {

        Pet pet = new Pet(
                new PetName("Bob"),
                Species.DOG,
                new PetAge(3)
        );

        assertThrows(
                IllegalStateException.class,
                pet::startTreatment
        );
    }

    @Test
    void shouldRecoverPetWhenRecovering() {

        Pet pet = new Pet(
                new PetName("Nina"),
                Species.CAT,
                new PetAge(2)
        );

        pet.markAsSick();

        pet.startTreatment();

        pet.recover();

        assertTrue(pet.isHealthy());
    }

    @Test
    void shouldNotRecoverHealthyPet() {

        Pet pet = new Pet(
                new PetName("Nina"),
                Species.CAT,
                new PetAge(2)
        );

        assertThrows(
                IllegalStateException.class,
                pet::recover
        );
    }

    @Test
    void shouldNotVaccinatePetTwice() {

        Pet pet = new Pet(
                new PetName("Rex"),
                Species.DOG,
                new PetAge(2)
        );

        pet.vaccinate();

        assertThrows(
                IllegalStateException.class,
                pet::vaccinate
        );
    }

    @Test
    void shouldRegisterMedicalEventWhenVaccinated() {

        Pet pet = new Pet(
                new PetName("Rex"),
                Species.DOG,
                new PetAge(2)
        );

        pet.vaccinate();

        assertEquals(
                1,
                pet.getMedicalRecord()
                        .getEvents()
                        .size()
        );
    }

    @Test
    void shouldRegisterMedicalEventWhenPetIsAdopted() {

        Pet pet = new Pet(
                new PetName("Luna"),
                Species.CAT,
                new PetAge(1)
        );

        pet.vaccinate();

        pet.makeAvailableForAdoption();

        pet.adopt();

        assertFalse(
                pet.getMedicalRecord()
                        .getEvents()
                        .isEmpty()
        );
    }
}