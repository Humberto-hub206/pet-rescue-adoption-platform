package application.foster;

import domain.foster.FosterHome;
import domain.foster.FosterHomeId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeFosterHomeRepository
        implements FosterHomeRepository {

    private final Map<FosterHomeId, FosterHome> data =
            new HashMap<>();

    @Override
    public Optional<FosterHome> findById(
            FosterHomeId id) {

        return Optional.ofNullable(
                data.get(id)
        );
    }

    @Override
    public void save(
            FosterHome fosterHome) {

        data.put(
                fosterHome.getId(),
                fosterHome
        );
    }
}