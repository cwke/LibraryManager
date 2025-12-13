/**
 * @file Loan.java
 * @brief Definizione della classe modello che rappresenta un prestito.
 * @author Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @class Loan
 * @brief Classe modello che rappresenta un prestito.
 * @details Questa classe modella un'entità prestito, associando uno studente
 * a un libro e tracciando la data di scadenza e lo stato di restituzione.
 * Implementa {@link Comparable} per l'ordinamento.
 */
public class Loan implements Comparable<Loan> {

    private static int count;

    /**
     * @brief Identificativo univoco del prestito {readOnly}
     */
    private int loanId;

    /**
     * @brief Riferimento allo studente che ha effettuato il prestito {readOnly}.
     */
    private final Student student;

    /**
     * @brief Riferimento al libro prestato {readOnly}.
     */
    private final Book book;

    /**
     * @brief Data di fine prestito (scadenza).
     */
    private LocalDate loanEnd;

    /**
     * @brief Stato di restituzione (True se restituito, False altrimenti).
     */
    private boolean returned = false;

    /**
     * @brief Costruttore parametrizzato.
     * @details Inizializza un nuovo prestito con studente, libro e data di scadenza specificati.
     * @param[in] student Lo studente che richiede il prestito.
     * @param[in] book Il libro oggetto del prestito.
     * @param[in] loanEnd La data di scadenza prevista.
     */
    public Loan(Student student, Book book, LocalDate loanEnd) {
        this.loanId = count++;
        this.student = student;
        this.book = book;
        this.loanEnd = loanEnd;
        // this.returned viene inizializzato a false di default da Java
        if(!this.isValid()) throw new IllegalArgumentException("Impossibile creare un prestito con i seguenti valori");
        student.addActiveLoan(this);
        book.setAvailableCopies(book.getAvailableCopies()-1);
    }

    /**
     * @brief Restituisce l'identificativo del prestito.
     * @return int L'identificativo del prestito.
     */
    public int getLoanId() {
        return this.loanId;
    }

    /**
     * @brief Restituisce lo studente associato al prestito.
     * @return Student L'oggetto studente.
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * @brief Restituisce il libro associato al prestito.
     * @return Book L'oggetto libro.
     */
    public Book getBook() {
        return this.book;
    }

    /**
     * @brief Restituisce la data di fine prestito.
     * @return LocalDate La data di scadenza.
     */
    public LocalDate getLoanEnd() {
        return this.loanEnd;
    }

    /**
     * @brief Restituisce lo stato di restituzione del prestito.
     * @return boolean True se il libro è stato restituito, altrimenti False.
     */
    public boolean isReturned() {
        return this.returned;
    }

    /**
     * @brief Imposta una nuova data di fine prestito (proroga).
     * @param[in] loanEnd La nuova data di scadenza da impostare.
     */
    public void setLoanEnd(LocalDate loanEnd) {
        this.loanEnd = loanEnd;
    }

    /**
     * @brief Segna il prestito come restituito.
     * @details Imposta lo stato returned a true.
     */
    public void returnLoan() {
        this.returned = true;

        student.removeActiveLoan(this);
        book.setAvailableCopies(book.getAvailableCopies()+1);
    }

    /**
     * @brief Confronta due prestiti per l'ordinamento.
     * @details L'ordinamento avviene tipicamente in base alla data di scadenza.
     * @param[in] other Il prestito con cui confrontare l'istanza corrente.
     * @return int Un valore negativo, zero o positivo se questo prestito è rispettivamente minore, uguale o maggiore dell'altro.
     */
    @Override
    public int compareTo(Loan other) {
        if (other == null) return 1;
        return Integer.compare(this.loanId, other.getLoanId());
    }

    /**
     * @brief Indica se il prestito è in ritardo.
     * @details Ritorna true solo se la data odierna è successiva alla data di scadenza (loanEnd).
     *          Se oggi è il giorno della scadenza, ritorna comunque false.
     * @return boolean true se il prestito è in ritardo, false altrimenti.
     */
    public boolean isDelay() {
        LocalDate today = LocalDate.now();
        return today.isAfter(loanEnd);
    }

    /**
     * @brief Verifica la validità e la coerenza dei dati per un nuovo prestito.
     * @details Controlla che le invarianti (studente valido, libro valido, data futura) siano rispettate.
     * @param[in] student Lo studente da verificare.
     * @param[in] book Il libro da verificare.
     * @param[in] loanEnd La data di scadenza da verificare.
     * @return boolean True in caso di successo (dati validi), altrimenti False.
     */
    private boolean isValid(){
        return loanEnd != null &&
                isBookValid(book) &&
                isStudentValid(student);

    }

    /**
     * @brief Verifica la validità dei dati di uno studente.
     * @details Controlla che lo studente rispetti le regole di business (es. ID valido).
     * @param[in] student Lo studente da verificare.
     * @return boolean True in caso di successo, altrimenti False.
     */
    private boolean isStudentValid(Student student){
        return student != null && student.isAvailableForLoan();
    }

    /**
     * @brief Verifica la validità dei dati di un libro.
     * @details Controlla che il libro sia in uno stato valido per il prestito.
     * @param[in] book Il libro da verificare.
     * @return boolean True in caso di successo, altrimenti False.
     */
    private boolean isBookValid(Book book){
        return book != null && book.isAvailableForLoan();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other == null || getClass() != other.getClass()) return false;

        Loan otherLoan = (Loan) other;

        return this.loanId == otherLoan.getLoanId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId);
    }

}