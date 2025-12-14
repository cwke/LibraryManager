/**
 * @file LoanRegister.java
 * @brief Implementazione del registro dei prestiti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.*;


/**
 * @brief Implementa un catalogo prestiti e le funzionalità per la sua gestione.
 * @details Questa classe fornisce metodi per l'aggiunta, la rimozione, la modifica, l'estinzione e la verifica dell'univocità di un prestito.
 * Inoltre, fornisce un metodo per la restituzione del catalogo come lista ordinata.
 * @see Register
 * @see Loan
 * @invariant loanRegister != null
 */
public class LoanRegister implements Register<Loan>, Serializable {

    private Set<Loan> loanRegister; ///< @brief Struttura dati interna per la memorizzazione del catalogo.

    /**
     * @post Il catalogo prestiti è correttamente inizializzato ed è vuoto.
     */
    public LoanRegister() {
        this.loanRegister = new TreeSet<>();
    }

    /**
     * @brief Aggiunge un prestito al catalogo e lo attiva.
     * @param[in] toAdd Prestito da aggiungere al catalogo.
     * @pre 'toAdd' != null
     * @pre 'toAdd' deve essere un prestito valido secondo RegisterValidator
     * @pre 'toAdd' deve essere un prestito attivabile secondo il metodo isActivable() di Loan
     * @see Loan
     * @see RegisterValidator
     * @post Il prestito specificato è presente nel catalogo.
     * @post Il prestito specificato è attivo.
     */
    @Override
    public void add(Loan toAdd) {
        loanRegister.add(toAdd);
        toAdd.activateLoan();
    }

    /**
     * @brief Modifica i dati di un prestito esistente nel catalogo.
     * @details Permette di aggiornare la data di scadenza o di registrare la restituzione di un prestito (se l'attributo "returned" di newObj == true).
     * @param[in] old Prestito da modificare.
     * @param[in] newObj Prestito contenente i nuovi dati aggiornati (nuova data e/o stato di restituzione).
     * @pre newObj != null
     * @pre 'old' deve essere già presente nel catalogo.
     * @post La data di restituzione di 'old' è aggiornata a quella di 'newObj'.
     * @post Se 'newObj' è contrassegnato come estinto, anche 'old' viene contrassegnato come estinto.
     * @post Il riferimento all'oggetto originale presente nel catalogo resta invariato.
     */
    @Override
    public void modify(Loan old, Loan newObj) {
        for(Loan loan : loanRegister)
            if(loan.equals(old)){
                loan.setLoanEnd(newObj.getLoanEnd());
                if(newObj.isReturned())
                    loan.returnLoan();
            }
    }

    /**
     * @brief Rimuove un prestito dal catalogo e lo contrassegna come estinto.
     * @param[in] toRemove Prestito da rimuovere dal catalogo.
     * @pre 'toRemove' è attualmente presente nel catalogo.
     * @post Il prestito specificato è contrassegnato come estinto.
     * @post Il prestito specificato è rimosso dal catalogo.
     */
    @Override
    public void remove(Loan toRemove) {
        toRemove.returnLoan();
        loanRegister.remove(toRemove);
    }

    /**
     * @brief Verifica che non sia già presente nel catalogo un prestito identico.
     * @details Per identico, si intende un prestito che coinvolga la stessa coppia studente-libro e che non sia stato ancora contrassegnato come estinto.
     * @param[in] 'toVerify' prestito la cui assenza va verificata.
     * @return true se non è già presente nel catalogo un prestito attivo e con stessa coppia studente-libro, false altrimenti.
     * @pre 'toVerify' != null.
     */
    @Override
    public boolean isUnique(Loan toVerify) {
        for (Loan loan : loanRegister)
            if(loan.getStudent().equals(toVerify.getStudent()) &&
                    loan.getBook().equals(toVerify.getBook()) &&
                    !loan.isReturned())
                return false;
        return true;
    }

    /**
     * @brief Restituisce una lista ordinata di tutti i prestiti presenti nel catalogo.
     * @details L'ordinamento avviene per data di restituzione.
     * @return Una lista (ordinata per data di restituzione) contenente tutti i prestiti del catalogo.
     */
    @Override
    public List<Loan> getRegisterList() {
        List<Loan> list = new ArrayList<>(loanRegister);
        list.sort(Comparator.comparing(Loan::getLoanEnd));
        return list;
    }

}