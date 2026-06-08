package application.pet;

import domain.pet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MakePetAvailableForAdoptionUseCaseTest {

    private Pet createPet() {

        Pet pet = new Pet(
                new PetName("Max"),
                Species.DOG,
                new PetAge(3)
        );

        pet.vaccinate();

        return pet;
    }

    @Test
    void shouldMakePetAvailableForAdoption() {

        FakePetRepository repository =
                new FakePetRepository();

        Pet pet = createPet();

        repository.save(pet);

        MakePetAvailableForAdoptionUseCase useCase =
                new MakePetAvailableForAdoptionUseCase(
                        repository
                );

        useCase.execute(pet.getId());

        assertTrue(
                pet.isAvailableForAdoption()
        );
    }

    @Test
    void shouldThrowWhenPetDoesNotExist() {

        FakePetRepository repository =
                new FakePetRepository();

        MakePetAvailableForAdoptionUseCase useCase =
                new MakePetAvailableForAdoptionUseCase(
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