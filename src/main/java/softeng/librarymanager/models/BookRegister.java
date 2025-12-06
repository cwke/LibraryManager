/**
 * @file BookRegister.java
 * @brief Implementa la classe BookRegister.
 */
package softeng.librarymanager.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import softeng.librarymanager.interfaces.Register;

/**
 * @brief Gestisce un registro di libri.
 * @details Implementa l'interfaccia {@link Register} per {@link Book}.
 *          Mantiene un elenco osservabile di libri e fornisce metodi per
 *          aggiungere, rimuovere e validare libri.
 * @see Register
 * @see Book
 */
public class BookRegister implements Register<Book> {
    private final ObservableList<Book> books; ///< La lista osservabile dei libri registrati.

    /**
     * @brief Costruisce un nuovo BookRegister vuoto.
     * @details Inizializza la lista osservabile di libri.
     */
    public BookRegister() {
        this.books = FXCollections.observableArrayList();
    }

    /**
     * @brief Aggiunge un libro al registro.
     * 
     * @pre Il libro deve essere valido. 
     * @post Il libro viene aggiunto al registro.
     * 
     * @param book Il libro da aggiungere.
     * @see isValid()
     */
    @Override
    public void add(Book book) {
        books.add(book);
    }

    /**
     * @brief Rimuove un libro dal registro.
     * @param book Il libro da rimuovere.
     */
    @Override
    public void remove(Book book) {
        books.remove(book);
    }

    /**
     * @return La lista osservabile contenente tutti i libri registrati.
     */
    @Override
    public ObservableList<Book> getObservableList() {
        return this.books;
    }

    /**
     * @brief Controlla se un libro è valido.
     * @details Verifica se il codice del libro ha la lunghezza corretta (13) e se
     *          è unico all'interno del registro (escludendo il libro stesso se si
     *          tratta di una modifica).
     * 
     * @param book Il libro da controllare.
     * @return True se il libro è valido, false altrimenti.
     */
    @Override
    public boolean isValid(Book book) {
        if (book.getBookCode().length() != 13)
            return false;
        for (Book b : books) {
            if (b != book) { // Per la verifica in caso di modifica (non vogliamo confrontare il libro da
                              // modificare con se stesso)
                if (b.getBookCode().equals(book.getBookCode()))
                    return false;
            }

        }
        return true;
    }
}
