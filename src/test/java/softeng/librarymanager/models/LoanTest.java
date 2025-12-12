package softeng.librarymanager.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    @Test
    void testValidLoanCreation() {
        Student s = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book b = new Book("T", "A", "1234567890123", 2020, 3);

        Loan loan = new Loan(s, b, LocalDate.now().plusDays(10));

        assertNotNull(loan);
        assertEquals(s, loan.getStudent());
        assertEquals(b, loan.getBook());
        assertFalse(loan.isReturned());
        assertEquals(2, b.getAvailableCopies()); // decremento
        assertEquals(1, s.getActiveLoans().size());
    }

    @Test
    void testReturnLoanRestoresState() {
        Student s = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book b = new Book("T", "A", "1234567890123", 2020, 1);

        Loan loan = new Loan(s, b, LocalDate.now().plusDays(10));
        loan.returnLoan();

        assertTrue(loan.isReturned());
        assertEquals(1, b.getAvailableCopies());
        assertEquals(0, s.getActiveLoans().size());
    }

    @Test
    void testLoanInvalidIfBookUnavailable() {
        Student s = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book b = new Book("T", "A", "1234567890123", 2020, 0);

        assertThrows(IllegalArgumentException.class,
                () -> new Loan(s, b, LocalDate.now().plusDays(7)));
    }

    @Test
    void testCompareTo() {
        Student s = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book b = new Book("T", "A", "1234567890123", 2020, 10);

        Loan l1 = new Loan(s, b, LocalDate.now().plusDays(10));
        Loan l2 = new Loan(s, b, LocalDate.now().plusDays(20));

        assertTrue(l1.compareTo(l2) < 0);
    }

    @Test
    void testEqualsAndHashCode() {
        Student s = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book b = new Book("T", "A", "1234567890123", 2020, 10);

        Loan l1 = new Loan(s, b, LocalDate.now().plusDays(10));
        Loan l2 = new Loan(s, b, LocalDate.now().plusDays(20));

        // Loan ID è incrementale, quindi l1 ≠ l2
        assertNotEquals(l1, l2);
        assertNotEquals(l1.hashCode(), l2.hashCode());
    }
}
