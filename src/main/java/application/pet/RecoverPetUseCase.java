package application.pet;

import domain.pet.Pet;
import domain.pet.PetId;

public class RecoverPetUseCase {
    private final PetRepository petRepository;

    public RecoverPetUseCase(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void execute(PetId petId) {

        Pet pet = petRepository.findById(petId).orElseThrow(
            () -> new IllegalArgumentException(
                "Pet not found"));

        pet.recover();

        petRepository.save(pet);
    }
}
