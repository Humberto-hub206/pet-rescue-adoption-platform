package application.pet;

import domain.pet.Pet;
import domain.pet.PetId;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePetRepository implements PetRepository {

    private final Map<UUID, Pet> pets = new HashMap<>();

    @Override
    public Optional<Pet> findById(PetId id) {

        return Optional.ofNullable(pets.get(id.getValue()));
    }

    @Override
    public void save(Pet pet) {

        pets.put(
                pet.getId().getValue(),
                pet
        );
    }
}