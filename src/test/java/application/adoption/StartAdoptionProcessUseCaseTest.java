package application.adoption;

import domain.adoption.*;
import domain.pet.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StartAdoptionProcessUseCaseTest {

    @Test
    void shouldStartAdoptionProcess() {

        Pet pet = new Pet(
                new PetName("Bolt"),
                Species.DOG,
                new PetAge(2)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();

        FakeAdoptionProcessRepository repository =
                new FakeAdoptionProcessRepository();

        StartAdoptionProcessUseCase useCase =
                new StartAdoptionProcessUseCase(repository);

        AdoptionProcess process =
                useCase.execute(
                        pet,
                        new AdopterName("Maria Silva"),
                        new AdopterCpf("529.982.247-25"),
                        new AdopterPhone("82999999999")
                );

        assertNotNull(process);

        assertTrue(process.isPending());

        AdoptionProcess savedProcess =
                repository.findById(process.getId())
                        .orElseThrow();

        assertEquals(
                process.getId(),
                savedProcess.getId()
        );
    }

    @Test
    void shouldNotStartProcessForUnavailablePet() {

    Pet pet = new Pet(
            new PetName("Thor"),
            Species.DOG,
            new PetAge(3)
    );

    FakeAdoptionProcessRepository repository =
            new FakeAdoptionProcessRepository();

    StartAdoptionProcessUseCase useCase =
            new StartAdoptionProcessUseCase(repository);

    assertThrows(
            IllegalStateException.class,
            () -> useCase.execute(
                    pet,
                    new AdopterName("Maria Silva"),
                    new AdopterCpf("529.982.247-25"),
                    new AdopterPhone("82999999999")
            )
    );
}
}