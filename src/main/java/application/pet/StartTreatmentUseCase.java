package application.pet;

import domain.pet.Pet;
import domain.pet.PetId;

public class StartTreatmentUseCase {
    private final PetRepository petRepository;

    public StartTreatmentUseCase(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void execute(PetId petId) {

        Pet pet = petRepository.findById(petId).orElseThrow(
            () -> new IllegalArgumentException(
                    "Pet not found"));

        pet.startTreatment();

        petRepository.save(pet);
    }
}
