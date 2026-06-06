package application.pet;

import domain.pet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecoverPetUseCaseTest {

    private Pet createPet() {

        return new Pet(
                new PetName("Luna"),
                Species.CAT,
                new PetAge(2)
        );
    }

    @Test
    void shouldRecoverPet() {

        FakePetRepository repository =
                new FakePetRepository();

        Pet pet = createPet();

        pet.markAsSick();
        pet.startTreatment();

        repository.save(pet);

        RecoverPetUseCase useCase =
                new RecoverPetUseCase(repository);

        useCase.execute(pet.getId());

        assertTrue(
                pet.isHealthy()
        );
    }

    @Test
    void shouldThrowWhenPetDoesNotExist() {

        FakePetRepository repository =
                new FakePetRepository();

        RecoverPetUseCase useCase =
                new RecoverPetUseCase(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        new PetId()
                )
        );
    }
}