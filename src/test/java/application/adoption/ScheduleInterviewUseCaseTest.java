package application.adoption;

import domain.adoption.*;
import domain.pet.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleInterviewUseCaseTest {

    @Test
    void shouldScheduleInterview() {

        Pet pet = new Pet(
                new PetName("Rex"),
                Species.DOG,
                new PetAge(2)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();

        AdoptionProcess process =
                new AdoptionProcess(
                        pet,
                        new AdopterName("Joao"),
                        new AdopterCpf("123.456.789-01"),
                        new AdopterPhone("82999999999")
                );

        FakeAdoptionProcessRepository repository =
                new FakeAdoptionProcessRepository();

        repository.save(process);

        ScheduleInterviewUseCase useCase =
                new ScheduleInterviewUseCase(repository);

        LocalDateTime interviewDate =
                LocalDateTime.now().plusDays(1);

        useCase.execute(
                process.getId(),
                interviewDate
        );

        assertEquals(
                interviewDate,
                process.getInterviewDate()
        );
    }
}