/**
 * @file BookModifyPopupController.java
 * @brief Controller per la finestra di dialogo di modifica libro.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.book;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.RegisterModifier;

/**
 * @class BookModifyPopupController
 * @brief Classe controller per la gestione del popup di modifica libri.
 * @details Estende {@link BookPopupController}. Gestisce il popolamento dei
 *          campi con i dati esistenti e l'aggiornamento del libro nel registro
 *          tramite {@link RegisterModifier}.
 */
public class BookModifyPopupController extends BookPopupController {

    private RegisterModifier<Book> bookRegisterModifier;
    private Book bookToModify;

    /**
     * @brief Costruttore.
     * @param[in] BookRegisterModifier L'istanza che implementa RegisterModifier<Book>.
     * @param[in] bookToModify Il libro selezionato da modificare.
     */
    public BookModifyPopupController(RegisterModifier<Book> bookRegisterModifier, Book bookToModify) {
        this.bookRegisterModifier = bookRegisterModifier;
        this.bookToModify = bookToModify;
    }

    /**
     * @brief Metodo di inizializzazione del controller.
     * @details Viene chiamato automaticamente da JavaFX dopo il caricamenteo dell'FXML.
     *          Inizializza i campi con i dati esistenti e imposta i listener.
     */
    @Override
    @FXML
    public void initialize() {
        super.initialize();

        // Placeholder campi
        if (bookToModify != null) {
            titleTF.setText(bookToModify.getTitle());
            Author1TF.setText(bookToModify.getAuthors().get(0));
            if (bookToModify.getAuthors().size() >= 2) {
                addAuthorsBtn.fire();
                dynamicAuthorFields.get(0).setText(bookToModify.getAuthors().get(1));
            }
            if (bookToModify.getAuthors().size() == 3) {
                addAuthorsBtn.fire();
                dynamicAuthorFields.get(1).setText(bookToModify.getAuthors().get(2));
            }
            bookCodeTF.setText(bookToModify.getBookId());
            publishYearTF.setText(String.valueOf(bookToModify.getPublishmentYear()));
            copiesTF.setText(String.valueOf(bookToModify.getAvailableCopies()));

            bookCodeTF.setDisable(true);
        }
    }

    @Override
    protected void confirmBtnAction(javafx.event.ActionEvent event) {
        try {
            Book bookModified = new Book(titleTF.getText(), getAuthorsListPopup(), bookCodeTF.getText(), Integer.parseInt(publishYearTF.getText()), Integer.parseInt(copiesTF.getText()));

            bookRegisterModifier.modify(bookToModify, bookModified);

            Stage stage = (Stage) confirmBtn.getScene().getWindow();
            stage.close();
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Errore", "Dati non validi", e.getMessage());
        }
    }
    
}