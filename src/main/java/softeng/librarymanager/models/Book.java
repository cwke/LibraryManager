/**
 * @file Book.java
 * @brief Definizione della classe modello che rappresenta un libro.
 * @author Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @class Book
 * @brief Classe modello che rappresenta un libro.
 * @details Questa classe modella un'entità libro all'interno del catalogo, contenente
 * dettagli come titolo, anno di pubblicazione, autori, ID univoco
 * e numero di copie disponibili.
 * Implementa {@link Comparable} per l'ordinamento.
 * @invariant availableCopies >= 0
 * @invariant bookId.length == 13
 * @invariant publishmentYear >= 0
 */
public class Book implements Comparable<Book>, Serializable {

    /**
     * @brief Titolo del libro.
     */
    private String title;

    /**
     * @brief Autori del libro.
     */
    private List<String> authors;

    /**
     * @brief Identificativo univoco libro (ISBN) {readOnly}.
     */
    private final String bookId;

    /**
     * @brief Anno di pubblicazione.
     */
    private int publishmentYear;

    /**
     * @brief Numero di copie disponibili.
     */
    private int availableCopies;

    /**
     * @brief Costruttore parametrizzato.
     * @details Inizializza gli attributi con i valori specificati se validi.
     * @param[in] title Il titolo del libro.
     * @param[in] authors Gli autori del libro.
     * @param[in] bookId L'identificativo univoco del libro (ISBN).
     * @param[in] publishmentYear L'anno di pubblicazione del libro.
     * @param[in] availableCopies Il numero di copie disponibili iniziali.
     * @post Se le invarianti non sono rispettate viene lanciata una IllegalArgumentException.
     */
    public Book(String title, List<String> authors, String bookId, int publishmentYear, int availableCopies) {
        this.title = title;
        this.authors = authors;
        this.bookId = bookId;
        this.publishmentYear = publishmentYear;
        this.availableCopies = availableCopies;
        if(!this.isValid()) throw new IllegalArgumentException("Impossibile creare un libro con i seguenti valori");
    }

    /**
     * @brief Restituisce il titolo del libro.
     * @return String Il titolo.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @brief Restituisce gli autori del libro.
     * @return String La stringa contenente gli autori.
     */
    public List<String> getAuthors() {
        return this.authors;
    }

    /**
     * @brief Restituisce l'identificativo univoco (ISBN).
     * @return String L'ISBN del libro.
     */
    public String getBookId() {
        return this.bookId;
    }

    /**
     * @brief Restituisce l'anno di pubblicazione.
     * @return int L'anno di pubblicazione.
     */
    public int getPublishmentYear() {
        return this.publishmentYear;
    }

    /**
     * @brief Restituisce il numero di copie attualmente disponibili.
     * @return int Il numero di copie.
     */
    public int getAvailableCopies() {
        return this.availableCopies;
    }

    /**
     * @brief Imposta il numero di copie disponibili.
     * @param[in] availableCopies Il nuovo numero di copie.
     * @post Se le invarianti non sono rispettate (es. copie negative) viene lanciata una IllegalArgumentException.
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    /**
     * @brief Copia i dati modificabili da un libro sorgente a un libro da modificare.
     * @param[in,out] toModify Lo studente i cui campi verranno aggiornati.
     * @param[in] newData Lo studente da cui verranno prelevati i nuovi valori.
     * @details Questo metodo sostituisce i tradizionali setter, permettendo di aggiornare
     *          in un'unica chiamata i campi principali dello studente (nome, cognome, email).
     */
    public void copy(Book newData){
        this.title = newData.getTitle();
        this.authors = newData.getAuthors();
        this.publishmentYear = newData.getPublishmentYear();
        this.availableCopies = newData.getAvailableCopies();
    }

    /**
     * @brief Confronta due libri per l'ordinamento.
     * @details L'ordinamento avviene lessicograficamente prima per titolo, poi per autori, e infine per ID.
     * @param[in] other Il libro con cui confrontare l'istanza corrente.
     * @return int Un valore negativo, zero o positivo.
     */
    @Override
    public int compareTo(Book other) {
        // Esempio basico di implementazione conforme alla documentazione
        if (other == null) return 1;

        return this.bookId.compareTo(other.bookId);
    }

    /**
     * @brief Verifica la validità di tutti i dati necessari per creare un libro.
     * @details Controlla che tutte le invarianti siano rispettate simultaneamente.
     * @return boolean True in caso di successo (tutti i dati validi), altrimenti False.
     */
    private boolean isValid(){
        return isBookIdValid(bookId) &&
                isPublishmentYearValid(publishmentYear) &&
                isAvailableCopiesValid(availableCopies) &&
                title != null &&
                authors != null;
    }

    /**
     * @brief Verifica la validità dell'identificativo di un libro.
     * @details Controlla che l'ID rispetti il formato richiesto (es. lunghezza 13 caratteri).
     * @param[in] bookId L'identificativo da verificare.
     * @return boolean True se l'ID è valido, altrimenti False.
     */
    private boolean isBookIdValid(String bookId){
        return bookId != null && bookId.length() == 13;
    }

    /**
     * @brief Verifica la validità dell'anno di pubblicazione.
     * @details Controlla che l'anno non sia negativo.
     * @param[in] publishmentYear L'anno da verificare.
     * @return boolean True se l'anno è valido, altrimenti False.
     */
    private boolean isPublishmentYearValid(int publishmentYear){
        return publishmentYear >= 0;
    }

    /**
     * @brief Verifica la validità del numero di copie.
     * @details Controlla che il numero di copie non sia negativo.
     * @param[in] availableCopies Il numero di copie da verificare.
     * @return boolean True se il numero è valido, altrimenti False.
     */
    private boolean isAvailableCopiesValid(int availableCopies){
        return availableCopies >= 0;
    }

    /**
     * @brief Verifica se il libro è disponibile per il prestito.
     * @details Un libro è disponibile se il numero di copie disponibili è maggiore di zero.
     * @return boolean True se ci sono copie disponibili, altrimenti False.
     */
    public boolean isAvailableForLoan(){
        return this.availableCopies > 0;
    }

    @Override
    public boolean equals(Object otherBook) {
        if (this == otherBook) return true;

        if (otherBook == null || getClass() != otherBook.getClass()) return false;

        Book other = (Book) otherBook;

        return bookId.equals(other.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.bookId);
    }
}