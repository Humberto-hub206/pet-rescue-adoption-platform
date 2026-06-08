package application.adoption;

import domain.adoption.AdoptionProcess;
import domain.adoption.AdoptionProcessId;

public class RejectAdoptionUseCase {

    private final AdoptionProcessRepository repository;

    public RejectAdoptionUseCase(
            AdoptionProcessRepository repository) {

        this.repository = repository;
    }

    public void execute(
            AdoptionProcessId processId,
            String notes) {

        AdoptionProcess process =
                repository.findById(processId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Adoption process not found"));

        process.reject(notes);

        repository.save(process);
    }
}