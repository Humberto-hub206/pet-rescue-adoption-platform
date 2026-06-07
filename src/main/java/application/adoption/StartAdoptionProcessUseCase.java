package application.adoption;

import domain.adoption.AdopterCpf;
import domain.adoption.AdopterName;
import domain.adoption.AdopterPhone;
import domain.adoption.AdoptionProcess;
import domain.pet.Pet;

public class StartAdoptionProcessUseCase {
     private final AdoptionProcessRepository repository;

    public StartAdoptionProcessUseCase(
            AdoptionProcessRepository repository) {

        this.repository = repository;
    }

    public AdoptionProcess execute(
            Pet pet,
            AdopterName name,
            AdopterCpf cpf,
            AdopterPhone phone) {

        AdoptionProcess process =
                new AdoptionProcess(
                        pet,
                        name,
                        cpf,
                        phone
                );

        repository.save(process);

        return process;
    }
}
