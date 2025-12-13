/**
 * @file LoanInsertPopupController.java
 * @brief Controller per la finestra di dialogo di creazione di un nuovo prestito.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.loan;

import javafx.fxml.FXML;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.Loan;
import softeng.librarymanager.models.RegisterAdder;
import softeng.librarymanager.models.RegisterObtainer;
import softeng.librarymanager.models.RegisterValidator;
import softeng.librarymanager.models.Student;

/**
 * @class LoanInsertPopupController
 * @brief Classe controller per la gestione del popup di inserimento prestiti.
 * @details Estende {@link LoanPopupController}. Si occupa di popolare le ComboBox
 *          recuperando le liste di studenti e libri tramite {@link RegisterObtainer} e
 *          di aggiungere il nuovo prestito tramite {@link RegisterAdder}.
 */
public class LoanInsertPopupController extends LoanPopupController {

    private RegisterAdder<Loan> loanRegisterAdder;
    private RegisterObtainer<Book> bookRegisterObtainer;
    private RegisterObtainer<Student> studentRegisterObtainer;
    private RegisterValidator<Loan> loanRegisterValidator;

    public LoanInsertPopupController(RegisterAdder<Loan> loanRegisterAdder, RegisterObtainer<Book> bookRegisterObtainer, RegisterObtainer<Student> studentRegisterObtainer, RegisterValidator<Loan> loanRegisterValidator) {
        this.loanRegisterAdder = loanRegisterAdder;
        this.bookRegisterObtainer = bookRegisterObtainer;
        this.studentRegisterObtainer = studentRegisterObtainer;
        this.loanRegisterValidator = loanRegisterValidator;
    }

    /**
     * @brief Inizializza il controller.
     * @details Richiama l'inizializzazione della superclasse.
     */
    @FXML
    public void initialize() {
    }

    /**
     * @brief Imposta il delegato per ottenere il registro dei libri.
     * @param[in] bookRegisterObtainer L'istanza che implementa RegisterObtainer<Book>.
     */
    public void setBookRegisterObtainer(RegisterObtainer<Book> bookRegisterObtainer) {
        this.bookRegisterObtainer = bookRegisterObtainer;
    }

    /**
     * @brief Imposta il delegato per ottenere il registro degli studenti.
     * @param[in] studentRegisterObtainer L'istanza che implementa RegisterObtainer<Student>.
     */
    public void setStudentRegisterObtainer(RegisterObtainer<Student> studentRegisterObtainer) {
        this.studentRegisterObtainer = studentRegisterObtainer;
    }

    /**
     * @brief Imposta il delegato per l'aggiunta del prestito.
     * @param[in] loanRegisterAdder L'istanza che implementa RegisterAdder<Loan>.
     */
    public void setLoanRegisterAdder(RegisterAdder<Loan> loanRegisterAdder) {
        this.loanRegisterAdder = loanRegisterAdder;
    }

    /**
     * @brief Imposta il delegato per la validazione.
     * @param[in] loanRegisterValidator L'istanza che implementa RegisterValidator<Loan>.
     */
    public void setLoanRegisterValidator(RegisterValidator<Loan> loanRegisterValidator) {
        this.loanRegisterValidator = loanRegisterValidator;
    }

    /**
     * @brief Gestisce l'azione di conferma per l'inserimento.
     * @details Crea un nuovo oggetto Loan associando Studente e Libro selezionati,
     *          lo valida e lo aggiunge al registro.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(javafx.event.ActionEvent event) {
        // Implementazione specifica
    }

}
