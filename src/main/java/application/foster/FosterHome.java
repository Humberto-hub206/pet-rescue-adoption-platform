package application.foster;

import java.util.HashSet;
import java.util.Set;

public class FosterHome {
    private final String id;
    private final String nomeProtetor;
    private final Set<String> petIdsHospedados = new HashSet<>();

    public FosterHome(String id, String nomeProtetor) {
        this.id = id;
        this.nomeProtetor = nomeProtetor;
    }

    public String getId() { return id; }

    public void assignPet(String petId) {
        this.petIdsHospedados.add(petId);
    }

    public void removePet(String petId) {
        this.petIdsHospedados.remove(petId);
    }

    public Set<String> getPetIdsHospedados() {
        return petIdsHospedados;
    }
}

