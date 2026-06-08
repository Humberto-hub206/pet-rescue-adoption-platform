package application.foster;

import java.util.Optional;

public interface FosterHomeRepository {
    Optional<FosterHome> findById(String id);
    void save(FosterHome fosterHome);
}