package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class LoanRegisterTest {

    private LoanRegister loanRegister;
    private Loan loan1;
    private Loan loan2;
    private Loan loan3;
    private Loan loan4;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    @BeforeEach
    void setUp(){
        loanRegister = new LoanRegister();

        student1 = new Student("Fabrizio", "Acerra", "1234564543", "f.acerra4@studenti.unisa.it");
        student2 = new Student("Natale", "Affinita", "2312342345", "n.affinita@studenti.unisa.it");
        student3 = new Student("Hermann", "Galluccio", "0001110001", "h.galluccio@studenti.unisa.it");
        student4 = new Student("Jakub", "Cwiertka", "0000102933", "j.cwiertka@studenti.unisa.it");
        List<String> authors1 = new ArrayList<>(); authors1.add("Luca Rossi"); authors1.add("Paolo Verdi");
        List<String> authors2 = new ArrayList<>(); authors2.add("Andrea Bianchi");
        book1 = new Book("Libro", authors1, "0000000000000", 1900, 1400);
        book2 = new Book("Manuale", authors2, "2222222222222", 1090, 41000);
        book3 = new Book("Romanzo", authors2, "3333333333333", 1090, 41000);
        book4 = new Book("Fiaba", authors1, "1111111111111", 1090, 41000);

        loan1 = new Loan(student2, book2, LocalDate.of(2025, 12, 12));
        loan2 = new Loan(student1, book1, LocalDate.of(2026, 10, 12));
        loan3 = new Loan(student3, book3, LocalDate.of(2020, 8, 4));
        loan4 = new Loan(student4, book4, LocalDate.of(2018, 8, 4));
    }

    /*
     * Il costruttore deve garantire 1) Le invarianti alla fine dell'operazione -> loanRegister != null
     * 2) Che il catalogo prestiti sia vuoto.
     * */
    @Test
    void testLoanRegister(){
        LoanRegister loanRegisterConstructorTest = new LoanRegister();
        assertNotNull(loanRegisterConstructorTest);
        List<Loan> list = loanRegisterConstructorTest.getRegisterList();
        assertTrue(list.isEmpty());
    }

    /*
     * Il metodo "add()" deve garantire che, dato un ingresso "loan2" != null che risulti valido secondo
     * isUnique(), quest'ultimo sia presente nel catalogo al termine dell'operazione.
     * */
    @Test
    void testAdd() {
        assertTrue(loanRegister.isUnique(loan2));
        assertNotNull(loan2);
        loanRegister.add(loan2);
        List<Loan> list = loanRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(loan2));
    }

    /*
     * Il metodo "modify()" deve garantire che, dato un parametro 'old' già presente nel catalogo e un
     * parametro 'newObj' non nullo, gli attributi modificabili (quindi data di restituzione o "returned" se ancora non estinto) di 'old'
     * risultino pari a quelli di 'newObj' al termine dell'operazione. Si noti che il riferimento di 'old' presente
     * nel catalogo al termine dell'operazione deve rimanere invariato (non è una sostituzione, bensì una copia valore per valore
     * degli attributi modificabili).
     * */
    @Test
    void testModify() {
        loanRegister.add(loan1);
        List<Loan> list = loanRegister.getRegisterList();
        assertTrue(list.contains(loan1));
        Student oldStud = loan1.getStudent();
        Book oldBook = loan1.getBook();
        assertNotNull(loan2);
        loan2.returnLoan();
        loanRegister.modify(loan1, loan2);

        assertEquals(1, list.size());

        Loan modifiedLoan = list.get(0);
        assertEquals(loan2.getLoanEnd(), modifiedLoan.getLoanEnd());
        assertEquals(oldStud, modifiedLoan.getStudent());
        assertEquals(oldBook, modifiedLoan.getBook());
        assertSame(modifiedLoan, loan1);
        assertTrue(modifiedLoan.isReturned());
    }

    @Test
    void testModify2() {
        loanRegister.add(loan1);
        List<Loan> list = loanRegister.getRegisterList();
        assertTrue(list.contains(loan1));
        Student oldStud = loan1.getStudent();
        Book oldBook = loan1.getBook();
        assertNotNull(loan2);
        loan2.returnLoan();
        assertTrue(loan2.isReturned());
        assertFalse(loan1.isReturned());
        loanRegister.modify(loan1, loan2);

        assertEquals(1, list.size());

        Loan modifiedLoan = list.get(0);
        assertEquals(loan2.getLoanEnd(), modifiedLoan.getLoanEnd());
        assertEquals(oldStud, modifiedLoan.getStudent());
        assertEquals(oldBook, modifiedLoan.getBook());
        assertSame(modifiedLoan, loan1);
        assertTrue(modifiedLoan.isReturned());
        assertTrue(modifiedLoan.isReturned());
    }

    /*
     * Il metodo remove() deve garantire che, dato un parametro 'toRemove' presente nel catalogo, quest'ultimo
     * non risulti più presente nel catalogo al termine dell'operazione.
     * */
    @Test
    void testRemove1() {
        loanRegister.add(loan1);
        loanRegister.add(loan2);

        List<Loan> list = loanRegister.getRegisterList();
        assertTrue(list.contains(loan1));
        loanRegister.remove(loan1);
        list = loanRegister.getRegisterList();
        assertFalse(list.contains(loan1));
    }
    /*
     * Il metodo isUnique() deve garantire che, dato un ingresso toVerify != null, sia restituito "false" se
     * 'toVerify' è già presente nel catalogo (che in questo caso significa "è già presente un prestito ATTIVO che coinvolge
     * la stessa coppia studente-libro), altrimenti "true".
     * */
    @Test
    void testIsUnique1() {
        assertNotNull(loan3);
        assertNotNull(loan4);

        loanRegister.add(loan3);

        assertTrue(loanRegister.isUnique(loan4));
        assertFalse(loanRegister.isUnique(new Loan(loan3.getStudent(), loan3.getBook(), LocalDate.of(2016, 8, 4))));
    }

    /*
     * Il metodo getRegisterList() deve garantire che la List<Loan> restituita contenga tutti i prestiti aggiunti al catalogo
     * e che questi siano ordinati secondo la data ultima di restituzione.
     * */
    @Test
    void testGetValuesList() {
        loanRegister.add(loan1);
        loanRegister.add(loan2);
        loanRegister.add(loan3);
        loanRegister.add(loan4);
        List<Loan> list = loanRegister.getRegisterList();
        assertEquals(4, list.size());
        assertTrue(list.contains(loan4));
        assertTrue(list.contains(loan3));
        assertTrue(list.contains(loan1));
        assertTrue(list.contains(loan2));

        assertEquals(list.get(0), loan4);
        assertEquals(list.get(1), loan3);
        assertEquals(list.get(2), loan1);
        assertEquals(list.get(3), loan2);
    }
}