package softeng.librarymanager.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private final Student validStudent = new Student()
    private final Book validBook = new Book
    private final String validBookId = "1234567890123";
    private final int validPublishmentYear = 1880;
    private final int validAvailableCopies = 10;

    //Test costruttore e di conseguenza dei getter e delle funzioni di validazione
    @Test
    void testLoan() {
        //Creazione valida
        Book book = new Book(validTitle, validAuthors, validBookId, validPublishmentYear, validAvailableCopies);

        assertEquals(validTitle, book.getTitle());
        assertEquals(validAuthors, book.getAuthors());
        assertEquals(validBookId, book.getBookId());
        assertEquals(validPublishmentYear, book.getPublishmentYear());
        assertEquals(validAvailableCopies, book.getAvailableCopies());
        
        //Creazione invalida perché titolo null
        assertThrows(IllegalArgumentException.class, () -> new Book(null, validAuthors, validBookId, validPublishmentYear, validAvailableCopies));
        
        //Creazione invalida perché autori null
        assertThrows(IllegalArgumentException.class, () -> new Book(validTitle, null, validBookId, validPublishmentYear, validAvailableCopies));
        
        //Creazione invalida perché identificativo libro null
        assertThrows(IllegalArgumentException.class, () -> new Book(validTitle, validAuthors, null, validPublishmentYear, validAvailableCopies));
        
        //Creazione invalida perché lunghezza identificativo libro diversa da 13
        assertThrows(IllegalArgumentException.class, () -> new Book(validTitle, validAuthors, "12345", validPublishmentYear, validAvailableCopies));
        
        //Creazione invalida perché anno di pubblicazione minore di 0
        assertThrows(IllegalArgumentException.class, () -> new Book(validTitle, validAuthors, validBookId, -1, validAvailableCopies));
        
        //Creazione invalida perché numero di copie disponibili minore di 0
        assertThrows(IllegalArgumentException.class, () -> new Book(validTitle, validAuthors, validBookId, validPublishmentYear, -1));
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
