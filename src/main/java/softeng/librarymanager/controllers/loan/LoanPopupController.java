/**
 * @file LoanPopupController.java
 * @brief Controller astratto base per i popup di gestione prestiti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.loan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.Student;

/**
 * @class LoanPopupController
 * @brief Classe base astratta per i controller dei popup prestiti (Inserimento e Modifica).
 * @details Raccoglie i componenti grafici comuni (TextField, Button, ComboBox) e gestisce
 *          la logica di base condivisa dalle finestre popup di dialogo dei prestiti.
 */
public abstract class LoanPopupController {

    @FXML protected TextField studentSearchTF;
    @FXML protected ListView<Student> studentListView;
    @FXML protected TextField bookSearchTF;
    @FXML protected ListView<Book> bookListView;
    @FXML protected javafx.scene.control.DatePicker dateDP;
    @FXML protected Button confirmBtn;
    @FXML protected Button cancelBtn;

    /**
     * @brief Inizializzazione base del controller.
     * @details Metodo chiamato automaticamente da JavaFX dopo il caricamento dell'FXML.
     */
    @FXML
    public void initialize() {
    }

    /**
     * @brief Gestisce l'evento di click sul bottone di conferma.
     * @details Metodo astratto o base da implementare nelle sottoclassi per definire
     *          la logica specifica (Inserimento o Modifica).
     * @param[in] event L'evento generato dal click.
     */
    @FXML
    public abstract void confirmBtnAction(ActionEvent event);

    /**
     * @brief Gestisce l'evento di click sul bottone di annullamento.
     * @details Chiude la finestra corrente senza salvare le modifiche.
     * @param[in] event L'evento generato dal click.
     */
    @FXML
    public void cancelBtnAction(ActionEvent event) {
    }
    
    protected void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());
        alert.showAndWait();
    }
}
