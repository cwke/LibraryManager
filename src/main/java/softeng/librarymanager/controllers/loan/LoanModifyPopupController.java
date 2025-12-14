/**
 * @file LoanModifyPopupController.java
 * @brief Controller per la finestra di dialogo di modifica di un prestito.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.loan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
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

    private final RegisterModifier<Loan> loanRegisterModifier;
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
        studentListView.setVisible(false);
        studentListView.setManaged(false);
        studentSearchTF.setText(loanToModify.getStudent().getName() + " " + loanToModify.getStudent().getSurname() + " (" + loanToModify.getStudent().getStudentId() + ")");
        studentSearchTF.setDisable(true);

        bookListView.setVisible(false);
        bookListView.setManaged(false);
        bookSearchTF.setText(loanToModify.getBook().getTitle() + " (" + loanToModify.getBook().getBookId() + ")");
        bookSearchTF.setDisable(true);

        dateDP.setValue(loanToModify.getLoanEnd());
    }

    /**
     * @brief Gestisce l'azione di conferma per la modifica.
     * @details Raccoglie i dati modificati, valida l'oggetto e applica le
     *          modifiche.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(ActionEvent event) {
        try {
            Loan loanModified = new Loan(loanToModify.getStudent(), loanToModify.getBook(), dateDP.getValue());

            loanRegisterModifier.modify(loanToModify, loanModified);
            // Chiudi la finestra
            Stage stage = (Stage) confirmBtn.getScene().getWindow();
            stage.close();
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Errore", "Dati non validi", e.getMessage());
        }
    }
}