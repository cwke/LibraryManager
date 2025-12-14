package softeng.librarymanager.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private final String validName = "Mario";
    private final String validSurname = "Rossi";
    private final String validStudentId = "1234567890";
    private final String validEmail = "m.rossi@studenti.unisa.it";
    
    private final List<String> validAuthors = new ArrayList<>(Arrays.asList("Giovanni Verga"));

    //Test costruttore e di conseguenza dei getter e delle funzioni di validazione
    @Test
    void testStudent() {
        //Creazione valida
        Student student = new Student(validName, validSurname, validStudentId, validEmail);

        assertEquals(validName, student.getName());
        assertEquals(validSurname, student.getSurname());
        assertEquals(validStudentId, student.getStudentId());
        assertEquals(validEmail, student.getEmail());
        
        //Creazione invalida perché nome null
        assertThrows(IllegalArgumentException.class, () -> new Student(null, validSurname, validStudentId, validEmail));
        
        //Creazione invalida perché cognome null
        assertThrows(IllegalArgumentException.class, () -> new Student(validName, null, validStudentId, validEmail));
        
        //Creazione invalida perché identificativo studente null
        assertThrows(IllegalArgumentException.class, () -> new Student(validName, validSurname, null, validEmail));
        
        //Creazione invalida perché lunghezza identificativo studente diversa da 10
        assertThrows(IllegalArgumentException.class, () -> new Student(validName, validSurname, "ABC", validEmail));
        
        //Creazione invalida perché email null
        assertThrows(IllegalArgumentException.class, () -> new Student(validName, validSurname, validStudentId, null));
        
        //Creazione invalida perché email non terminante in @studenti.unisa.it
        assertThrows(IllegalArgumentException.class, () -> new Student(validName, validSurname, validStudentId, "a@b"));
    }

    @Test
    void testCopy() {
        Student student = new Student(validName, validSurname, validStudentId, validEmail);
        Student studentCopy = new Student("Luigi", "Verdi", "0987654321", "l.verdi@studenti.unisa.it");

        student.copy(studentCopy);

        assertEquals("Luigi", student.getName());
        assertEquals("Verdi", student.getSurname());
        assertEquals(validStudentId, student.getStudentId()); // non cambia
        assertEquals("l.verdi@studenti.unisa.it", student.getEmail());
    }

    @Test
    void testAddLoanLimit() {
        Student student = new Student(validName, validSurname, validStudentId, validEmail);
        Book book = new Book("La roba", validAuthors, "1234567890123", 1880, 10);
        //Altrimenti vengono aggiunti due volte i loan agli activeLoan
        Student s1 = new Student("Mario", "Rossi", "1234567891", "m.rossi@studenti.unisa.it");

        //Verifica aggiunta
        student.addActiveLoan(new Loan(s1, book, java.time.LocalDate.now().plusDays(7)));
        assertEquals(1, student.getActiveLoans().size());
        
        student.addActiveLoan(new Loan(s1, book, java.time.LocalDate.now().plusDays(7)));
        assertEquals(2, student.getActiveLoans().size());
        
        student.addActiveLoan(new Loan(s1, book, java.time.LocalDate.now().plusDays(7)));
        assertEquals(3, student.getActiveLoans().size());

        //Verifica aggiunta fallita
        assertThrows(IllegalStateException.class, () -> student.addActiveLoan(new Loan(s1, book, java.time.LocalDate.now().plusDays(7))));
    }

    @Test
    void testCompareTo() {
        Student student = new Student(validName, validSurname, validStudentId, validEmail);
        
        //Verifica studente uguale
        Student s1 = new Student("Luigi", "Verdi", validStudentId, "l.verdi@studenti.unisa.it");
        assertTrue(student.compareTo(s1) == 0);
        
        //Verifica studente minore
        Student s2 = new Student("Luigi", "Verdi", "2345678901", "l.verdi@studenti.unisa.it");
        assertTrue(student.compareTo(s2) < 0);
        
        //Verifica studente maggiore
        Student s3 = new Student("Luigi", "Verdi", "0123456789", "l.verdi@studenti.unisa.it");
        assertTrue(student.compareTo(s3) > 0);
    }

    @Test
    void testEquals() {
        Student student = new Student(validName, validSurname, validStudentId, validEmail);
        
        //Verifica studenti uguali
        Student s1 = new Student("", "", validStudentId, "@studenti.unisa.it");
        assertEquals(student, s1);
        
        //Verifica studenti diversi
        Student s2 = new Student(validName, validSurname, "1234554321", validEmail);
        assertFalse(student.equals(s2));
    }

    @Test
    void testHashCode() {
        Student student = new Student(validName, validSurname, validStudentId, validEmail);
        
        //Verifica hashcode studenti uguali
        Student s1 = new Student("", "", validStudentId, "@studenti.unisa.it");
        assertEquals(student.hashCode(), s1.hashCode());
        
        //Verifica haschcode studenti diversi
        Student s2 = new Student(validName, validSurname, "1234554321", validEmail);
        assertFalse(student.hashCode() == s2.hashCode());
    }

    @Test
    void testIsAvailableForLoan() {
        //Verifica studente disponibile per il prestito
        Student student = new Student(validName, validSurname, validStudentId, validEmail);
        assertTrue(student.isAvailableForLoan());
        
        //Verifica studente non disponibile per il prestito
        Book book = new Book("La roba", validAuthors, "1234567890123", 1880, 10);
        Loan l1 = new Loan(student, book, LocalDate.now());
        Loan l2 = new Loan(student, book, LocalDate.now());
        Loan l3 = new Loan(student, book, LocalDate.now());
        l1.activateLoan();
        l2.activateLoan();
        l3.activateLoan();
                
        assertFalse(student.isAvailableForLoan());
    }
    
    
}
