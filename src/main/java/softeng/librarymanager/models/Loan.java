/**
 * @file Loan.java
 * @brief Definizione della classe modello che rappresenta un prestito.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @class Loan
 * @brief Classe modello che rappresenta un prestito.
 * @details Questa classe modella un'entità prestito, associando uno studente
 * a un libro e tracciando la data di scadenza e lo stato di restituzione.
 * Implementa {@link Comparable} per l'ordinamento e {@link Serializable} per la serializzazione.
 * @invariant attributi diversi da null.
 */
public class Loan implements Comparable<Loan>, Serializable{

    /**
     * @brief Identificativo univoco del prestito {readOnly}
     */
    private final UUID loanId;

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
     * @post Se le invarianti sono rispettate viene creato un prestito con i valori specificati.
     * @post Se le invarianti non sono rispettate viene lanciata una IllegalArgumentException.
     */
    public Loan(Student student, Book book, LocalDate loanEnd) {
        this.loanId = UUID.randomUUID();
        this.student = student;
        this.book = book;
        this.loanEnd = loanEnd;
        // this.returned viene inizializzato a false di default da Java
        if(!this.isValid()) throw new IllegalArgumentException("Impossibile creare un prestito con i seguenti valori");
    }

    /**
     * @brief Restituisce l'identificativo del prestito.
     * @return int L'identificativo del prestito.
     */
    public UUID getLoanId() {
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
     * @brief Imposta una nuova data di fine prestito.
     * @param[in] loanEnd La nuova data di scadenza da impostare.
     * @post Viene impostata la nuova data di scadenza.
     */
    public void setLoanEnd(LocalDate loanEnd) {
        this.loanEnd = loanEnd;
    }

    /**
     * @brief Segna il prestito come restituito.
     * @details Imposta lo stato returned a true, rimuove il prestito dai prestiti attivi dello studente e aumenta di uno il numero di copie del relativo libro.
     * @pre Il prestito è stato attivato.
     * @post Il prestito viene rimosso dallo studente e il numero di copie del libro viene incrementato di uno.
     */
    public void returnLoan() {
        this.returned = true;

        student.removeActiveLoan(this);
        book.setAvailableCopies(book.getAvailableCopies()+1);
    }
    
    /**
     * @brief Attiva il prestito.
     * @details Attiva il presito aggiungendo il prestito alla lista dei prestiti attivi dello studente.
     *          e decrementando le copie disponibili del libro.
     * @pre Il prestito deve essere attivabile.
     * @post Il prestito viene aggiunto allo studente e il numero di copie del libro viene decrementato di uno.
     * @post Se la precondizione non è rispettata viene lanciata IllegalStateException.
     */
    public void activateLoan() {
        if (!isActivable()) throw new IllegalStateException("Non è possibile attivare questo prestito");
        student.addActiveLoan(this);
        book.setAvailableCopies(book.getAvailableCopies()-1);
    }
    
    private boolean isActivable() {
        return book.isAvailableForLoan() &&
                student.isAvailableForLoan();
    }

    /**
     * @brief Confronta due prestiti per l'ordinamento.
     * @details Il confronto viene effettuato sull'identificativo del prestito (loanId).
     * @param[in] other Il prestito con cui confrontare l'istanza corrente.
     * @return int Un valore negativo, zero o positivo se questo prestito è
     *             rispettivamente minore, uguale o maggiore di quello passato.
     */
    @Override
    public int compareTo(Loan other) {
        if (other == null) return 1;
        return loanId.toString().compareTo(other.loanId.toString());
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

    private boolean isValid(){
        return loanEnd != null &&
                book != null &&
                student != null;
    }

    /**
     * @brief Verifica l'uguaglianza tra due prestiti.
     * @details Due prestiti sono considerati uguali se condividono lo stesso
     *          identificativo univoco (loanId)
     * @param[in] other L'oggetto con cui confrontare il prestito.
     * @return boolean true se i prestiti sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other == null || getClass() != other.getClass()) return false;

        Loan otherLoan = (Loan) other;

        return loanId.equals(otherLoan.loanId);
    }

    /**
     * @brief Calcola l'hash del prestito.
     * @details L'hash è calcolato utilizzando esclusivamente l'identificativo
     *          del prestito (loanId).
     * @return int Il valore hash del prestito.
     */
    @Override
    public int hashCode() {
        return loanId.hashCode();
    }
    
}
