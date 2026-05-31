package domain.pet;

import java.time.LocalDate;

public class Pet {

    private final PetName name;

    private final PetId id;

    private final Species species;

    private final PetAge age;

    private final MedicalRecord medicalRecord;

    private HealthStatus healthStatus;

    private VaccinationStatus vaccinationStatus;

    private AdoptionStatus adoptionStatus;

    public Pet(PetName name,Species species,PetAge age) {

        if (name == null) {
            throw new IllegalArgumentException(
                    "Name cannot be null"
            );
        }

        if (species == null) {
            throw new IllegalArgumentException(
                    "Species cannot be null"
            );
        }

        if (age == null) {
            throw new IllegalArgumentException(
                    "Age cannot be null"
            );
        }

        this.id = new PetId();

        this.name = name;

        this.species = species;

        this.age = age;

        this.healthStatus = HealthStatus.HEALTHY;

        this.vaccinationStatus =
                VaccinationStatus.NOT_VACCINATED;

        this.adoptionStatus =
                AdoptionStatus.WAITING_EVALUATION;

        this.medicalRecord =
                new MedicalRecord();
    }

    private void ensureNotAdopted() {

        if (isAdopted()) {
            throw new IllegalStateException(
                    "Adopted pet cannot be modified"
            );
        }
    }

    // HEALTH
    public void markAsSick() {

        ensureNotAdopted();

        this.healthStatus = HealthStatus.SICK;

        medicalRecord.addEvent(
                "Pet diagnosed as sick",
                LocalDate.now()
        );
    }

    public void startTreatment() {

        ensureNotAdopted();

        if (healthStatus != HealthStatus.SICK) {
            throw new IllegalStateException(
                    "Only sick pets can start treatment"
            );
        }

        this.healthStatus =
                HealthStatus.RECOVERING;

        medicalRecord.addEvent(
                "Treatment started",
                LocalDate.now()
        );
    }

    public void recover() {

        ensureNotAdopted();

        if (healthStatus != HealthStatus.RECOVERING) {
            throw new IllegalStateException(
                    "Pet is not recovering"
            );
        }

        this.healthStatus =
                HealthStatus.HEALTHY;

        medicalRecord.addEvent(
                "Pet recovered",
                LocalDate.now()
        );
    }

    // VACCINATION
    public void vaccinate() {

        ensureNotAdopted();

        if (isVaccinated()) {
            throw new IllegalStateException(
                    "Pet already vaccinated"
            );
        }

        this.vaccinationStatus =
                VaccinationStatus.VACCINATED;

        medicalRecord.addEvent(
                "Pet vaccinated",
                LocalDate.now()
        );
    }

    // ADOPTION
    public boolean isEligibleForAdoption() {

        return isHealthy()
                && isVaccinated()
                && !isAdopted();
    }

    public void makeAvailableForAdoption() {

        ensureNotAdopted();

        if (!isEligibleForAdoption()) {

            throw new IllegalStateException(
                    "Pet cannot be made available for adoption"
            );
        }

        this.adoptionStatus =
                AdoptionStatus.AVAILABLE;

        medicalRecord.addEvent(
                "Pet available for adoption",
                LocalDate.now()
        );
    }

    public void adopt() {

        if (!isAvailableForAdoption()) {

            throw new IllegalStateException(
                    "Pet is not available for adoption"
            );
        }

        this.adoptionStatus =
                AdoptionStatus.ADOPTED;

        medicalRecord.addEvent(
                "Pet adopted",
                LocalDate.now()
        );
    }

    // QUERIES
    public boolean isHealthy() {
        return healthStatus == HealthStatus.HEALTHY;
    }

    public boolean isVaccinated() {
        return vaccinationStatus ==
                VaccinationStatus.VACCINATED;
    }

    public boolean isAvailableForAdoption() {
        return adoptionStatus ==
                AdoptionStatus.AVAILABLE;
    }

    public boolean isAdopted() {
        return adoptionStatus ==
                AdoptionStatus.ADOPTED;
    }

    // GETTERS
    public PetId getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }

    public Species getSpecies() {
        return species;
    }

    public PetAge getAge() {
        return age;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public VaccinationStatus getVaccinationStatus() {
        return vaccinationStatus;
    }

    public AdoptionStatus getAdoptionStatus() {
        return adoptionStatus;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }
}