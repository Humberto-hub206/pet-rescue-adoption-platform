package application.pet;

import domain.pet.Pet;
import domain.pet.PetId;

public class VaccinatePetUseCase {
    private final PetRepository petRepository;

    public VaccinatePetUseCase(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    public void execute(PetId petId) {

        Pet pet = petRepository.findById(petId).orElseThrow(
            () -> new IllegalArgumentException(
                "Pet not found"));

        pet.vaccinate();

        petRepository.save(pet);
    }
}


