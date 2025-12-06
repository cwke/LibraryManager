/**
 * @file Book.java
 * @brief Implementa la classe Book.
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * @brief Rappresenta un libro.
 * @details Questa classe modella un'entità libro, contenente dettagli come il
 *          titolo, l'anno di pubblicazione, l'identificatore unico, la
 *          disponibilità e gli autori. Implementa {@link Comparable} per
 *          l'ordinamento e {@link Serializable} per l'IO su file.
 *
 */
public class Book implements Comparable<Book>, Serializable {
    private String title; ///< Il titolo del libro.

    private int publishYear; ///< L'anno di pubblicazione del libro.

    private final String bookCode; ///< Il codice identificativo del libro (ISBN).

    private int availableCopies; ///< Il numero di copie attualmente disponibili.

    private String authors; ///< Gli autori del libro.

    /**
     * @brief Costruisce un nuovo Book.
     * 
     * @param[in] title Il titolo del libro.
     * @param[in] publishYear L'anno di pubblicazione del libro.
     * @param[in] bookCode Il codice identificativo del libro (ISBN).
     * @param[in] availableCopies Il numero di copie attualmente disponibili.
     * @param[in] authors Gli autori del libro.
     */
    public Book(String title, int publishYear, String bookCode, int availableCopies, String authors) {
        this.title = title;
        this.publishYear = publishYear;
        this.bookCode = bookCode;
        this.availableCopies = availableCopies;
        this.authors = authors;
    }

    /**
     * @return Gli autori del libro.
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * @return Il titolo del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return L'anno di pubblicazione del libro.
     */
    public int getPublishYear() {
        return publishYear;
    }

    /**
     * @return Il codice identificativo del libro (ISBN).
     */
    public String getBookCode() {
        return bookCode;
    }

    /**
     * @return Il numero di copie attualmente disponibili.
     */
    public int getAvailableCopies() {
        return availableCopies;
    }

    /**
     * @param[in] title Il nuovo titolo da impostare.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param[in] publishYear Il nuovo anno di pubblicazione da impostare.
     */
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * @param availableCopies Il nuovo numero di copie disponibili da impostare.
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    /**
     * @param[in] obj L'oggetto con cui confrontare.
     * @return True se gli oggetti hanno lo stesso riferimento o lo stesso codice ISBN;
     *         false altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return Objects.equals(this.bookCode, other.bookCode);
    }

    /**
     * @param[in] other L'altro libro con cui confrontare.
     * @return Un numero negativo, zero o positivo se questo oggetto è inferiore,
     *         uguale o superiore all'oggetto specificato.
     */
    @Override
    public int compareTo(Book other) {
        // TODO: da implementare
        return 0;
    }
}
