package softeng.librarymanager.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    
    private final String validTitle = "La roba";
    private final List<String> validAuthors = new ArrayList<>(Arrays.asList("Giovanni Verga"));
    private final String validBookId = "1234567890123";
    private final int validPublishmentYear = 1880;
    private final int validAvailableCopies = 10;
    
    private final List<String> otherAuthors = new ArrayList<>(Arrays.asList("Dante Alighieri"));

    //Test costruttore e di conseguenza dei getter e delle funzioni di validazione
    @Test
    void testBook() {
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
    void testCopy() {
        Book book = new Book(validTitle, validAuthors, validBookId, validPublishmentYear, validAvailableCopies);
        Book bookCopy = new Book("C", otherAuthors, "9876543210987", 2021, 9);

        book.copy(bookCopy);

        assertEquals("C", book.getTitle());
        assertEquals(otherAuthors, book.getAuthors());
        assertEquals(2021, book.getPublishmentYear());
        assertEquals(9, book.getAvailableCopies());
        assertEquals(validBookId, book.getBookId()); // non cambia
    }

    @Test
    void testCompareTo() {
        Book book = new Book(validTitle, validAuthors, validBookId, validPublishmentYear, validAvailableCopies);
        
        //Verifica libro uguale
        Book b1 = new Book("T", otherAuthors, validBookId, 2020, 1);
        assertTrue(book.compareTo(b1) == 0);
        
        //Verifica libro minore
        Book b2 = new Book("T", otherAuthors, "2234567890123", 2020, 1);
        assertTrue(book.compareTo(b2) < 0);
        
        //Verifica libro maggiore
        Book b3 = new Book("T", otherAuthors, "0234567890123", 2020, 1);
        assertTrue(book.compareTo(b3) > 0);
    }

    @Test
    void testEquals() {
        Book book = new Book(validTitle, validAuthors, validBookId, validPublishmentYear, validAvailableCopies);
        
        //Verifica libri uguali
        Book book1 = new Book("", otherAuthors, validBookId, 2020, 9);
        assertEquals(book, book1);
        
        //Verifica libri diversi
        Book book2 = new Book(validTitle, validAuthors, "1234567654321", validPublishmentYear, validAvailableCopies);
        assertFalse(book.equals(book2));
    }

    @Test
    void testHashCode() {
        Book book = new Book(validTitle, validAuthors, validBookId, validPublishmentYear, validAvailableCopies);
        
        //Verifica hashcode libri uguali
        Book book1 = new Book("", otherAuthors, validBookId, 2020, 9);
        assertEquals(book.hashCode(), book1.hashCode());
        
        //Verifica hashcode libri diversi
        Book book2 = new Book(validTitle, validAuthors, "1234567654321", validPublishmentYear, validAvailableCopies);
        assertFalse(book.hashCode() == book2.hashCode());
    }

    @Test
    void testIsAvailableForLoan() {
        //Verifica libro disponibile per il prestito
        Book book = new Book(validTitle, validAuthors, validBookId, validPublishmentYear, validAvailableCopies);
        assertTrue(book.isAvailableForLoan());

        //Verifica libro non disponibile per il prestito
        Book book1 = new Book(validTitle, validAuthors, validBookId, validPublishmentYear, 0);
        assertFalse(book1.isAvailableForLoan());
    }
}
