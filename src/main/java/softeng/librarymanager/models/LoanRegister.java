/**
 * @file LoanRegister.java
 * @brief Implementa la classe LoanRegister.
 */

package softeng.librarymanager.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import softeng.librarymanager.interfaces.Register;

/**
 * @brief Gestisce un registro di prestiti.
 * @details Implementa l'interfaccia {@link Register} per {@link Loan}.
 *          Mantiene un elenco osservabile di prestiti e fornisce metodi per
 *          aggiungere, rimuovere e validare prestiti.
 * @see Register
 * @see Loan
 */
public class LoanRegister implements Register<Loan> {
    private final ObservableList<Loan> loans; ///< La lista osservabile dei prestiti registrati.

    /**
     * @brief Costruisce un nuovo registro di prestiti.
     * @details Inizializza la lista osservabile di prestiti.
     */
    public LoanRegister() {
        this.loans = FXCollections.observableArrayList();
    }

    /**
     * @brief Rimuove un prestito dal registro.
     * @param item Il prestito da rimuovere.
     */
    @Override
    public void remove(Loan item) {
        loans.remove(item);
    }

    /**
     * @return La lista osservabile contenente tutti i prestiti registrati.
     */
    @Override
    public ObservableList<Loan> getObservableList() {
        return this.loans;
    }

    /**
     * @brief Aggiunge un prestito al registro.
     * 
     * @pre Il prestito deve essere valido. 
     * @post Il prestito viene aggiunto al registro.
     * 
     * @param item Il prestito da aggiungere.
     * @see isValid()
     */
    @Override
    public void add(Loan item) {
        // TODO: gestire un eventuale incremento di availableLoans
        loans.add(item);
    }

    /**
     * @brief Controlla se un prestito è valido.
     * @details Verifica se il prestito è valido in base ai seguenti criteri:
     *          - ci sono copie disponbili del libro
     *          - lo studente ha availableLoans >= 1
     *          - lo studente non ha già in prestito lo stesso libro
     * @param item Il prestito da controllare.
     * @return True se il prestito è valido, false altrimenti.
     */
    @Override
    public boolean isValid(Loan item) {

        // TODO: da implementare
        return true;
    }
}
