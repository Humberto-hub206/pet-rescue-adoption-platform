package domain.foster;

import domain.pet.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FosterHomeTest {

    private Pet createAvailablePet(String name) {
        return new Pet(
                new PetName(name),
                Species.DOG,
                new PetAge(2)
        );
    }

    private FosterHome createFosterHome(int capacity) {
        return new FosterHome(
                new CaretakerName("Maria Silva"),
                new Address("Rua das Flores, 123"),
                new PhoneNumber("82999990000"),
                new Capacity(capacity)
        );
    }

    private LocalDate today() {
        return LocalDate.now();
    }

    private LocalDate tomorrow() {
        return LocalDate.now().plusDays(1);
    }

    @Test
    void shouldAssignPetToFosterHome() {

        FosterHome home = createFosterHome(2);
        Pet pet = createAvailablePet("Rex");

        home.assignPet(pet, today(), tomorrow());

        assertEquals(1, home.getAssignments().size());
    }

    @Test
    void shouldNotExceedCapacity() {

        FosterHome home = createFosterHome(1);
        Pet pet1 = createAvailablePet("Rex");
        Pet pet2 = createAvailablePet("Luna");

        home.assignPet(pet1, today(), tomorrow());

        assertThrows(
                IllegalStateException.class,
                () -> home.assignPet(pet2, today(), tomorrow())
        );
    }

    @Test
    void shouldNotAssignAdoptedPet() {

        FosterHome home = createFosterHome(2);

        Pet pet = createAvailablePet("Max");
        pet.vaccinate();
        pet.makeAvailableForAdoption();
        pet.adopt();

        assertThrows(
                IllegalStateException.class,
                () -> home.assignPet(pet, today(), tomorrow())
        );
    }

    @Test
    void shouldRemovePetFromFosterHome() {

        FosterHome home = createFosterHome(2);
        Pet pet = createAvailablePet("Bolt");

        home.assignPet(pet, today(), tomorrow());
        home.removePet(pet);

        assertEquals(0, home.getAssignments().size());
    }

    @Test
    void shouldReturnTrueWhenHasCapacity() {

        FosterHome home = createFosterHome(3);

        assertTrue(home.hasCapacity());
    }

    @Test
    void shouldReturnFalseWhenAtFullCapacity() {

        FosterHome home = createFosterHome(1);
        Pet pet = createAvailablePet("Nina");

        home.assignPet(pet, today(), tomorrow());

        assertFalse(home.hasCapacity());
    }

    @Test
    void shouldNotCreateAssignmentWithEndBeforeStart() {

        Pet pet = createAvailablePet("Thor");

        assertThrows(
                IllegalArgumentException.class,
                () -> new FosterAssignment(
                        pet,
                        tomorrow(),
                        today()
                )
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithZeroCapacity() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Capacity(0)
        );
    }

    @Test
    void shouldThrowWhenRemovingPetNotInFosterHome() {

        FosterHome home = createFosterHome(2);
        Pet pet = createAvailablePet("Mia");

        assertThrows(
                IllegalStateException.class,
                () -> home.removePet(pet)
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithoutCaretakerName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CaretakerName(null)
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithoutAddress() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(null)
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithoutPhoneNumber() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhoneNumber(null)
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithoutCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Capacity(0)
        );
    }

    @Test
    void shouldReturnImmutableAssignmentsList() {
        FosterHome home = createFosterHome(2);

        assertThrows(
                UnsupportedOperationException.class,
                () -> home.getAssignments().add(null)
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithoutCaretakerName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CaretakerName(null)
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithoutAddress() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Address(null)
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithoutPhoneNumber() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhoneNumber(null)
        );
    }

    @Test
    void shouldNotCreateFosterHomeWithoutCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Capacity(0)
        );
    }

    @Test
    void shouldReturnImmutableAssignmentsList() {
        FosterHome home = createFosterHome(2);

        assertThrows(
                UnsupportedOperationException.class,
                () -> home.getAssignments().add(null)
        );
    }
}