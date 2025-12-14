/**
 * @file Student.java
 * @brief Definizione della classe modello che rappresenta uno studente.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
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
 * @details Questa classe modella un'entità studente, contenente dettagli quali
 * nome, cognome, matricola, email, e una lista di prestiti attualmente attivi.
 * Implementa {@link Comparable} per l'ordinamento e {@link Serializable} per la serializzazione.
 * @invariant attributi diversi da null.
 * @invariant studentId.length == 10.
 * @invariant email terminante in "@studenti.unisa.it".
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
     * @brief Email istituzionale.
     */
    private String email;

    /**
     * @brief Prestiti attualmente attivi.
     */
    private final List<Loan> activeLoans;

    /**
     * @brief Costruttore parametrizzato.
     * @details Inizializza le proprietà con i valori forniti e istanzia la lista dei prestiti se i valori forniti sono validi.
     * @param[in] name Nome dello studente.
     * @param[in] surname Cognome dello studente.
     * @param[in] studentId Matricola dello studente.
     * @param[in] email Email dello studente.
     * @post Se le invarianti sono rispettate viene creato uno studente con i valori specificati.
     * @post Se le invarianti non sono rispettate viene lanciata una IllegalArgumentException.
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
     * @brief Restituisce la lista dei prestiti attualmente attivi.
     * @return List<Loan> La lista dei prestiti attualmente attivi.
     */
    public List<Loan> getActiveLoans() {
        return this.activeLoans;
    }
    
    /**
     * @brief Copia i dati modificabili da uno studente sorgente.
     * @param[in] newData Lo studente da cui verranno prelevati i nuovi valori.
     * @details Questo metodo sostituisce i tradizionali setter, permettendo di aggiornare
     *          in un'unica chiamata i principali campi modificabili dello studente (nome, cognome, email).
     * @post I campi modificabili dello studente sono aggiornati con quelli dello studente specificato
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
     * @post Il prestito viene aggiunto alla lista dei prestiti attualmente attivi
     * @post Se lo studente ha già 3 prestiti attivi viene lanciata IllegalStateException.
     */
    public void addActiveLoan(Loan loanToAdd) {
        if (!isAvailableForLoan()) {
            throw new IllegalStateException("Lo studente ha raggiunto il limite massimo di prestiti.");
        }
        this.activeLoans.add(loanToAdd);
    }

    /**
     * @brief Rimuove un prestito dalla lista dei prestiti attivi.
     * @param[in] loanToRemove L'oggetto Loan da rimuovere.
     * @post Il prestito viene rimosso dalla lista dei prestiti attualmente attivi
     */
    public void removeActiveLoan(Loan loanToRemove) {
        this.activeLoans.remove(loanToRemove);
    }

    /**
     * @brief Confronta due studenti per l'ordinamento.
     * @details Il confronto viene effettuato sull'identificativo dello studente (studentId).
     * @param[in] other Lo studente con cui confrontare l'istanza corrente.
     * @return int Un valore negativo, zero o positivo se questo studente è
     *             rispettivamente minore, uguale o maggiore di quello passato.
     */
    @Override
    public int compareTo(Student other) {
        if (other == null) return 1;

        return this.studentId.compareTo(other.studentId);
    }

    /**
     * @brief Verifica la validità e completezza dei dati di uno studente.
     * @details Controlla che tutte le invarianti siano rispettate.
     * @return boolean True in caso di successo, altrimenti False.
     */
    public boolean isValid(){
        return name != null &&
                surname != null &&
                isStudentIdValid(studentId) &&
                isEmailValid(email);
    }

    private boolean isStudentIdValid(String studentId){
        // Implementazione esempio: ID non nullo e non vuoto
        return studentId != null && studentId.length() == 10;
    }

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

    /**
     * @brief Verifica l'uguaglianza tra due studenti.
     * @details Due studenti sono considerati uguali se condividono lo stesso
     *          identificativo univoco (studentId)
     * @param[in] other L'oggetto con cui confrontare lo studente.
     * @return boolean true se gli studenti sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other == null || getClass() != other.getClass()) return false;

        Student otherStudent = (Student) other;

        return studentId.equals(otherStudent.getStudentId());
    }

    /**
     * @brief Calcola l'hash dello studente.
     * @details L'hash è calcolato utilizzando esclusivamente l'identificativo
     *          dello studente (studentId).
     * @return int Il valore hash dello studente.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.studentId);
    }
    
}
