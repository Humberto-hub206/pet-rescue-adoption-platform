package application.pet;

import domain.pet.Pet;
import domain.pet.PetId;

public class MakePetAvailableForAdoptionUseCase {
    private final PetRepository petRepository;

    public MakePetAvailableForAdoptionUseCase(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void execute(PetId petId) {

        Pet pet = petRepository.findById(petId).orElseThrow(
            () -> new IllegalArgumentException(
                "Pet not found"));

        pet.makeAvailableForAdoption();

        petRepository.save(pet);
    }
}
