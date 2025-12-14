/**
 * @file StudentRegister.java
 * @brief Implementazione del registro degli studenti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.*;

/**
 * @brief Implementa un catalogo studenti e le funzionalità per la sua gestione.
 * @details  Questa classe fornisce metodi per l'aggiunta, la rimozione, la modifica e la verifica dell'univocità di uno studente.
 * Inoltre, fornisce un metodo per la restituzione del catalogo come lista ordinata.
 * @see Register
 * @see Student
 * @invariant studentRegister != null
 */
public class StudentRegister implements Register<Student>, Serializable {

    private Set<Student> studentRegister;///< @brief Struttura dati interna per la memorizzazione del catalogo.

    /**
     * @post Il catalogo studenti è correttamente inizializzato ed è vuoto.
     */
    public StudentRegister() {
        this.studentRegister = new TreeSet<>();
    }

    /**
     * @brief Aggiunge uno studente al catalogo.
     * @param[in] toAdd Studente da aggiungere al catalogo.
     * @pre toAdd != null
     * @pre toAdd deve essere uno studente valido secondo RegisterValidator
     * @see RegisterValidator
     * @post Lo studente specificato è presente nel catalogo.
     */
    @Override
    public void add(Student toAdd) {
        studentRegister.add(toAdd);
    }

    /**
     * @brief Modifica i dati di uno studente già presente nel catalogo.
     * @details Copia i dati modificabili (nome, cognome, mail) dal nuovo oggetto a quello già presente
     * @param[in] old Studente da modificare.
     * @param[in] newObj Studente contenente i nuovi dati aggiornati.
     * @pre newObj != null
     * @pre 'old' deve essere già presente nel catalogo.
     * @post I dati dello studente specificato sono aggiornati con quelli di 'newObj'
     * @post Il riferimento all'oggetto originale presente nel catalogo resta invariato.
     */
    @Override
    public void modify(Student old, Student newObj) {
        for(Student student : studentRegister)
            if(student.equals(old)){
                student.copy(newObj);
            }
    }

    /**
     * @brief Rimuove uno studente dal catalogo.
     * @param[in] toRemove Studente da rimuovere dal catalogo.
     * @pre 'toRemove' è attualmente presente nel catalogo.
     * @post 'toRemove' è rimosso dal catalogo.
     */
    @Override
    public void remove(Student toRemove) {
        studentRegister.remove(toRemove);
    }

    /**
     * @brief Verifica l'assenza di uno studente nel catalogo.
     * @param[in] toVerify studente la cui assenza va verificata.
     * @return True se lo studente non è già presente nel catalogo, false altrimenti.
     * @pre toVerify != null
     */
    @Override
    public boolean isUnique(Student toVerify) {
        return !this.studentRegister.contains(toVerify);
    }

    /**
     * @brief Restituisce una lista ordinata di tutti gli studenti presenti nel catalogo.
     * @details L'ordinamento avviene prima per Cognome, poi per Nome, infine per Matricola.
     * @return Una lista (ordinata per Cognome, Nome e Matricola) contenente tutti gli studenti del catalogo
     */
    @Override
    public List<Student> getRegisterList() {
        List<Student> list = new ArrayList<>(studentRegister);
        list.sort(Comparator.comparing(Student::getSurname).
                thenComparing(Student::getName).
                thenComparing(Student::getStudentId));
        return list;
    }

}