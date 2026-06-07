package application.adoption;

import domain.adoption.*;
import domain.pet.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RejectAdoptionUseCaseTest {

    @Test
    void shouldRejectProcess() {

        Pet pet = new Pet(
                new PetName("Luna"),
                Species.CAT,
                new PetAge(2)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();

        AdoptionProcess process =
                new AdoptionProcess(
                        pet,
                        new AdopterName("Carlos"),
                        new AdopterCpf("12345678901"),
                        new AdopterPhone("82999999999")
                );

        FakeAdoptionProcessRepository repository =
                new FakeAdoptionProcessRepository();

        repository.save(process);

        RejectAdoptionUseCase useCase =
                new RejectAdoptionUseCase(repository);

        useCase.execute(
                process.getId(),
                "Reprovado"
        );

        assertTrue(process.isRejected());
    }
}