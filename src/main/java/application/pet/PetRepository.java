package application.pet;

import  domain.pet.Pet;
import domain.pet.PetId;
import java.util.Optional;

public interface PetRepository {
    Optional<Pet> findById(PetId id);

    void save(Pet pet);
}
