/**
 * @file BookRegister.java
 * @brief Implementazione del registro dei libri.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.*;


/**
 * @brief Implementa un catalogo libri e le funzionalità per la sua gestione.
 * @details Questa classe fornisce metodi per l'aggiunta, la rimozione, la modifica e la verifica dell'univocità di un libro.
 * Inoltre, fornisce un metodo per la restituzione del catalogo come lista ordinata.
 * @see Register
 * @see Book
 * @invariant bookRegister != null
 */
public class BookRegister implements Register<Book>, Serializable {

    private Set<Book> bookRegister; ///< @brief Struttura dati interna per la memorizzazione del catalogo.

    /**
     * @post Il catalogo libri è correttamente inizializzato ed è vuoto.
     */
    public BookRegister() {
        this.bookRegister = new TreeSet<>();
    }

    /**
     * @brief Aggiunge un nuovo libro al catalogo.
     * @param[in] toAdd Libro da aggiungere al catalogo.
     * @pre toAdd != null
     * @pre toAdd deve essere un libro valido secondo RegisterValidator
     * @see RegisterValidator
     * @post Il libro specificato è presente nel catalogo.
     */
    @Override
    public void add(Book toAdd) {
        bookRegister.add(toAdd);
    }

    /**
     * @brief Modifica i dati di un libro già presente nel catalogo.
     * @details Copia i dati modificabili (titolo, autori, anno, copie) dal nuovo oggetto a quello già presente nel catalogo.
     * @param[in] old Libro da modificare.
     * @param[in] newObj Libro contenente i nuovi dati aggiornati.
     * @pre newObj != null
     * @pre 'old' deve essere già presente nel catalogo.
     * @post I dati del libro specificato sono aggiornati con quelli di 'newObj'
     * @post Il riferimento all'oggetto originale presente nel catalogo resta invariato.
     */
    @Override
    public void modify(Book old, Book newObj) {
        for(Book book : bookRegister)
            if(book.equals(old)){
                book.copy(newObj);
            }
    }

    /**
     * @brief Rimuove un libro dal catalogo.
     * @param[in] toRemove Libro da rimuovere dal catalogo.
     * @pre 'toRemove' è presente nel catalogo.
     * @post 'toRemove' è rimosso dal catalogo.
     */
    @Override
    public void remove(Book toRemove) {
        bookRegister.remove(toRemove);
    }

    /**
     * @brief Verifica l'assenza di un libro nel catalogo.
     * @param[in] toVerify Libro la cui assenza va verificata.
     * @return True se il libro non è già presente nel catalogo, false altrimenti.
     * @pre toVerify != null.
     */
    @Override
    public boolean isUnique(Book toVerify) {
        return !this.bookRegister.contains(toVerify);
    }

    /**
     * @brief Restituisce una lista ordinata di tutti i libri presenti nel catalogo.
     * @details L'ordinamento avviene prima per Titolo e poi per Autori (questi ultimi sono concatenati seguendo lo stesso ordine di inserimento).
     * @return Una lista (ordinata per Titolo e Autori) contenente tutti i libri del catalogo
     */
    @Override
    public List<Book> getRegisterList() {
        List<Book> list = new ArrayList<>(bookRegister);
        list.sort(Comparator.comparing(Book::getTitle).
                thenComparing(book -> String.join(", ", book.getAuthors())));
        return list;
    }
}