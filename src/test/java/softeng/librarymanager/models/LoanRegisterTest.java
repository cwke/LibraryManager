package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import java.util.ArrayList;
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

    /*
     * Inizializzo due Loan che utilizzerò per verificare le diverse
     * operazioni messe a disposizione dallo StudentRegister.
     * */
    @BeforeEach
    void setUp(){
        loanRegister = new LoanRegister();

        newStudent = new Student("Fabrizio", "Acerra", "0612709404", "f.acerra4@studenti.unisa.it");
        List<String> newAuthors = new ArrayList<>(); newAuthors.add("Natale Affinita"); newAuthors.add("Hermann Galluccio");
        List<String> oldAuthors = new ArrayList<>(); oldAuthors.add("Natale Affinita"); oldAuthors.add("Fabrizio Acerra");
        newBook = new Book("Libro", newAuthors, "0000000000000", 1900, 1400);
        oldBook = new Book("orbiL", oldAuthors, "1111111111111", 1090, 41000);oldStudent = new Student("Natale", "Affinita", "0612709745", "n.affinita@studenti.unisa.it");

        oldLoan = new Loan(oldStudent, oldBook, LocalDate.of(2025, 12, 12));
        newLoan = new Loan(newStudent, newBook, LocalDate.of(2026, 10, 12));
    }

    /*
     * Il costruttore deve garantire 1) Le invarianti alla fine dell'operazione -> studentRegisteR != null
     * 2) Che il catalogo studenti sia vuoto.
     * */
    @Test
    void testLoanRegister(){
        assertNotNull(loanRegister);
        List<Loan> list = loanRegister.getRegisterList();
        assertTrue(list.isEmpty());
    }

    /*
     * Dato un ingresso != null valido secondo RegisterValidator il metodo add() deve garantire che, al termine dell'operazione, il prestito sia stato
     * effettivamente inserito nel catalogo. Si noti che utilizzo il metodo getRegisterList() per quest'operazione e le seguenti
     * poiché il relativo caso di test mi garantisce la corrispondenza tra la List restituita da suddetto metodo ed il catalogo prestiti
     * (salvo ulteriori operazioni sul catalogo successive rispetto all'invocazione del metodo).
     * */
    @Test
    void testAdd() {
        loanRegister.add(newLoan);
        List<Loan> list = loanRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newLoan));
    }

    /*
     * Dato un ingresso "newLoan" != null ed un prestito "oldLoan" già presente nel catalogo, modify() deve
     * garantire che al termine dell'operazione il riferimento al Loan "oldLoan" già presente resti invariato e che
     * i suoi dati (tranne i non modificabili, quindi tutti tranne il studentId) risultino pari a quelli di newBook.
     * Anche in questo metodo utilizzo il metodo getRegisterList() per i motivi citati nel commento del metodo di test precedente.
     * */
    @Test
    void testModify() {
        loanRegister.add(oldLoan);
        loanRegister.modify(oldLoan, newLoan);
        List<Loan> list = loanRegister.getRegisterList();

        assertEquals(1, list.size());

        Loan modifiedLoan = list.get(0);
        assertEquals(newLoan.getLoanEnd(), modifiedLoan.getLoanEnd());
        assertEquals(oldLoan.getStudent(), modifiedLoan.getStudent());
        assertEquals(oldLoan.getBook(), modifiedLoan.getBook());
        assertTrue(modifiedLoan == oldLoan);
    }

    /*
     * Dato un ingresso "toRemove" (in questo caso oldLoan) già presente nel catalogo, il metodo
     * remove() deve garantire che al termine dell'operazione il prestito non sia più presente nel catalogo.
     * */
    @Test
    void testRemove1() {
        loanRegister.add(oldLoan);
        loanRegister.add(newLoan);
        loanRegister.remove(oldLoan);
        List<Loan> list = loanRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newLoan));
    }

    /*
     * Dato un ingresso !null con attributi !null (garantito dal costrutture di Book)
     * questo metodo deve restituire "false" se un loan identico (quindi con stesso Student, Book e non ancora terminato) è
     * già presente nel catalogo, true altrimenti.
     * */
    @Test
    void testIsUnique1() {
        loanRegister.add(newLoan);
        assertFalse(loanRegister.isUnique(newLoan));
    }

    @Test
    void testIsUnique2() {
        assertTrue(loanRegister.isUnique(newLoan));
    }

    /*
     * Questo metodo deve garantire che la List restituita contenga tutti gli elementi presenti
     * nel catalogo, ordinati prima per titolo e poi per autori.
     * */
    @Test
    void testGetValuesList() {
        loanRegister.add(newLoan);
        loanRegister.add(oldLoan);
        List<Loan> list = loanRegister.getRegisterList();
        assertEquals(2, list.size());
        assertTrue(list.contains(newLoan));
        assertTrue(list.contains(oldLoan));
    }
}