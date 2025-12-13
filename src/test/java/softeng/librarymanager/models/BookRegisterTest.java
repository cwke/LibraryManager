package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookRegisterTest {

    private BookRegister bookRegister;
    private Book newBook;
    private Book oldBook;

    /*
    * Inizializzo due libri che utilizzerò per verificare le diverse
    * operazioni messe a disposizione dal BookRegister.
    * */
    @BeforeEach
    void setUp(){
        bookRegister = new BookRegister();
        List<String> newAuthors = new ArrayList<>(); newAuthors.add("Natale Affinita"); newAuthors.add("Hermann Galluccio");
        List<String> oldAuthors = new ArrayList<>(); oldAuthors.add("Natale Affinita"); oldAuthors.add("Fabrizio Acerra");
        newBook = new Book("Libro", newAuthors, "0000000000000", 1900, 1400);
        oldBook = new Book("orbiL", oldAuthors, "1111111111111", 1090, 41000);
    }

    /*
    * Il costruttore deve garantire 1) Le invarianti alla fine dell'operazione -> bookRegisteR != null
    * 2) Che il catalogo libri sia vuoto.
    * */
    @Test
    void testBookRegister(){
        assertNotNull(bookRegister);
        List<Book> list = bookRegister.getRegisterList();
        assertTrue(list.isEmpty());
    }

    /*
    * Dato un ingresso != null valido secondo RegisterValidator (dunque unico, non ancora inserito
    * nel catalogo) il metodo add() deve garantire che, al termine dell'operazione, il libro sia stato
    * effettivamente inserito nel catalogo. Si noti che utilizzo il metodo getRegisterList() per quest'operazione e le seguenti
    * poiché il relativo caso di test mi garantisce la corrispondenza tra la List restituita da suddetto metodo ed il catalogo studenti
    * (salvo ulteriori operazioni sul catalogo successivamente l'invocazione del metodo).
    * */
    @Test
    void testAdd() {
        bookRegister.add(newBook); //non avendo ancora inserito alcun libro, newBook rispetta certamente la condizione di validità di RegisterValidator
        List<Book> list = bookRegister.getRegisterList();
        assertEquals(1, list.size());//questa assert e la successiva mi garantiscono (insieme) il corretto inserimento
        assertTrue(list.contains(newBook));  //del libro nel catalogo.
    }

    /*
    * Dato un ingresso "newBook" != null ed un libro "oldBook" già presente nel catalogo, modify() deve
    * garantire che al termine dell'operazione il riferimento al libro "oldBook" già presente resti invariato e che
    * i suoi dati (non modificabili, quindi tutti tranne il bookId) risultino pari a quelli di newBook.
    * Anche in questo metodo utilizzo il metodo getRegisterList() per i motivi citati nel commento del metodo di test precedente.
    * */
    @Test
    void testModify() {
        bookRegister.add(oldBook);
        String oldBookId = oldBook.getBookId();
        bookRegister.modify(oldBook, newBook);
        List<Book> list = bookRegister.getRegisterList();

        assertEquals(1, list.size());

        Book modifiedBook = list.get(0);

        assertEquals(newBook.getTitle(), modifiedBook.getTitle());
        assertEquals(oldBookId, modifiedBook.getBookId()); //l'id deve rimanere invariato
        assertEquals(newBook.getAvailableCopies(), modifiedBook.getAvailableCopies());
        assertEquals(newBook.getAuthors(), modifiedBook.getAuthors());
        assertEquals(newBook.getPublishmentYear(), modifiedBook.getPublishmentYear());
        assertTrue(modifiedBook == oldBook); //verifica sul riferimento invariato
    }

    /*
    * Dato un ingresso "toRemove" (in questo caso oldBook) già presente nel catalogo, il metodo
    * remove() deve garantire che al termine dell'operazione il libro non sia più presente nel catalogo.
    * */
    @Test
    void testRemove1() {
        bookRegister.add(oldBook);
        bookRegister.add(newBook);
        bookRegister.remove(oldBook);
        List<Book> list = bookRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newBook));
    }

    /*
    * Dato un ingresso !null con attributi !null (garantito dal costrutture di Book)
    * questo metodo deve restituire "false" se un libro identico (quindi con stesso bookId) è
    * già presente nel catalogo, true altrimenti.
    * */
    @Test
    void testIsUnique1() {
        bookRegister.add(newBook);
        List<String> testAuth = new ArrayList<>();
        testAuth.add("testAutore");
        assertFalse(bookRegister.isUnique(new Book("a", testAuth, newBook.getBookId(), 10, 10)));
    }

    @Test
    void testIsUnique2() {
        assertTrue(bookRegister.isUnique(newBook));
    }

    /*
    * Questo metodo deve garantire che la List restituita contenga tutti gli elementi presenti
    * nel catalogo, ordinati prima per titolo e poi per autori.
    * */
    @Test
    void testGetValuesList() {
        bookRegister.add(newBook);
        bookRegister.add(oldBook);
        List<Book> list = bookRegister.getRegisterList();
        assertEquals(2, list.size());
        assertTrue(list.contains(newBook));
        assertTrue(list.contains(oldBook));

        /*
        * Se il metodo funziona correttamente, per il rispetto dell'ordinamento alfabetico l'ordine
        * di oldBook e newBook nella List dev'essere invertito.
        * */
        assertEquals(list.get(0), oldBook);
        assertEquals(list.get(1), newBook);
    }
}