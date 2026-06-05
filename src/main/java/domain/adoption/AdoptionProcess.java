package domain.adoption;

import domain.pet.Pet;
import java.time.LocalDateTime;

public class AdoptionProcess {

    private final AdoptionProcessId id;

    private final Pet pet;

    private final AdopterName adopterName;

    private final AdopterCpf adopterCpf;

    private final AdopterPhone adopterPhone;

    private InterviewNotes interviewNotes;

    private AdoptionProcessStatus status;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime interviewDate;

    private LocalDateTime completedAt;

    public AdoptionProcess(Pet pet, AdopterName adopterName, AdopterCpf adopterCpf, AdopterPhone adopterPhone){
    
    if (pet == null) {
    throw new IllegalArgumentException(
            "Pet cannot be null"
    );
    }

    if (adopterName == null) {
        throw new IllegalArgumentException(
                "Adopter name cannot be null"
        );
    }

    if (adopterCpf == null) {
        throw new IllegalArgumentException(
                "Adopter cpf cannot be null"
        );
    }

    if (adopterPhone == null) {
        throw new IllegalArgumentException(
                "Adopter phone cannot be null"
        );
    }
    
    if (!pet.isAvailableForAdoption()) {

        throw new IllegalStateException(
                "Pet is not available for adoption"
        );
    }

    this.id = new AdoptionProcessId();

    this.pet = pet;

    this.adopterName = adopterName;

    this.adopterCpf = adopterCpf;

    this.adopterPhone = adopterPhone;

    this.status = AdoptionProcessStatus.PENDING;

    this.createdAt = LocalDateTime.now();

    this.updatedAt = LocalDateTime.now();
    }
    //agendamento de entrevista, rejeição e conclusão do processo
    public void scheduleInterview(LocalDateTime interviewDate) {
        
        if (this.interviewDate != null) {
            throw new IllegalStateException(
                    "Interview already scheduled"
            );
        }
        if (interviewDate == null) {
            throw new IllegalArgumentException(
                    "Interview date cannot be null"
            );
        }
        if (interviewDate.isBefore(LocalDateTime.now())) {

            throw new IllegalArgumentException(
                    "Interview date must be in the future"
            );
        }
        if (status != AdoptionProcessStatus.PENDING) {
            throw new IllegalStateException(
                    "Interview can only be scheduled for pending processes"
            );
        }

        this.interviewDate = interviewDate;

        this.updatedAt = LocalDateTime.now();
    }
    //rejeição do processo
    public void reject(String notes) {

        if (status == AdoptionProcessStatus.COMPLETED) {

            throw new IllegalStateException(
                    "Completed process cannot be rejected"
            );
        }
        if (status == AdoptionProcessStatus.REJECTED) {
            throw new IllegalStateException(
                    "Process is already rejected"
            );
        }

        this.interviewNotes = new InterviewNotes(notes);

        this.status = AdoptionProcessStatus.REJECTED;

        this.updatedAt = LocalDateTime.now();
    }

    public void approve(String notes) {

    if (status != AdoptionProcessStatus.PENDING) {

        throw new IllegalStateException(
                "Only pending processes can be approved"
        );
    }
    if (interviewDate == null) {
        throw new IllegalStateException(
                "Interview must be scheduled before approval"
        );
    }
    this.interviewNotes = new InterviewNotes(notes);

    this.status = AdoptionProcessStatus.APPROVED;

    this.updatedAt = LocalDateTime.now();
    }

    //conclusão do processo
    public void completeAdoption() {

    if (status != AdoptionProcessStatus.APPROVED) {

        throw new IllegalStateException(
                "Only approved processes can be completed"
        );
    }

    this.status =
            AdoptionProcessStatus.COMPLETED;

    this.completedAt =
            LocalDateTime.now();

    this.updatedAt =
            LocalDateTime.now();

    pet.adopt();
    }

    public boolean isPending() {
    return status == AdoptionProcessStatus.PENDING;
    }

    public boolean isApproved() {
        return status == AdoptionProcessStatus.APPROVED;
    }

    public boolean isRejected() {
        return status == AdoptionProcessStatus.REJECTED;
    }

    public boolean isCompleted() {
        return status == AdoptionProcessStatus.COMPLETED;
    }

    //getters
    public AdoptionProcessStatus getStatus() {
        return status;
    }
    public Pet getPet() {
        return pet;
    }
    public LocalDateTime getInterviewDate() {
        return interviewDate;
    }
    public InterviewNotes getInterviewNotes() {
        return interviewNotes;
    }
    public AdoptionProcessId getId() {
        return id;
    }
    public AdopterName getAdopterName() {
        return adopterName;
    }
    public AdopterCpf getAdopterCpf() {
        return adopterCpf;
    }
    public AdopterPhone getAdopterPhone() {
        return adopterPhone;
    }
}
