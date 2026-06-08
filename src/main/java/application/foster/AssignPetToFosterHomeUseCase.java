package application.foster;

import application.foster.FosterHome;
import application.foster.FosterHomeRepository;
import domain.pet.Pet;
import application.pet.PetRepository;

public class AssignPetToFosterHomeUseCase {

    private final PetRepository petRepository;
    private final FosterHomeRepository fosterHomeRepository;

    public AssignPetToFosterHomeUseCase(PetRepository petRepository, FosterHomeRepository fosterHomeRepository) {
        this.petRepository = petRepository;
        this.fosterHomeRepository = fosterHomeRepository;
    }

    public void execute(String petId, String fosterHomeId) {
       
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet não encontrado com o ID: " + petId));

        FosterHome fosterHome = fosterHomeRepository.findById(fosterHomeId)
                .orElseThrow(() -> new IllegalArgumentException("Lar temporário não encontrado com o ID: " + fosterHomeId));

        fosterHome.assignPet(pet.getId());

        fosterHomeRepository.save(fosterHome);
    }
}

