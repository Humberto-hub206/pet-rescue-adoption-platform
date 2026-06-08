package application.adoption;

import domain.adoption.*;
import domain.pet.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CompleteAdoptionUseCaseTest {

    @Test
    void shouldCompleteAdoption() {

        Pet pet = new Pet(
                new PetName("Max"),
                Species.DOG,
                new PetAge(3)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();

        AdoptionProcess process =
                new AdoptionProcess(
                        pet,
                        new AdopterName("Ana"),
                        new AdopterCpf("123.456.789-01"),
                        new AdopterPhone("82999999999")
                );

        process.scheduleInterview(
                LocalDateTime.now().plusDays(1)
        );

        process.approve("Aprovado");

        FakeAdoptionProcessRepository repository =
                new FakeAdoptionProcessRepository();

        repository.save(process);

        CompleteAdoptionUseCase useCase =
                new CompleteAdoptionUseCase(repository);

        useCase.execute(process.getId());

        assertTrue(process.isCompleted());
    }
}
