/**
 * @file Student.java
 * @brief Definizione della classe modello che rappresenta uno studente.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @class Student
 * @brief Classe modello che rappresenta uno studente.
 * @details Questa classe modella un'entità studente, contenente dettagli come
 *          il nome, cognome, matricola, email, e numero di prestiti disponibili.
 *          Implementa {@link Comparable} per l'ordinamento e {@link Externalizable}
 *          per l'IO su file.
 */
public class Student implements Comparable<Student>, Externalizable{

    /**
     * @brief Proprietà nome.
     */
    private StringProperty name;

    /**
     * @brief Proprietà cognome.
     */
    private StringProperty surname;

    /**
     * @brief Proprietà matricola {readOnly}.
     */
    private StringProperty studentId;

    /**
     * @brief Proprietà email.
     */
    private StringProperty email;

    /**
     * @brief Lista prestiti attivi.
     */
    private ObservableList<Loan> activeLoans;

    /**
     * @brief Costruttore predefinito.
     * @details Inizializza le proprietà con valori di default e crea la lista dei prestiti.
     */
    public Student() {
        this.name = new SimpleStringProperty("");
        this.surname = new SimpleStringProperty("");
        this.studentId = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.activeLoans = FXCollections.observableArrayList();
    }

    /**
     * @brief Restituisce la proprietà del nome per il binding.
     * @return StringProperty Oggetto proprietà del nome.
     */
    public StringProperty nameProperty() {
        return this.name;
    }

    /**
     * @brief Restituisce la proprietà del cognome per il binding.
     * @return StringProperty Oggetto proprietà del cognome.
     */
    public StringProperty surnameProperty() {
        return this.surname;
    }

    /**
     * @brief Restituisce la proprietà dell'ID studente (Matricola).
     * @return StringProperty Oggetto proprietà dell'ID.
     */
    public StringProperty studentIdProperty() {
        return this.studentId;
    }

    /**
     * @brief Restituisce la proprietà dell'email per il binding.
     * @return StringProperty Oggetto proprietà dell'email.
     */
    public StringProperty emailProperty() {
        return this.email;
    }

    /**
     * @brief Restituisce la lista dei prestiti attivi.
     * @return ObservableList<Loan> Lista osservabile dei prestiti correnti.
     */
    public ObservableList<Loan> getActiveLoans() {
        return this.activeLoans;
    }

    /**
     * @brief Aggiunge un prestito alla lista dei prestiti attivi dello studente.
     * @details Verifica che lo studente non abbia già raggiunto il limite massimo
     *          di 3 prestiti (vincolo 0...3 da UML).
     * @param[in] loanToAdd L'oggetto Loan da aggiungere.
     */
    public void addActiveLoan(Loan loanToAdd) {
    }

    /**
     * @brief Rimuove un prestito dalla lista dei prestiti attivi.
     * @details Metodo chiamato quando un libro viene restituito.
     * @param[in] loanToRemove L'oggetto Loan da rimuovere.
     */
    public void removeActiveLoan(Loan loanToRemove) {
    }

    /**
     * @brief Confronta due studenti per l'ordinamento.
     * @details L'ordinamento avviene prima per cognome, poi per nome, infine per ID.
     * @param[in] other Lo studente con cui confrontare.
     * @return int Un valore negativo, zero o positivo.
     */
    @Override
    public int compareTo(Student o) {
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
