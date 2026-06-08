package application.foster;

import domain.foster.FosterHome;
import domain.foster.FosterHomeId;
import domain.pet.Pet;

public class RemovePetFromFosterHomeUseCase {

    private final FosterHomeRepository repository;

    public RemovePetFromFosterHomeUseCase(
            FosterHomeRepository repository) {

        this.repository = repository;
    }

    public void execute(
            FosterHomeId fosterHomeId,
            Pet pet) {

        FosterHome fosterHome =
            repository.findById(fosterHomeId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Foster home not found"));

        fosterHome.removePet(pet);

        repository.save(fosterHome);
    }
}