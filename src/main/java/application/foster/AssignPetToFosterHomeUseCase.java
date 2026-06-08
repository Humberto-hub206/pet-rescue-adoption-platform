package application.foster;

import domain.foster.FosterHome;
import domain.foster.FosterHomeId;
import domain.pet.Pet;

import java.time.LocalDate;

public class AssignPetToFosterHomeUseCase {

    private final FosterHomeRepository repository;

    public AssignPetToFosterHomeUseCase(
            FosterHomeRepository repository) {

        this.repository = repository;
    }

    public void execute(
            FosterHomeId fosterHomeId,
            Pet pet,
            LocalDate startDate,
            LocalDate endDate) {

        FosterHome fosterHome =
            repository.findById(fosterHomeId)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Foster home not found"));

        fosterHome.assignPet(
                pet,
                startDate,
                endDate
        );

        repository.save(fosterHome);
    }
}