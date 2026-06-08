package application.adoption;

import domain.adoption.AdoptionProcess;
import domain.adoption.AdoptionProcessId;

import java.time.LocalDateTime;

public class ScheduleInterviewUseCase {

    private final AdoptionProcessRepository repository;

    public ScheduleInterviewUseCase(
            AdoptionProcessRepository repository) {

        this.repository = repository;
    }

    public void execute(
            AdoptionProcessId processId,
            LocalDateTime interviewDate) {

        AdoptionProcess process =
                repository.findById(processId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Adoption process not found"));

        process.scheduleInterview(interviewDate);

        repository.save(process);
    }
}