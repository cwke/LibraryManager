/**
 * @file StudentRegister.java
 * @brief Implementazione concreta del registro per la gestione degli studenti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @class StudentRegister
 * @brief Classe responsabile della gestione della lista degli studenti.
 * @details Questa classe implementa l'interfaccia generica Register tipizzata sulla classe Student.
 * @see Register
 * @see Student
 */
public class StudentRegister implements Register<Student> {

    /**
     * @brief Lista interna che memorizza gli oggetti Student.
     * @details Utilizza ObservableList per permettere il binding con componenti grafici JavaFX.
     */
    private ObservableList<Student> studentRegister;

    /**
     * @brief Costruttore della classe StudentRegister.
     * @details Inizializza la lista osservabile vuota.
     */
    public StudentRegister() {
        this.studentRegister = FXCollections.observableArrayList();
    }

    /**
     * @brief Aggiunge un nuovo studente al registro.
     * @details Prima di aggiungere, verifica che lo studente sia valido tramite il metodo isValid.
     * @param[in] toAdd L'oggetto Student da aggiungere.
     * @return boolean True se l'aggiunta ha avuto successo, False se l'oggetto non è valido o esiste già.
     */
    @Override
    public boolean add(Student toAdd) {
        if (isValid(toAdd)) {
            return this.studentRegister.add(toAdd);
        }
        return false;
    }

    /**
     * @brief Modifica uno studente esistente nel registro.
     * @details Cerca l'oggetto 'old' e lo sostituisce con 'newObj'.
     * @param[in] old L'oggetto Student originale da sostituire.
     * @param[in] newObj Il nuovo oggetto Student che prenderà il posto del precedente.
     * @return boolean True se la sostituzione è avvenuta, False se l'oggetto 'old' non è stato trovato.
     */
    @Override
    public boolean modify(Student old, Student newObj) {
        if (!isValid(newObj)) {
            return false;
        }

        int index = this.studentRegister.indexOf(old);
        if (index >= 0) {
            this.studentRegister.set(index, newObj);
            return true;
        }
        return false;
    }

    /**
     * @brief Rimuove uno studente dal registro.
     * @param[in] toRemove L'oggetto Student da rimuovere.
     * @return boolean True se la rimozione ha avuto successo, False altrimenti.
     */
    @Override
    public boolean remove(Student toRemove) {
        return this.studentRegister.remove(toRemove);
    }

    /**
     * @brief Verifica la validità di un oggetto Student.
     * @details Controlla che l'oggetto non sia nullo (Scrivere qui gli ulteriori controlli, es. matricola univoca)..
     * @param[in] toVerify L'oggetto Student da verificare.
     * @return boolean True se l'oggetto è valido, False altrimenti.
     */
    @Override
    public boolean isValid(Student toVerify) {
        // Implementazione di base: controlla che non sia null
        return toVerify != null;
    }

    /**
     * @brief Restituisce la lista osservabile degli studenti.
     * @return ObservableList<Student> La lista contenente gli studenti.
     */
    @Override
    public ObservableList<Student> getRegister() {
        return this.studentRegister;
    }

}
