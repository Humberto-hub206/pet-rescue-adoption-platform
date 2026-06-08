package application.pet;

import domain.pet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VaccinatePetUseCaseTest {

    private Pet createPet() {

        return new Pet(
                new PetName("Rex"),
                Species.DOG,
                new PetAge(2)
        );
    }

    @Test
    void shouldVaccinatePet() {

        FakePetRepository repository =
                new FakePetRepository();

        Pet pet = createPet();

        repository.save(pet);

        VaccinatePetUseCase useCase =
                new VaccinatePetUseCase(
                        repository
                );

        useCase.execute(
                pet.getId()
        );

        assertTrue(
                pet.isVaccinated()
        );
    }

    @Test
    void shouldThrowWhenPetDoesNotExist() {

        FakePetRepository repository =
                new FakePetRepository();

        VaccinatePetUseCase useCase =
                new VaccinatePetUseCase(
                        repository
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        new PetId()
                )
        );
    }
}