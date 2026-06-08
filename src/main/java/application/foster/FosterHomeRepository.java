package application.foster;

import domain.foster.FosterHome;
import domain.foster.FosterHomeId;
import java.util.Optional;

public interface FosterHomeRepository {
    Optional<FosterHome> findById(FosterHomeId id);
    void save(FosterHome fosterHome);
}