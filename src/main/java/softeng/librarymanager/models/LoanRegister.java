/**
 * @file LoanRegister.java
 * @brief Implementazione del registro dei prestiti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 */

package softeng.librarymanager.models;

import java.util.*;


/**
 * @brief Implementa un catalogo prestiti e le funzionalità per la sua gestione.
 * @see Register
 * @see Loan
 * @invariant loanRegister != null
 */
public class LoanRegister implements Register<Loan> {

    private Set<Loan> loanRegister;

    /**
     * @post Il catalogo prestiti è correttamente inizializzato come un TreeSet vuoto.
     */
    public LoanRegister() {
        this.loanRegister = new TreeSet<>();
    }

    /**
     * @brief Aggiunge un prestito al catalogo.
     * @param[in] toAdd Prestito da aggiungere al catalogo.
     * @pre toAdd != null
     * @pre toAdd deve essere un prestito valido secondo RegisterValidator
     * @see RegisterValidator
     * @post Il prestito specificato è correttamente inserito nel catalogo.
     */
    @Override
    public void add(Loan toAdd) {
        loanRegister.add(toAdd);
    }

    /**
     * @brief Modifica i dati di un prestito esistente nel catalogo.
     * @param[in] old Prestito da modificare.
     * @param[in] newObj Prestito contenente i nuovi dati aggiornati.
     * @pre newObj != null
     * @pre 'old' deve essere già presente nel catalogo.
     * @pre 'newObj' deve essere un prestito valido secondo RegisterValidator
     * @see RegisterValidator
     * @post I dati del prestito specificato sono aggiornati mantenendo invariato il riferimento (all'oggetto originale) presente nel catalogo.
     */
    @Override
    public void modify(Loan old, Loan newObj) {
        /*
         * Recuperiamo il riferimento all'oggetto Loan memorizzato nel set.
         * Iteriamo sugli elementi per trovare quello corrispondente a "old".
         */
        for(Loan loan : loanRegister)
            if(loan.equals(old)){
                loan.setLoanEnd(newObj.getLoanEnd());
                if(newObj.isReturned())
                    loan.returnLoan();
            }
    }

    /**
     * @brief Rimuove un prestito dal catalogo.
     * @param[in] toRemove Prestito da rimuovere dal catalogo.
     * @pre 'toRemove' è attualmente presente nel catalogo.
     * @post Il prestito specificato è rimosso dal catalogo.
     */
    @Override
    public void remove(Loan toRemove) {
        loanRegister.remove(toRemove);
    }

    /**
     * @brief Verifica la validità di un loan.
     * @param[in] toVerify Loan la cui validità va verificata.
     * @return true se il prestito rispetta i criteri di validità, false altrimenti.
     * @pre toVerify non deve essere null.
     * @post Il valore restituito sarà "true" se il loan è valido, "false" altrimenti
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
     * @brief Restituisce la lista di tutti i prestiti presenti nel catalogo.
     * @return Una List contenente tutti i prestiti presenti nel catalogo, ordinati per loanId.
     * @post Viene restituita una lista contenente i prestiti presenti nel catalogo, ordinata per loanId.
     */
    @Override
    public List<Loan> getRegisterList() {
        /*
         * Creiamo una nuova ArrayList passando il Set al costruttore per restituire una lista ordinata.
         */
        List<Loan> list = new ArrayList<>(loanRegister);
        list.sort(Comparator.comparing(Loan::getLoanEnd));
        return list;
    }

}