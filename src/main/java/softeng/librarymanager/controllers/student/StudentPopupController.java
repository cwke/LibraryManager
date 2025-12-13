/**
 * @file StudentPopupController.java
 * @brief Controller astratto base per i popup di gestione degli studenti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @class StudentPopupController
 * @brief Classe base astratta per i controller dei popup studenti (Inserimento e Modifica).
 * @details Raccoglie i componenti grafici comuni (TextField per l'anagrafica) e gestisce
 *          la logica di base condivisa dalle finestre popup di dialogo degli studenti.
 */
public abstract class StudentPopupController {

    @FXML protected TextField nameTF;
    @FXML protected TextField surnameTF;
    @FXML protected TextField studentIdTF;
    @FXML protected TextField emailTF;
    @FXML protected Button confirmBtn;
    @FXML protected Button cancelBtn;

    /**
     * @brief Inizializzazione base del controller.
     */
    @FXML
    public void initialize() {
    }

    /**
     * @brief Gestisce l'evento di click sul bottone di conferma.
     * @details Metodo astratto da implementare nelle sottoclassi per definire
     *          la logica specifica (Inserimento o Modifica).
     * @param[in] event L'evento generato dal click.
     */
    @FXML
    public abstract void confirmBtnAction(ActionEvent event);

    /**
     * @brief Gestisce l'evento di click sul bottone di annullamento.
     * @details Chiude la finestra corrente.
     * @param[in] event L'evento generato dal click.
     */
    @FXML
    public void cancelBtnAction(ActionEvent event) {
        // 1. Recuperiamo il componente che ha scatenato l'evento (il pulsante)
        Node source = (Node) event.getSource();

        // 2. Dal pulsante, otteniamo la Scena, e dalla Scena otteniamo la Finestra
        // (Stage)
        Stage stage = (Stage) source.getScene().getWindow();

        // 3. Chiudiamo la finestra
        stage.close();
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
