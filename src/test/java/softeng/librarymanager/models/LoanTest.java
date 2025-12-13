package softeng.librarymanager.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private final LocalDate validLoanEnd = LocalDate.of(2025, Month.MARCH, 18);
    private final List<String> validAuthors = new ArrayList<>(Arrays.asList("Giovanni Verga"));

    //Test costruttore e di conseguenza dei getter e delle funzioni di validazione
    @Test
    void testLoan() {
        Student validStudent = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book validBook = new Book("La roba", validAuthors, "1234567890123", 1880, 10);
        //Creazione valida
        Loan loan = new Loan(validStudent, validBook, validLoanEnd);

        assertEquals(validStudent, loan.getStudent());
        assertEquals(validBook, loan.getBook());
        assertEquals(validLoanEnd, loan.getLoanEnd());
        assertEquals(loan, validStudent.getActiveLoans().get(0));
        assertEquals(9, validBook.getAvailableCopies());
        
        //Creazione invalida perché studente null
        assertThrows(IllegalArgumentException.class, () -> new Loan(null, validBook, validLoanEnd));
        
        //Creazione invalida perché studente non disponibile per prestito
        validStudent.addActiveLoan(loan);
        validStudent.addActiveLoan(loan);
        assertThrows(IllegalArgumentException.class, () -> new Loan(validStudent, validBook, validLoanEnd));
        
        //Creazione invalida perché libro null
        assertThrows(IllegalArgumentException.class, () -> new Loan(validStudent, null, validLoanEnd));
        
        //Creazione invalida perché libro non disponibile per prestito
        assertThrows(IllegalArgumentException.class, () -> new Loan(validStudent, new Book("La roba", validAuthors, "1234567890123", 1880, 0), validLoanEnd));
        
        //Creazione invalida perché data ultima di restituzione null
        assertThrows(IllegalArgumentException.class, () -> new Loan(validStudent, validBook, null));
    }

    @Test
    void testReturnLoan() {
        Student validStudent = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book validBook = new Book("La roba", validAuthors, "1234567890123", 1880, 10);
        Loan loan = new Loan(validStudent, validBook, validLoanEnd);
        
        //Verifica libro non restituito
        assertFalse(loan.isReturned());
        
        //Verifica libro restituito
        loan.returnLoan();
        assertTrue(loan.isReturned());
        assertTrue(validStudent.getActiveLoans().isEmpty());
        assertEquals(10, validBook.getAvailableCopies());
    }

    @Test
    void testCompareTo() {
        Student validStudent = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book validBook = new Book("La roba", validAuthors, "1234567890123", 1880, 10);

        Loan l1 = new Loan(validStudent, validBook, validLoanEnd);
        Loan l2 = new Loan(validStudent, validBook, validLoanEnd);
        Loan l3 = new Loan(validStudent, validBook, validLoanEnd);
        
        //Verifica prestito minore
        assertTrue(l2.compareTo(l3) < 0);
        
        //Verifica libro maggiore
        assertTrue(l2.compareTo(l1) > 0);
    }

    @Test
    void testEquals() {
        Student validStudent = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book validBook = new Book("La roba", validAuthors, "1234567890123", 1880, 10);
        Loan loan = new Loan(validStudent, validBook, validLoanEnd);
        
        //Verifica prestiti uguali
        assertEquals(loan, loan);
        
        //Verifica prestiti diversi
        Loan loan2 = new Loan(validStudent, validBook, validLoanEnd);
        assertFalse(loan.equals(loan2));
    }

    @Test
    void testHashCode() {
        Student validStudent = new Student("Mario", "Rossi", "1234567890", "m.rossi@studenti.unisa.it");
        Book validBook = new Book("La roba", validAuthors, "1234567890123", 1880, 10);
        Loan loan = new Loan(validStudent, validBook, validLoanEnd);
        
        //Verifica hashcode prestiti uguali
        assertEquals(loan.hashCode(), loan.hashCode());
        
        //Verifica hashcode prestiti diversi
        Loan loan2 = new Loan(validStudent, validBook, validLoanEnd);
        assertFalse(loan.hashCode() == loan2.hashCode());
    }
}
