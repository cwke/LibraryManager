/**
 * @file StudentRegister.java
 * @brief Implementazione del registro degli studenti.
 * @author Acerra Fabrizio
 * @date Dicembre 2025
 */

package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.*;

/**
 * @brief Implementa un catalogo studenti e le funzionalità per la sua gestione.
 * @see Register
 * @see Student
 * @invariant studentRegister != null
 */
public class StudentRegister implements Register<Student>, Serializable {

    private Set<Student> studentRegister;

    /**
     * @post Il catalogo studenti è correttamente inizializzato come un TreeSet vuoto.
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
     * @post Lo studente specificato è correttamente inserito nel catalogo.
     */
    @Override
    public void add(Student toAdd) {
        studentRegister.add(toAdd);
    }

    /**
     * @brief Modifica i dati di uno studente esistente nel catalogo.
     * @param[in] old Studente da modificare.
     * @param[in] newObj Studente contenente i nuovi dati aggiornati.
     * @pre newObj != null
     * @pre 'old' deve essere già presente nel catalogo.
     * @pre 'newObj' deve essere uno Studente valido secondo RegisterValidator
     * @see RegisterValidator
     * @post I dati dello studente specificato sono aggiornati mantenendo invariato il riferimento (all'oggetto originale) presente nel catalogo.
     */
    @Override
    public void modify(Student old, Student newObj) {
        /*
         * Recuperiamo il riferimento all'oggetto Student memorizzato nel set.
         * Poiché i Loan (prestiti) potrebbero mantenere un riferimento diretto all'istanza
         * dello studente, non possiamo sostituire l'oggetto con una nuova istanza (per necessità di sincronizzazione).
         * Procediamo quindi aggiornando i singoli campi dell'istanza esistente con i valori di 'newObj'.
         * Si noti che, essendo lo studentId non modificabile, non è possibile corrompere l'ordinamento del TreeSet.
         */
        for(Student student : studentRegister)
            if(student.equals(old)){
                student.copy(newObj);
            }
    }

    /**
     * @brief Rimuove uno studente dal catalogo.
     * @param[in] toRemove Studente da rimuovere dal catalogo.
     * @pre 'toRemove' è attualmente presente nel catalogo.
     * @post Lo studente specificato è rimosso dal catalogo.
     */
    @Override
    public void remove(Student toRemove) {
        studentRegister.remove(toRemove);
    }

    /**
     * @brief Verifica la validità di uno studente.
     * @param[in] toVerify Studente la cui validità va verificata.
     * @return true se lo studente rispetta i criteri di validità, false altrimenti.
     * @pre toVerify e i suoi attributi devono essere !null.
     * @post Il valore restituito sarà "true" se lo studente è valido, "false" altrimenti
     */
    @Override
    public boolean isUnique(Student toVerify) {
        return !this.studentRegister.contains(toVerify);
    }

    /**
     * @brief Restituisce la lista di tutti gli studenti presenti nel catalogo.
     * @return Una List contenente tutti gli studenti presenti nel catalogo
     * @post Viene restituita una lista contenente gli studenti presenti nel catalogo
     */
    @Override
    public List<Student> getRegisterList() {
        /*
         * Creiamo una nuova ArrayList passando il Set al costruttore per restituire una lista ordinata.
         */
        List<Student> list = new ArrayList<>(studentRegister);
        list.sort(Comparator.comparing(Student::getName).thenComparing(Student::getSurname));
        return list;
    }

}