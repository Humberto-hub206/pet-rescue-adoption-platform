package application.adoption;

import domain.adoption.AdoptionProcess;
import domain.adoption.AdoptionProcessId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeAdoptionProcessRepository
        implements AdoptionProcessRepository {

    private final Map<AdoptionProcessId, AdoptionProcess> data =
            new HashMap<>();

    @Override
    public Optional<AdoptionProcess> findById(
            AdoptionProcessId id) {

        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void save(
            AdoptionProcess adoptionProcess) {

        data.put(
                adoptionProcess.getId(),
                adoptionProcess
        );
    }
}