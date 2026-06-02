import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

enum AdoptionStatus {
    PENDING("Pendente"),
    APPROVED("Aprovado"),
    REJECTED("Rejeitado"),
    COMPLETED("Finalizado");

    private final String description;
    AdoptionStatus(String description) { this.description = description; }
    public String getDescription() { return description; }
}

enum PetStatus {
    AVAILABLE("Disponível"),
    ADOPTED("Adotado"),
    UNAVAILABLE("Indisponível");

    private final String description;
    PetStatus(String description) { this.description = description; }
    public String getDescription() { return description; }
}

// ==================== CLASSE PET ====================
class Pet {
    private final UUID id;
    private final String name;
    private final String species;
    private final int age;
    private PetStatus status;

    public Pet(String name, String species, int age) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.species = species;
        this.age = age;
        this.status = PetStatus.AVAILABLE;
    }

    public void markAsAdopted() {
        if (this.status != PetStatus.AVAILABLE) {
            throw new IllegalStateException("Pet não está disponível para adoção");
        }
        this.status = PetStatus.ADOPTED;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public PetStatus getStatus() { return status; }
}

// ==================== VALUE OBJECTS ====================
class AdopterName {
    private final String value;
    public AdopterName(String value) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException("Nome é obrigatório");
        if (value.length() < 3) throw new IllegalArgumentException("Nome deve ter pelo menos 3 caracteres");
        this.value = value;
    }
    public String getValue() { return value; }
}

class AdopterCpf {
    private final String value;
    public AdopterCpf(String value) {
        if (value == null || !value.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) 
            throw new IllegalArgumentException("CPF inválido. Use: xxx.xxx.xxx-xx");
        this.value = value;
    }
    public String getValue() { return value; }
}

class AdopterPhone {
    private final String value;
    public AdopterPhone(String value) {
        if (value == null || !value.matches("\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}")) 
            throw new IllegalArgumentException("Telefone inválido");
        this.value = value;
    }
    public String getValue() { return value; }
}

class InterviewNotes {
    private final String value;
    public InterviewNotes(String value) {
        if (value != null && value.length() > 1000) 
            throw new IllegalArgumentException("Observações muito longas (máx 1000 caracteres)");
        this.value = value;
    }
    public String getValue() { return value; }
}

// ==================== ENTIDADE PRINCIPAL ====================
public class AdoptionProcess {

    private final UUID id;
    private final Pet pet;
    private final AdopterName adopterName;
    private final AdopterCpf adopterCpf;
    private final AdopterPhone adopterPhone;
    private InterviewNotes interviewNotes;
    private AdoptionStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime interviewDate;
    private LocalDateTime completedAt;

    public AdoptionProcess(Pet pet, AdopterName adopterName, AdopterCpf adopterCpf, AdopterPhone adopterPhone) {
        if (pet.getStatus() != PetStatus.AVAILABLE) {
            throw new IllegalStateException("Só pets disponíveis podem iniciar processo de adoção");
        }
        
        this.id = UUID.randomUUID();
        this.pet = pet;
        this.adopterName = adopterName;
        this.adopterCpf = adopterCpf;
        this.adopterPhone = adopterPhone;
        this.status = AdoptionStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void scheduleInterview(LocalDateTime interviewDate) {
        if (this.status != AdoptionStatus.PENDING) {
            throw new IllegalStateException("Só é possível agendar entrevista em processos PENDENTES");
        }
        this.interviewDate = interviewDate;
        this.updatedAt = LocalDateTime.now();
    }

    public void approve(String notes) {
        if (this.status != AdoptionStatus.PENDING) {
            throw new IllegalStateException("Apenas processos PENDENTES podem ser aprovados");
        }
        this.interviewNotes = new InterviewNotes(notes);
        this.status = AdoptionStatus.APPROVED;
        this.updatedAt = LocalDateTime.now();
    }

    public void reject(String notes) {
        if (this.status == AdoptionStatus.COMPLETED) {
            throw new IllegalStateException("Processo já finalizado não pode ser rejeitado");
        }
        this.interviewNotes = new InterviewNotes(notes);
        this.status = AdoptionStatus.REJECTED;
        this.updatedAt = LocalDateTime.now();
    }

    public void completeAdoption() {
        if (this.status != AdoptionStatus.APPROVED) {
            throw new IllegalStateException("Apenas processos APROVADOS podem ser finalizados");
        }
        this.status = AdoptionStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        
        pet.markAsAdopted();
        
        System.out.println("🎉 Adoção FINALIZADA com sucesso!");
        System.out.println("Pet " + pet.getName() + " agora está ADOTADO.");
    }

    // ==================== GETTERS (corrigindo warnings) ====================
    public UUID getId() { return id; }
    public Pet getPet() { return pet; }
    public AdopterName getAdopterName() { return adopterName; }
    public AdopterCpf getAdopterCpf() { return adopterCpf; }
    public AdopterPhone getAdopterPhone() { return adopterPhone; }
    public InterviewNotes getInterviewNotes() { return interviewNotes; }
    public AdoptionStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public LocalDateTime getInterviewDate() { return interviewDate; }
    public LocalDateTime getCompletedAt() { return completedAt; }
}

// ==================== MAIN INTERATIVO ====================
class AdoptionDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("=== SISTEMA DE ADOÇÃO ===\n");

            // Pet
            System.out.println("--- Dados do Pet ---");
            System.out.print("Nome do Pet: ");
            String petName = sc.nextLine();
            System.out.print("Espécie: ");
            String species = sc.nextLine();
            System.out.print("Idade: ");
            int age = sc.nextInt();
            sc.nextLine();

            Pet pet = new Pet(petName, species, age);
            System.out.println("Pet criado → " + pet.getStatus() + "\n");

            // Adotante
            System.out.println("--- Dados do Adotante ---");
            System.out.print("Nome completo: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            AdoptionProcess processo = new AdoptionProcess(
                pet, 
                new AdopterName(nome),
                new AdopterCpf(cpf),
                new AdopterPhone(telefone)
            );

            System.out.println("\n✅ Processo criado com sucesso!");
            System.out.println("Pet: " + pet.getName());
            System.out.println("Adotante: " + processo.getAdopterName().getValue());
            System.out.println("CPF: " + processo.getAdopterCpf().getValue());
            System.out.println("Telefone: " + processo.getAdopterPhone().getValue());
            System.out.println("Criado em: " + processo.getCreatedAt());
            System.out.println("Status: " + processo.getStatus());

            // Fluxo
            System.out.print("\nAprovar processo? (s/n): ");
            if (sc.nextLine().trim().toLowerCase().equals("s")) {
                System.out.print("Observações da entrevista: ");
                String notes = sc.nextLine();
                processo.approve(notes);
                System.out.println("✅ Aprovado!");

                System.out.print("\nFinalizar adoção agora? (s/n): ");
                if (sc.nextLine().trim().toLowerCase().equals("s")) {
                    processo.completeAdoption();
                }
            } else {
                processo.reject("Não atendeu aos critérios.");
                System.out.println("❌ Processo rejeitado.");
            }

        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}