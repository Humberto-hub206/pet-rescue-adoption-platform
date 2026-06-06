package application.adoption;

import domain.adoption.AdoptionProcess;
import domain.adoption.AdoptionProcessId;

import java.util.Optional;

public interface AdoptionProcessRepository {

    Optional<AdoptionProcess> findById(
            AdoptionProcessId id);

    void save(
            AdoptionProcess adoptionProcess);
}