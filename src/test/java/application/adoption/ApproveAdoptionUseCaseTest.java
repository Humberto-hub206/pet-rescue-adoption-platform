package application.adoption;

import domain.adoption.*;
import domain.pet.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ApproveAdoptionUseCaseTest {

    @Test
    void shouldApproveProcess() {

        Pet pet = new Pet(
                new PetName("Bolt"),
                Species.DOG,
                new PetAge(2)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();

        AdoptionProcess process =
                new AdoptionProcess(
                        pet,
                        new AdopterName("Maria"),
                        new AdopterCpf("12345678901"),
                        new AdopterPhone("82999999999")
                );

        process.scheduleInterview(
                LocalDateTime.now().plusDays(1)
        );

        FakeAdoptionProcessRepository repository =
                new FakeAdoptionProcessRepository();

        repository.save(process);

        ApproveAdoptionUseCase useCase =
                new ApproveAdoptionUseCase(repository);

        useCase.execute(
                process.getId(),
                "Aprovado"
        );

        assertTrue(process.isApproved());
    }
}