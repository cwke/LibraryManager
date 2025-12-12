package softeng.librarymanager.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testValidStudentCreation() {
        Student s = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");

        assertEquals("Mario", s.getName());
        assertEquals("Rossi", s.getSurname());
        assertEquals("1234567890", s.getStudentId());
        assertEquals("m.rossi@studenti.unisa.it", s.getEmail());
        assertTrue(s.getActiveLoans().isEmpty());
    }

    @Test
    void testInvalidEmailThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student("Mario", "Rossi", "1234567890", "email@gmail.com"));
    }

    @Test
    void testCopyUpdatesFields() {
        Student s1 = new Student("Mario", "Verdi", "0000000001", "m.verdi@studenti.unisa.it");
        Student s2 = new Student("Luca", "Bianchi", "0000000002", "l.bianchi@studenti.unisa.it");

        s1.copy(s2);

        assertEquals("Luca", s1.getName());
        assertEquals("Bianchi", s1.getSurname());
        assertEquals("l.bianchi@studenti.unisa.it", s1.getEmail());
        assertEquals("0000000001", s1.getStudentId()); // unchanged
    }

    @Test
    void testAddLoanLimit() {
        Student s = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book b = new Book("T", "A", "1234567890123", 2020, 10);
        //Altrimenti vengono aggiunti due volte i loan agli activeLoan
        Student s1 = new Student("Mario", "Rossi", "1234567891", "m.rossi@studenti.unisa.it");

        // Aggiungo tre prestiti
        new Loan(s, b, java.time.LocalDate.now().plusDays(7));
        new Loan(s, b, java.time.LocalDate.now().plusDays(7));
        new Loan(s, b, java.time.LocalDate.now().plusDays(7));

        assertFalse(s.isAvailableForLoan());

        // Il quarto deve fallire
        assertThrows(IllegalStateException.class,
                () -> s.addActiveLoan(new Loan(s1, b, java.time.LocalDate.now().plusDays(7))));
    }

    @Test
    void testEqualsAndHashCode() {
        Student s1 = new Student("A", "B", "1234567890", "a.b@studenti.unisa.it");
        Student s2 = new Student("X", "Y", "1234567890", "x.y@studenti.unisa.it");

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }
}
