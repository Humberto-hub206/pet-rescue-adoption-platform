package domain.adoption;

import domain.pet.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AdoptionProcessTest {

    private Pet createAvailablePet() {

        Pet pet = new Pet(
                new PetName("Rex"),
                Species.DOG,
                new PetAge(2)
        );

        pet.vaccinate();
        pet.makeAvailableForAdoption();

        return pet;
    }

    private AdoptionProcess createProcess() {

        return new AdoptionProcess(
                createAvailablePet(),
                new AdopterName("João Silva"),
                new AdopterCpf("123.456.789-01"),
                new AdopterPhone("82999999999")
        );
    }

    @Test
    void shouldCreatePendingProcess() {

        AdoptionProcess process = createProcess();

        assertTrue(process.isPending());
    }

    @Test
    void shouldScheduleInterview() {

        AdoptionProcess process = createProcess();

        LocalDateTime interview =
                LocalDateTime.now().plusDays(2);

        process.scheduleInterview(interview);

        assertEquals(
                interview,
                process.getInterviewDate()
        );
    }

    @Test
    void shouldNotScheduleInterviewWithPastDate() {

        AdoptionProcess process = createProcess();

        assertThrows(
                IllegalArgumentException.class,
                () -> process.scheduleInterview(
                        LocalDateTime.now().minusDays(1)
                )
        );
    }

    @Test
    void shouldNotScheduleInterviewTwice() {

        AdoptionProcess process = createProcess();

        LocalDateTime interview =
                LocalDateTime.now().plusDays(2);

        process.scheduleInterview(interview);

        assertThrows(
                IllegalStateException.class,
                () -> process.scheduleInterview(
                        interview.plusDays(1)
                )
        );
    }

    @Test
    void shouldApproveProcess() {

        AdoptionProcess process = createProcess();

        process.scheduleInterview(
                LocalDateTime.now().plusDays(2)
        );

        process.approve("Approved");

        assertTrue(process.isApproved());
    }

    @Test
    void shouldNotApproveWithoutInterview() {

        AdoptionProcess process = createProcess();

        assertThrows(
                IllegalStateException.class,
                () -> process.approve("Approved")
        );
    }

    @Test
    void shouldRejectProcess() {

        AdoptionProcess process = createProcess();

        process.reject("Profile incompatible");

        assertTrue(process.isRejected());
    }

    @Test
    void shouldNotRejectTwice() {

        AdoptionProcess process = createProcess();

        process.reject("Reason");

        assertThrows(
                IllegalStateException.class,
                () -> process.reject("Another reason")
        );
    }

    @Test
    void shouldCompleteAdoption() {

        AdoptionProcess process = createProcess();

        process.scheduleInterview(
                LocalDateTime.now().plusDays(2)
        );

        process.approve("Approved");

        process.completeAdoption();

        assertTrue(process.isCompleted());
        assertTrue(
                process.getPet().isAdopted()
        );
    }

    @Test
    void shouldNotCompleteWithoutApproval() {

        AdoptionProcess process = createProcess();

        assertThrows(
                IllegalStateException.class,
                process::completeAdoption
        );
    }

    @Test
    void shouldNotCreateProcessWithUnavailablePet() {

        Pet pet = new Pet(
                new PetName("Max"),
                Species.DOG,
                new PetAge(3)
        );

        assertThrows(
                IllegalStateException.class,
                () -> new AdoptionProcess(
                        pet,
                        new AdopterName("João Silva"),
                        new AdopterCpf("123.456.789-01"),
                        new AdopterPhone("82999999999")
                )
        );
    }
}