package application.foster;

import domain.foster.*;
import domain.pet.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RemovePetFromFosterHomeUseCaseTest {

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
    void shouldRemovePetFromFosterHome() {

        FosterHome fosterHome =
                createFosterHome();

        Pet pet =
                createPet();

        fosterHome.assignPet(
                pet,
                LocalDate.now(),
                LocalDate.now().plusDays(10)
        );

        FakeFosterHomeRepository repository =
                new FakeFosterHomeRepository();

        repository.save(fosterHome);

        RemovePetFromFosterHomeUseCase useCase =
                new RemovePetFromFosterHomeUseCase(repository);

        useCase.execute(
                fosterHome.getId(),
                pet
        );

        assertEquals(
                0,
                fosterHome.getAssignments().size()
        );
    }

    @Test
    void shouldThrowWhenFosterHomeDoesNotExist() {

        FakeFosterHomeRepository repository =
                new FakeFosterHomeRepository();

        RemovePetFromFosterHomeUseCase useCase =
                new RemovePetFromFosterHomeUseCase(repository);

        Pet pet =
                createPet();

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        new FosterHomeId(),
                        pet
                )
        );
    }
}