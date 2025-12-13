/**
 * @file BookRegister.java
 * @brief Implementazione del registro dei libri.
 * @author Acerra Fabrizio
 * @date Dicembre 2025
 */
package softeng.librarymanager.models;

import java.util.*;


/**
 * @brief Implementa un catalogo libri e le funzionalità per la sua gestione.
 * @see Register
 * @see Book
 * @invariant bookRegister != null
 */
public class BookRegister implements Register<Book> {

    private Set<Book> bookRegister;

    /**
     * @post Il catalogo libri è correttamente inizializzato come un TreeSet vuoto.
     */
    public BookRegister() {
        this.bookRegister = new TreeSet<>();
    }

    /**
     * @brief Aggiunge un libro al catalogo.
     * @param[in] toAdd Libro da aggiungere al catalogo.
     * @pre toAdd != null
     * @pre toAdd deve essere un libro valido secondo RegisterValidator
     * @see RegisterValidator
     * @post Il libro specificato è correttamente inserito nel catalogo.
     */
    @Override
    public void add(Book toAdd) {
        bookRegister.add(toAdd);
    }

    /**
     * @brief Modifica i dati di un libro esistente nel catalogo.
     * @param[in] old Libro da modificare.
     * @param[in] newObj Libro contenente i nuovi dati aggiornati.
     * @pre newObj != null
     * @pre 'old' deve essere già presente nel catalogo.
     * @see RegisterValidator
     * @post I dati del libro specificato sono aggiornati mantenendo invariato il riferimento (all'oggetto originale) presente nel catalogo.
     */
    @Override
    public void modify(Book old, Book newObj) {
        /*
         * Recuperiamo il riferimento all'oggetto Book memorizzato nel set.
         * Poiché i Loan mantengono un riferimento diretto all'istanza
         * del libro, non possiamo sostituire l'oggetto con una nuova istanza (per necessità di sincronizzazione).
         * Procediamo quindi aggiornando i singoli campi dell'istanza esistente con i valori di 'newObj'.
         */
        for(Book book : bookRegister)
            if(book.equals(old)){
                book.copy(newObj);
            }
    }

    /**
     * @brief Rimuove un libro dal catalogo.
     * @param[in] toRemove Libro da rimuovere dal catalogo.
     * @pre 'toRemove' è attualmente presente nel catalogo.
     * @post Il libro specificato è rimosso dal catalogo.
     */
    @Override
    public void remove(Book toRemove) {
        bookRegister.remove(toRemove);
    }

    /**
     * @brief Verifica la validità di un libro.
     * @param[in] toVerify Libro la cui validità va verificata.
     * @return true se il libro rispetta i criteri di validità, false altrimenti.
     * @pre toVerify e i suoi attributi non devono essere null.
     * @post Il valore restituito sarà "true" se il libro è valido, "false" altrimenti
     */
    @Override
    public boolean isUnique(Book toVerify) {
        return !this.bookRegister.contains(toVerify);
    }

    /**
     * @brief Restituisce la lista di tutti i libri presenti nel catalogo.
     * @return Una List contenente tutti i libri presenti nel catalogo
     * @post Viene restituita una lista contenente i libri presenti nel catalogo
     */
    @Override
    public List<Book> getRegisterList() {
        /*
         * Creiamo una nuova ArrayList passando il Set al costruttore per restituire una lista ordinata.
         * Nota: avendo aggiornato "authors" da String a List<String>, non posso più utilizzare l'ordinamento naturale
         * delle String. Di conseguenza, utilizzo il metodo String.join per concatenare i vari autori in un'unica Stringa.
         * (Si noti che l'ordinamento rispetto agli autori, di conseguenza, da la priorità in base all'ordine di inserimento )
         */
        List<Book> list = new ArrayList<>(bookRegister);
        list.sort(Comparator.comparing(Book::getTitle).thenComparing(book -> String.join(", ", book.getAuthors())));
        return list;
    }
}