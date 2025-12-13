package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookRegisterTest {

    private BookRegister bookRegister;
    private Book newBook;
    private Book oldBook;

    @BeforeEach
    void setUp(){
        bookRegister = new BookRegister();
        newBook = new Book("Libro", "Natale Affinita, Jakub Cwiertka", "0000000000000", 1900, 1400);
        oldBook = new Book("orbiL", "Hermann Galluccio, Fabrizio Acerra", "1111111111111", 1090, 41000);
    }

    @Test
    void testBookRegister(){
        assertNotNull(bookRegister);
        List<Book> list = bookRegister.getRegisterList();
        assertTrue(list.isEmpty());
    }

    @Test
    void testAdd1() {
        bookRegister.add(newBook);
        List<Book> list = bookRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newBook));
    }


    @Test
    void testModify() {
        bookRegister.add(oldBook);
        String oldBookId = oldBook.getBookId();
        bookRegister.modify(oldBook, newBook);
        List<Book> list = bookRegister.getRegisterList();

        assertEquals(1, list.size());

        Book modifiedBook = list.get(0);

        assertEquals(newBook.getTitle(), modifiedBook.getTitle());
        assertEquals(oldBookId, modifiedBook.getBookId());
        assertEquals(newBook.getAvailableCopies(), modifiedBook.getAvailableCopies());
        assertEquals(newBook.getAuthors(), modifiedBook.getAuthors());
        assertEquals(newBook.getPublishmentYear(), modifiedBook.getPublishmentYear());
        assertTrue(modifiedBook == oldBook);
    }

    @Test
    void testRemove1() {
        bookRegister.add(oldBook);
        bookRegister.add(newBook);
        bookRegister.remove(oldBook);
        List<Book> list = bookRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newBook));
    }


    @Test
    void testIsUnique1() {
        bookRegister.add(newBook);
        assertFalse(bookRegister.isUnique(newBook));
    }

    @Test
    void testIsUnique2() {
        assertTrue(bookRegister.isUnique(newBook));
    }

    @Test
    void testGetValuesList() {
        bookRegister.add(newBook);
        bookRegister.add(oldBook);
        List<Book> list = bookRegister.getRegisterList();
        assertEquals(2, list.size());
        assertTrue(list.contains(newBook));
        assertTrue(list.contains(oldBook));
    }
}