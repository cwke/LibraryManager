/**
 * @file Loan.java
 * @brief Implementa la classe Loan.
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @brief Rappresenta un prestito.
 * @details Questa classe modella un'entità prestito, contenente dettagli come
 *          lo studente, il libro, la data di inizio e fine prestito e lo stato
 *          di restituzione.
 *          Implementa {@link Comparable} per l'ordinamento e
 *          {@link Serializable} per l'IO su file.
 */
public class Loan implements Comparable<Loan>, Serializable {
    private final Student student; ///< Lo studente del prestito.

    private final Book book; ///< Il libro in prestito.

    private final LocalDate loanStart; ///< La data di inizio prestito.

    private LocalDate loanEnd; ///< La data di fine prestito.

    private boolean returned; ///< Indica se il libro è stato restituito. True se restituito, false altrimenti.

    /**
     * @brief Costruisce un nuovo prestito, specificando la data di fine prestito.
     * 
     * @param[in] student Lo studente del prestito.
     * @param[in] book    Il libro in prestito.
     * @param[in] loanEnd La data di fine prestito.
     */
    public Loan(Student student, Book book, LocalDate loanEnd) {
        this.student = student;
        this.book = book;
        this.loanEnd = loanEnd;
        this.loanStart = LocalDate.now();
    }

    /**
     * @brief Costruisce un nuovo prestito, la data di fine prestito viene impostata
     *        a un mese da oggi.
     * 
     * @param[in] student Lo studente del prestito.
     * @param[in] book    Il libro in prestito.
     */
    public Loan(Student student, Book book) {
        this(student, book, LocalDate.now().plusMonths(1));
    }

    /**
     * @return Lo studente del prestito.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @return Il libro in prestito.
     */
    public Book getBook() {
        return book;
    }

    /**
     * @return La data di inizio prestito.
     */
    public LocalDate getLoanStart() {
        return loanStart;
    }

    /**
     * @return La data di fine prestito.
     */
    public LocalDate getLoanEnd() {
        return loanEnd;
    }

    /**
     * @return True se il libro è stato restituito, false altrimenti.
     */
    public boolean isReturned() {
        return returned;
    }

    /**
     * @param loanEnd La nuova data di fine prestito.
     */
    public void setLoanEnd(LocalDate loanEnd) {
        this.loanEnd = loanEnd;
    }

    /**
     * @param returned True se il libro è stato restituito, false altrimenti.
     */
    public void setReturned(boolean returned) {
        this.returned = returned;
        // TODO: da implementare
        // Se il libro è stato restituito incrementare availableLoans di studente
        // Altrimenti decrementare
    }

    @Override
    public int compareTo(Loan o) {
        // TODO: da implementare
        return 0;
    }

}
