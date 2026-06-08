package application.pet;

import domain.pet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StartTreatmentUseCaseTest {

    private Pet createPet() {

        return new Pet(
                new PetName("Bolt"),
                Species.DOG,
                new PetAge(2)
        );
    }

    @Test
    void shouldStartTreatment() {

        FakePetRepository repository =
                new FakePetRepository();

        Pet pet = createPet();

        pet.markAsSick();

        repository.save(pet);

        StartTreatmentUseCase useCase =
                new StartTreatmentUseCase(repository);

        useCase.execute(pet.getId());

        assertEquals(
                HealthStatus.RECOVERING,
                pet.getHealthStatus()
        );
    }

    @Test
    void shouldThrowWhenPetDoesNotExist() {

        FakePetRepository repository =
                new FakePetRepository();

        StartTreatmentUseCase useCase =
                new StartTreatmentUseCase(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        new PetId()
                )
        );
    }
}