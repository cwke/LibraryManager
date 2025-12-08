/**
 * @file Loan.java
 * @brief Definizione della classe modello che rappresenta un prestito.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @class Loan
 * @brief Classe modello che rappresenta un prestito.
 * @details Questa classe modella un'entità prestito, associando uno studente
 *          a un libro e tracciando la data di scadenza e lo stato di restituzione.
 *          Implementa {@link Comparable} per l'ordinamento e {@link Externalizable}
 *          per l'IO su file.
 */
public class Loan implements Comparable<Loan>, Externalizable {

    /**
     * @brief Riferimento allo studente che ha effettuato il prestito {readOnly}.
     */
    private Student student;

    /**
     * @brief Riferimento al libro prestato {readOnly}.
     */
    private Book book;

    /**
     * @brief Proprietà data di fine prestito (scadenza).
     */
    private ObjectProperty<LocalDate> loanEnd;

    /**
     * @brief Proprietà stato di restituzione (True se restituito, False altrimenti).
     */
    private BooleanProperty returned;

    /**
     * @brief Costruttore predefinito.
     * @details Inizializza le proprietà con valori di default.
     */
    public Loan() {
        this.student = null;
        this.book = null;
        this.loanEnd = new SimpleObjectProperty<>();
        this.returned = new SimpleBooleanProperty(false);
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
     * @brief Restituisce la proprietà della data di fine prestito per il binding.
     * @return ObjectProperty<LocalDate> Oggetto proprietà della data di scadenza.
     */
    public ObjectProperty<LocalDate> loanEndProperty() {
        return this.loanEnd;
    }

    /**
     * @brief Restituisce la proprietà dello stato di restituzione per il binding.
     * @return BooleanProperty Oggetto proprietà booleana.
     */
    public BooleanProperty returnedProperty() {
        return this.returned;
    }

    /**
     * @brief Confronta due prestiti per l'ordinamento.
     * @details L'ordinamento avviene tipicamente in base alla data di scadenza.
     * @param[in] other Il prestito con cui confrontare.
     * @return int Un valore negativo, zero o positivo.
     */
    @Override
    public int compareTo(Loan other) {
        return 0;
    }

    /**
     * @brief Metodo personalizzato per serializzare l'oggetto.
     * @details Estrae i valori "puri" dalle proprietà JavaFX e li scrive nello stream.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    /**
     * @brief Metodo personalizzato per deserializzare l'oggetto.
     * @details Legge i valori dallo stream e ricostruisce le proprietà JavaFX.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }
}