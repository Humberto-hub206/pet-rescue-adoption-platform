package domain.pet;

public class Pet {

    private final PetName name;

    private final PetId id;

    private final Species species;

    private final PetAge age;

    private HealthStatus healthStatus;

    private VaccinationStatus vaccinationStatus;

    private boolean availableForAdoption;

    private boolean adopted;

    public Pet(PetName name, Species species, PetAge age) {

        this.id = new PetId();

        this.name = name;

        this.species = species;

        this.age = age;

        this.healthStatus = HealthStatus.SICK;

        this.vaccinationStatus = VaccinationStatus.NOT_VACCINATED;

        this.availableForAdoption = false;

        this.adopted = false;
    }

    public void updateHealthStatus(
            HealthStatus healthStatus
    ) {

        this.healthStatus = healthStatus;
    }

    public void vaccinate() {

        this.vaccinationStatus =
                VaccinationStatus.VACCINATED;
    }

    public void makeAvailableForAdoption() {

        if (healthStatus != HealthStatus.HEALTHY) {

            throw new IllegalStateException(
                    "Pet não está saudável"
            );
        }

        if (vaccinationStatus != VaccinationStatus.VACCINATED) {

            throw new IllegalStateException(
                    "Pet não está vacinado"
            );
        }

        if (adopted) {

            throw new IllegalStateException(
                    "Pet já foi adotado"
            );
        }

        this.availableForAdoption = true;
    }

    public void adopt() {

        if (!availableForAdoption) {

            throw new IllegalStateException(
                    "Pet is not available"
            );
        }

        this.adopted = true;

        this.availableForAdoption = false;
    }

    public boolean isAvailableForAdoption() {
        return availableForAdoption;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public VaccinationStatus getVaccinationStatus() {
        return vaccinationStatus;
    }

    public String getName() {
        return name.getValue();
    }

}