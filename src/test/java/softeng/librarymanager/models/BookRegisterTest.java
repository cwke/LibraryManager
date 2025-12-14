package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookRegisterTest {

    private BookRegister bookRegister;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Book book6;
    private Book book7;
    private Book book8;

    @BeforeEach
    void setUp(){
        bookRegister = new BookRegister();
        List<String> authors1 = new ArrayList<>(); authors1.add("Natale Affinita"); authors1.add("Hermann Galluccio");
        List<String> authors2 = new ArrayList<>(); authors2.add("Jakub Cwiertka"); authors2.add("Fabrizio Acerra");
        List<String> authors3 = new ArrayList<>(); authors3.add("Jakub Cwiertka"); authors3.add("Natale Affinita");
        List<String> authors4 = new ArrayList<>(); authors4.add("Hermann Galluccio"); authors4.add("Fabrizio Acerra");
        List<String> authors5 = new ArrayList<>(); authors5.add("Mario Rossi");
        List<String> authors6 = new ArrayList<>(); authors6.add("Luca Verdi"); authors6.add("Giuseppe Bianchi"); authors6.add("Autore Casuale");
        List<String> authors7 = new ArrayList<>(); authors7.add("Andrea Neri");
        List<String> authors8 = new ArrayList<>(); authors8.add("Francesco Verdi"); authors8.add("Fabrizio Acerra");
        book1 = new Book("Libro", authors1, "0000000000000", 1900, 1400);
        book2 = new Book("Romanzo", authors2, "1110011111111", 2020, 410);
        book3 = new Book("Fumetto", authors3, "1111111000111", 2021, 323);
        book4 = new Book("Altro Libro", authors4, "1111111111101", 2000, 33);
        book5 = new Book("Altro Romanzo", authors5, "1101111111111", 1999, 0);
        book6 = new Book("Altro Fumetto", authors6, "3331111111111", 1878, 1);
        book7 = new Book("Libro", authors7, "1111144441111", 2012, 4);
        book8 = new Book("Romanzo", authors8, "1111555555511", 2034, 555);

    }

    /*
    * Il costruttore deve garantire 1) Le invarianti alla fine dell'operazione -> bookRegisteR != null
    * 2) Che il catalogo libri sia vuoto.
    * */
    @Test
    void testBookRegister(){
        BookRegister bookRegisterConstructorTest = new BookRegister();
        assertNotNull(bookRegisterConstructorTest);
        List<Book> list = bookRegisterConstructorTest.getRegisterList();
        assertTrue(list.isEmpty());
    }

    /*
    * Il metodo "add()" deve garantire che, dato un ingresso "book1" != null che risulti valido secondo
    * isUnique(), quest'ultimo sia presente nel catalogo al termine dell'operazione.
    * */
    @Test
    void testAdd() {
        assertTrue(bookRegister.isUnique(book1));
        assertNotNull(book1);
        bookRegister.add(book1);
        List<Book> list = bookRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(book1));
    }

    /*
    * Il metodo "modify()" deve garantire che, dato un parametro 'old' già presente nel catalogo e un
    * parametro 'newObj' non nullo, gli attributi modificabili (quindi titolo, autori, anno, copie) di 'old'
    * risultino pari a quelli di 'newObj' al termine dell'operazione. Si noti che il riferimento di 'old' presente
    * nel catalogo al termine dell'operazione deve rimanere invariato (non è una sostituzione, bensì una copia valore per valore
    * degli attributi modificabili).
    * */
    @Test
    void testModify() {
        bookRegister.add(book2);
        List<Book> list = bookRegister.getRegisterList();
        assertTrue(list.contains(book2));
        String oldBookId = book2.getBookId();
        assertNotNull(book3);
        bookRegister.modify(book2, book3);
        list = bookRegister.getRegisterList();

        assertEquals(1, list.size());

        Book modifiedBook = list.get(0);

        assertEquals(book3.getTitle(), modifiedBook.getTitle());
        assertEquals(oldBookId, modifiedBook.getBookId());
        assertEquals(book3.getAvailableCopies(), modifiedBook.getAvailableCopies());
        assertEquals(book3.getAuthors(), modifiedBook.getAuthors());
        assertEquals(book3.getPublishmentYear(), modifiedBook.getPublishmentYear());
        assertSame(modifiedBook, book2);
    }


    /*
    * Il metodo remove() deve garantire che, dato un parametro 'toRemove' presente nel catalogo, quest'ultimo
    * non risulti più presente nel catalogo al termine dell'operazione.
    * */
    @Test
    void testRemove() {
        bookRegister.add(book2);
        bookRegister.add(book1);

        List<Book> list = bookRegister.getRegisterList();
        assertTrue(list.contains(book2));
        bookRegister.remove(book2);
        list = bookRegister.getRegisterList();
        assertFalse(list.contains(book2));
    }

    /*
    * Il metodo isUnique() deve garantire che, dato un ingresso toVerify != null, sia restituito "false" se
    * 'toVerify' è già presente nel catalogo, altrimenti "true".
    * */
    @Test
    void testIsUnique() {
        assertNotNull(book1);
        assertNotNull(book4);

        bookRegister.add(book1);

        List<String> testAuth = new ArrayList<>();
        testAuth.add("testAutore");
        assertFalse(bookRegister.isUnique(new Book("a", testAuth, book1.getBookId(), 10, 10)));
        assertTrue(bookRegister.isUnique(book4));
    }

    /*
    * Il metodo getRegisterList() deve garantire che la List<Book> restituita contenga tutti i libri aggiunti al catalogo
    * e che questi siano ordinati secondo Titolo e Autori.
    * */
    @Test
    void testGetValuesList() {
        bookRegister.add(book2);
        bookRegister.add(book1);
        bookRegister.add(book5);
        bookRegister.add(book6);
        bookRegister.add(book8);
        bookRegister.add(book7);

        List<Book> list = bookRegister.getRegisterList();
        assertEquals(6, list.size());
        assertTrue(list.contains(book1));
        assertTrue(list.contains(book2));
        assertTrue(list.contains(book5));
        assertTrue(list.contains(book6));
        assertTrue(list.contains(book7));
        assertTrue(list.contains(book8));

        assertEquals(list.get(0), book6);
        assertEquals(list.get(1), book5);
        assertEquals(list.get(2), book7);
        assertEquals(list.get(3), book1);
        assertEquals(list.get(4), book8);
        assertEquals(list.get(5), book2);
    }
}