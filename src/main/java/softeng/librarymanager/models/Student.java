/**
 * @file Student.java
 * @brief Definizione della classe modello che rappresenta uno studente.
 * @author Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @class Student
 * @brief Classe modello che rappresenta uno studente.
 * @details Questa classe modella un'entità studente, contenente dettagli come
 * il nome, cognome, matricola, email, e numero di prestiti disponibili.
 * Implementa {@link Comparable} per l'ordinamento.
 */
public class Student implements Comparable<Student>, Serializable {

    /**
     * @brief Nome dello studente.
     */
    private String name;

    /**
     * @brief Cognome dello studente.
     */
    private String surname;

    /**
     * @brief Matricola (ID studente) {readOnly}.
     */
    private final String studentId;

    /**
     * @brief Email istituzionale o personale.
     */
    private String email;

    /**
     * @brief Lista dei prestiti attualmente attivi.
     */
    private final List<Loan> activeLoans;

    /**
     * @brief Costruttore parametrizzato.
     * @details Inizializza le proprietà con i valori forniti e istanzia la lista dei prestiti.
     * @param[in] name Nome dello studente.
     * @param[in] surname Cognome dello studente.
     * @param[in] studentId Matricola dello studente.
     * @param[in] email Email dello studente.
     */
    public Student(String name, String surname, String studentId, String email) {
        this.name = name;
        this.surname = surname;
        this.studentId = studentId;
        this.email = email;
        this.activeLoans = new ArrayList<>();
        if(!this.isValid()) throw new IllegalArgumentException("Impossibile creare uno studente con i dati inseriti");
    }

    /**
     * @brief Restituisce il nome dello studente.
     * @return String Il nome.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @brief Restituisce il cognome dello studente.
     * @return String Il cognome.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * @brief Restituisce la matricola dello studente.
     * @return String La matricola.
     */
    public String getStudentId() {
        return this.studentId;
    }

    /**
     * @brief Restituisce l'email dello studente.
     * @return String L'email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @brief Restituisce la lista dei prestiti attivi.
     * @return List<Loan> La lista dei prestiti correnti.
     */
    public List<Loan> getActiveLoans() {
        return this.activeLoans;
    }

    /**
     * @brief Copia i dati anagrafici da uno studente sorgente a uno studente da modificare.
     * @param[in,out] toModify Lo studente i cui campi verranno aggiornati.
     * @param[in] newData Lo studente da cui verranno prelevati i nuovi valori.
     * @details Questo metodo sostituisce i tradizionali setter, permettendo di aggiornare
     *          in un'unica chiamata i campi principali dello studente (nome, cognome, email).
     */
    public void copy(Student newData){
        this.name = newData.getName();
        this.surname = newData.getSurname();
        this.email = newData.getEmail();
    }


    /**
     * @brief Aggiunge un prestito alla lista dei prestiti attivi dello studente.
     * @details Verifica che lo studente non abbia già raggiunto il limite massimo
     * di 3 prestiti.
     * @param[in] loanToAdd L'oggetto Loan da aggiungere.
     * @throws IllegalStateException se lo studente ha già 3 prestiti attivi.
     */
    public void addActiveLoan(Loan loanToAdd) {
        if (!isAvailableForLoan()) {
            throw new IllegalStateException("Lo studente ha raggiunto il limite massimo di prestiti.");
        }
        this.activeLoans.add(loanToAdd);
    }

    /**
     * @brief Rimuove un prestito dalla lista dei prestiti attivi.
     * @details Metodo chiamato quando un libro viene restituito.
     * @param[in] loanToRemove L'oggetto Loan da rimuovere.
     */
    public void removeActiveLoan(Loan loanToRemove) {
        this.activeLoans.remove(loanToRemove);
    }

    /**
     * @brief Confronta due studenti per l'ordinamento.
     * @details L'ordinamento avviene prima per cognome, poi per nome, infine per ID.
     * @param[in] other Lo studente con cui confrontare.
     * @return int Un valore negativo, zero o positivo.
     */
    @Override
    public int compareTo(Student other) {
        if (other == null) return 1;

        return this.studentId.compareTo(other.studentId);
    }

    /**
     * @brief Verifica la validità e completezza dei dati di uno studente.
     * @details Controlla che nome, cognome, ID ed email siano validi secondo le invarianti.
     * @return boolean True in caso di successo, altrimenti False.
     */
    public boolean isValid(){
        return name != null &&
                surname != null &&
                isStudentIdValid(studentId) &&
                isEmailValid(email);
    }

    /**
     * @brief Verifica la correttezza dell'identificativo di uno studente.
     * @details La correttezza viene verificata relativamente alle invarianti (es. non nullo, formato specifico).
     * @param[in] studentId L'identificativo da verificare.
     * @return boolean True in caso di successo, altrimenti False.
     */
    private boolean isStudentIdValid(String studentId){
        // Implementazione esempio: ID non nullo e non vuoto
        return studentId != null && studentId.length() == 10;
    }

    /**
     * @brief Verifica la correttezza dell'email di uno studente.
     * @details Verifica che l'email contenga il carattere '@' e non sia nulla.
     * @param[in] email L'email da verificare.
     * @return boolean True in caso di successo, altrimenti False.
     */
    private boolean isEmailValid(String email){
        return email != null && email.endsWith("@studenti.unisa.it");
    }

    /**
     * @brief Verifica se lo studente può richiedere nuovi prestiti.
     * @details Uno studente è abilitato se ha meno di 3 prestiti attivi.
     * @return boolean True se può richiedere prestiti, altrimenti False.
     */
    public boolean isAvailableForLoan(){
        return this.activeLoans.size() < 3;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other == null || getClass() != other.getClass()) return false;

        Student otherStudent = (Student) other;

        return studentId.equals(otherStudent.getStudentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.studentId);
    }
}