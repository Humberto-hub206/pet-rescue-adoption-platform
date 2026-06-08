package application.foster;

import domain.foster.*;
import domain.pet.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AssignPetToFosterHomeUseCaseTest {

    private FosterHome createFosterHome() {

        return new FosterHome(
                new CaretakerName("Maria Silva"),
                new Address("Rua das Flores"),
                new PhoneNumber("82999999999"),
                new Capacity(2)
        );
    }

    private Pet createPet() {

        return new Pet(
                new PetName("Bolt"),
                Species.DOG,
                new PetAge(2)
        );
    }

    @Test
    void shouldAssignPetToFosterHome() {

        FosterHome fosterHome =
                createFosterHome();

        Pet pet =
                createPet();

        FakeFosterHomeRepository repository =
                new FakeFosterHomeRepository();

        repository.save(fosterHome);

        AssignPetToFosterHomeUseCase useCase =
                new AssignPetToFosterHomeUseCase(repository);

        useCase.execute(
                fosterHome.getId(),
                pet,
                LocalDate.now(),
                LocalDate.now().plusDays(10)
        );

        assertEquals(
                1,
                fosterHome.getAssignments().size()
        );
    }

    @Test
    void shouldThrowWhenFosterHomeDoesNotExist() {

        FakeFosterHomeRepository repository =
                new FakeFosterHomeRepository();

        AssignPetToFosterHomeUseCase useCase =
                new AssignPetToFosterHomeUseCase(repository);

        Pet pet =
                createPet();

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        new FosterHomeId(),
                        pet,
                        LocalDate.now(),
                        LocalDate.now().plusDays(5)
                )
        );
    }
}