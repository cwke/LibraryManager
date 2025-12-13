/**
 * @file LoanModifyPopupController.java
 * @brief Controller per la finestra di dialogo di modifica di un prestito.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.loan;

import javafx.fxml.FXML;
import softeng.librarymanager.models.Loan;
import softeng.librarymanager.models.RegisterModifier;
import softeng.librarymanager.models.RegisterValidator;

/**
 * @class LoanModifyPopupController
 * @brief Classe controller per la gestione del popup di modifica prestiti.
 * @details Estende {@link LoanPopupController}. Gestisce il popolamento dei campi
 *          con i dati esistenti e l'aggiornamento del libro nel registro tramite
 *          {@link RegisterModifier}.
 */
public class LoanModifyPopupController extends LoanPopupController {

    private RegisterModifier<Loan> loanRegisterModifier;
    private RegisterValidator<Loan> loanRegisterValidator;
    private Loan loanToModify;

    public LoanModifyPopupController(RegisterModifier<Loan> loanRegisterModifier, RegisterValidator<Loan> loanRegisterValidator, Loan loanToModify) {
        this.loanRegisterModifier = loanRegisterModifier;
        this.loanRegisterValidator = loanRegisterValidator;
        this.loanToModify = loanToModify;
    }

    /**
     * @brief Inizializza il controller.
     * @details Richiama l'inizializzazione della superclasse.
     */
    @FXML
    public void initialize() {
    }

    /**
     * @brief Imposta il prestito da modificare e popola i campi della GUI.
     * @param[in] loanToModify L'istanza del prestito da modificare.
     */
    public void setLoanToModify(Loan loanToModify) {
        this.loanToModify = loanToModify;
    }

    /**
     * @brief Imposta il delegato per la modifica.
     * @param[in] loanRegisterModifier L'istanza che implementa RegisterModifier<Loan>.
     */
    public void setLoanRegisterModifier(RegisterModifier<Loan> loanRegisterModifier) {
        this.loanRegisterModifier = loanRegisterModifier;
    }

    /**
     * @brief Imposta il delegato per la validazione.
     * @param[in] loanRegisterValidator L'istanza che implementa RegisterValidator<Loan>.
     */
    public void setLoanRegisterValidator(RegisterValidator<Loan> loanRegisterValidator) {
        this.loanRegisterValidator = loanRegisterValidator;
    }

    /**
     * @brief Gestisce l'azione di conferma per la modifica.
     * @details Raccoglie i dati modificati, valida l'oggetto e applica le modifiche.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(javafx.event.ActionEvent event) {
        // Implementazione specifica
    }
}