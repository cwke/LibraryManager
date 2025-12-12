package softeng.librarymanager.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testValidBookCreation() {
        Book book = new Book("Titolo", "Autore", "1234567890123", 2020, 5);

        assertEquals("Titolo", book.getTitle());
        assertEquals("Autore", book.getAuthors());
        assertEquals("1234567890123", book.getBookId());
        assertEquals(2020, book.getPublishmentYear());
        assertEquals(5, book.getAvailableCopies());
    }

    @Test
    void testInvalidBookIdThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("Titolo", "Autore", "123", 2020, 5));
    }

    @Test
    void testNegativeCopiesThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("Titolo", "Autore", "1234567890123", 2020, -1));
    }

    @Test
    void testCopyUpdatesFields() {
        Book book1 = new Book("A", "B", "1234567890123", 2000, 2);
        Book book2 = new Book("C", "D", "9876543210987", 2021, 9);

        book1.copy(book2);

        assertEquals("C", book1.getTitle());
        assertEquals("D", book1.getAuthors());
        assertEquals(2021, book1.getPublishmentYear());
        assertEquals(9, book1.getAvailableCopies());
        assertEquals("1234567890123", book1.getBookId()); // non cambia
    }

    @Test
    void testCompareTo() {
        Book b1 = new Book("T", "A", "1234567890123", 2020, 1);
        Book b2 = new Book("T", "A", "2234567890123", 2020, 1);

        assertTrue(b1.compareTo(b2) < 0);
    }

    @Test
    void testEqualsAndHashCode() {
        Book b1 = new Book("T", "A", "1234567890123", 2020, 1);
        Book b2 = new Book("X", "Y", "1234567890123", 2020, 9);

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testIsAvailableForLoan() {
        Book book = new Book("T", "A", "1234567890123", 2020, 1);
        assertTrue(book.isAvailableForLoan());

        book.setAvailableCopies(0);
        assertFalse(book.isAvailableForLoan());
    }
}
