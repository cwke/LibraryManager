/**
 * @file BookInsertPopupController.java
 * @brief Controller per la finestra di dialogo di inserimento nuovo libro.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.RegisterAdder;
import softeng.librarymanager.models.RegisterValidator;

/**
 * @class BookInsertPopupController
 * @brief Classe controller per la gestione del popup di inserimento libri.
 * @details Estende {@link BookPopupController} ereditando l'interfaccia grafica comune.
 *          Implementa la logica specifica per creare un nuovo libro e aggiungerlo al registro
 *          utilizzando {@link RegisterAdder}.
 */
public class BookInsertPopupController extends BookPopupController {


    private final RegisterAdder<Book> bookRegisterAdder;
    private final RegisterValidator<Book> bookRegisterValidator;

    public BookInsertPopupController(RegisterAdder<Book> bookRegisterAdder, RegisterValidator<Book> bookRegisterValidator) {
        this.bookRegisterAdder = bookRegisterAdder;
        this.bookRegisterValidator = bookRegisterValidator;
    }

    /**
     * @brief Inizializza il controller.
     */
    @Override
    @FXML
    public void initialize() {
        super.initialize();
    }

    /**
     * @brief Gestisce l'azione di conferma per l'inserimento.
     * @details Raccoglie i dati dai campi, valida il nuovo oggetto e lo aggiunge.
     * @param[in] event L'evento click.
     */
    @Override
    protected void confirmBtnAction(ActionEvent event) {
        try {
            Book bookToAdd = new Book(titleTF.getText(), getAuthorsListPopup(), bookCodeTF.getText(), Integer.parseInt(publishYearTF.getText()), Integer.parseInt(copiesTF.getText()));

            if (bookRegisterValidator.isUnique(bookToAdd)) {
                bookRegisterAdder.add(bookToAdd);
                // Chiudi la finestra
                Stage stage = (Stage) confirmBtn.getScene().getWindow();
                stage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Errore", "Libro duplicato",
                        "Il codice ISBN inserito è già presente.");
            }
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Errore", "Dati non validi", e.getMessage());
        }
    }
}