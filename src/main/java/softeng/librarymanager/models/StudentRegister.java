/**
 * @file StudentRegister.java
 * @brief Implementa la classe StudentRegister.
 */

package softeng.librarymanager.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import softeng.librarymanager.interfaces.Register;

/**
 * @brief Gestisce un registro di studenti.
 * @details Implementa l'interfaccia {@link Register} per {@link Student}.
 *          Mantiene un elenco osservabile di studenti e fornisce metodi per
 *          aggiungere, rimuovere e validare studenti.
 * @see Register
 * @see Student
 */
public class StudentRegister implements Register<Student> {
    private final ObservableList<Student> students; ///< La lista osservabile dei studenti registrati.

    /**
     * @brief Costruisce un nuovo registro di studenti.
     * @details Inizializza la lista osservabile di studenti.
     */
    public StudentRegister() {
        this.students = FXCollections.observableArrayList();
    }

    /**
     * @brief Aggiunge un studente al registro.
     * 
     * @pre Lo studente deve essere valido.
     * @post Lo studente viene aggiunto al registro.
     * 
     * @param student Lo studente da aggiungere.
     * @see isValid()
     */
    @Override
    public void add(Student student) {
        students.add(student);
    }

    /**
     * @brief Rimuove un studente dal registro.
     * @param student Lo studente da rimuovere.
     */
    @Override
    public void remove(Student student) {
        students.remove(student);
    }

    /**
     * @return La lista osservabile contenente tutti i studenti registrati.
     */
    @Override
    public ObservableList<Student> getObservableList() {
        return this.students;
    }

    /**
     * @brief Controlla se un studente è valido.
     * @details Verifica se lo studente è valido in base ai seguenti criteri:
     *          - lo studente ha una matricola valida
     *          - Eventuali altri criteri.
     * @param student Lo studente da controllare.
     * @return True se lo studente è valido, false altrimenti.
     */
    @Override
    public boolean isValid(Student student) {
        // Todo: da implementare i controlli
        return true;
    }
}
