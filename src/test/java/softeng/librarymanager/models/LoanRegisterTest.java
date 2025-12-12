package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class LoanRegisterTest {

    private LoanRegister loanRegister;
    private Loan oldLoan;
    private Loan newLoan;
    private Student newStudent;
    private Student oldStudent;
    private Book newBook;
    private Book oldBook;

    @BeforeEach
    void setUp(){
        loanRegister = new LoanRegister();

        newStudent = new Student("Fabrizio", "Acerra", "0612709404", "f.acerra4@studenti.unisa.it");
        newBook = new Book("Libro", "Natale Affinita, Jakub Cwiertka", "1111111111111", 1900, 1400);

        oldBook = new Book("orbiL", "Hermann Galluccio, Fabrizio Acerra", "0000000000000", 1090, 41000);
        oldStudent = new Student("Natale", "Affinita", "0612709745", "n.affinita@studenti.unisa.it");

        oldLoan = new Loan(oldStudent, oldBook, LocalDate.of(2025, 12, 12));
        newLoan = new Loan(newStudent, newBook, LocalDate.of(2026, 10, 12));
    }

    @Test
    void testLoanRegister(){
        assertNotNull(loanRegister);
        List<Loan> list = loanRegister.getRegister();
        assertTrue(list.isEmpty());
    }

    @Test
    void testAdd() {
        loanRegister.add(newLoan);
        List<Loan> list = loanRegister.getRegister();
        assertEquals(1, list.size());
        assertTrue(list.contains(newLoan));
    }

    @Test
    void testModify() {
        loanRegister.add(oldLoan);
        loanRegister.modify(oldLoan, newLoan);
        List<Loan> list = loanRegister.getRegister();

        assertEquals(1, list.size());

        Loan modifiedLoan = list.get(0);
        assertEquals(newLoan.getLoanEnd(), modifiedLoan.getLoanEnd());
        assertEquals(oldLoan.getStudent(), modifiedLoan.getStudent());
        assertEquals(oldLoan.getBook(), modifiedLoan.getBook());
        assertTrue(modifiedLoan == oldLoan);
    }

    @Test
    void testRemove1() {
        loanRegister.add(oldLoan);
        loanRegister.add(newLoan);
        loanRegister.remove(oldLoan);
        List<Loan> list = loanRegister.getRegister();
        assertEquals(1, list.size());
        assertTrue(list.contains(newLoan));
    }


    @Test
    void testIsValid1() {
        loanRegister.add(newLoan);
        assertFalse(loanRegister.isValid(newLoan));
    }

    @Test
    void testIsValid2() {
        assertTrue(loanRegister.isValid(newLoan));
    }

    @Test
    void testGetValuesList() {
        loanRegister.add(newLoan);
        loanRegister.add(oldLoan);
        List<Loan> list = loanRegister.getRegister();
        assertEquals(2, list.size());
        assertTrue(list.contains(newLoan));
        assertTrue(list.contains(oldLoan));
    }
}