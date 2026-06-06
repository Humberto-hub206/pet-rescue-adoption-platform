package application.adoption;

import domain.adoption.AdoptionProcess;
import domain.adoption.AdoptionProcessId;

public class CompleteAdoptionUseCase {

    private final AdoptionProcessRepository repository;

    public CompleteAdoptionUseCase(
            AdoptionProcessRepository repository) {

        this.repository = repository;
    }

    public void execute(
            AdoptionProcessId processId) {

        AdoptionProcess process =
                repository.findById(processId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Adoption process not found"));

        process.completeAdoption();

        repository.save(process);
    }
}