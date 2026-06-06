package application.pet;

import domain.pet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarkPetAsSickUseCaseTest {

    private Pet createPet() {

        return new Pet(
                new PetName("Thor"),
                Species.DOG,
                new PetAge(3)
        );
    }

    @Test
    void shouldMarkPetAsSick() {

        FakePetRepository repository =
                new FakePetRepository();

        Pet pet = createPet();

        repository.save(pet);

        MarkPetAsSickUseCase useCase =
                new MarkPetAsSickUseCase(repository);

        useCase.execute(pet.getId());

        assertEquals(
                HealthStatus.SICK,
                pet.getHealthStatus()
        );
    }

    @Test
    void shouldThrowWhenPetDoesNotExist() {

        FakePetRepository repository =
                new FakePetRepository();

        MarkPetAsSickUseCase useCase =
                new MarkPetAsSickUseCase(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        new PetId()
                )
        );
    }
}