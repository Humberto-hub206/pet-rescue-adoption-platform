package application.adoption;

import domain.adoption.AdoptionProcess;
import domain.adoption.AdoptionProcessId;

public class ApproveAdoptionUseCase {

    private final AdoptionProcessRepository repository;

    public ApproveAdoptionUseCase(
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

        process.approve(notes);

        repository.save(process);
    }
}