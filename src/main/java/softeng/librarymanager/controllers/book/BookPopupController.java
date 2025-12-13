/**
 * @file BookPopupController.java
 * @brief Controller astratto base per i popup di gestione libri.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @class BookPopupController
 * @brief Classe base astratta per i controller dei popup (Inserimento e
 *        Modifica).
 * @details Raccoglie i componenti grafici comuni (TextField, Button) definiti
 *          nel diagramma
 *          e gestisce la logica di base condivisa dalle finestre popup di
 *          dialogo dei libri.
 */
public abstract class BookPopupController {

    @FXML protected TextField titleTF;
    @FXML protected VBox authorsListVBox;
    @FXML protected TextField Author1TF;
    @FXML protected TextField publishYearTF;
    @FXML protected TextField copiesTF;
    @FXML protected TextField bookCodeTF;
    @FXML protected Button confirmBtn;
    @FXML protected Button cancelBtn;
    @FXML protected Button addAuthorsBtn;

    /**
     * @brief Inizializzazione base del controller.
     */
    @FXML
    public void initialize() {
        if (addAuthorsBtn != null) {
            addAuthorsBtn.disableProperty().bind(Bindings.size(authorsListVBox.getChildren()).greaterThanOrEqualTo(3));
        }
    }

    // PARTE DI CODICE PER AUTOR

    protected List<TextField> dynamicAuthorFields = new ArrayList<>();

    private final Image removeImg = new Image(
            getClass().getResourceAsStream("/softeng/librarymanager/assets/remove.png"));

    @FXML
    private HBox createAuthorRow(int authorNum) {
        HBox row = new HBox();
        row.setAlignment(javafx.geometry.Pos.CENTER);
        row.setSpacing(20.0);

        Label label = new Label("Autore " + authorNum + ":");
        TextField tf = new TextField();

        // Aggiungi alla lista
        dynamicAuthorFields.add(tf);

        ImageView view = new ImageView(removeImg);
        view.setFitHeight(16);
        view.setPreserveRatio(true);

        Button btn = new Button();
        btn.setGraphic(view);
        btn.setOnAction(e -> {
            authorsListVBox.getChildren().remove(row);
            // Rimuovi dalla lista quando la riga viene eliminata
            dynamicAuthorFields.remove(tf);
        });

        btn.disableProperty().bind(Bindings.createBooleanBinding(() -> {
            return authorsListVBox.getChildren().size() >= 3 && authorsListVBox.getChildren().indexOf(row) < 2;
        }, authorsListVBox.getChildren()));

        row.getChildren().addAll(label, tf, btn);
        return row;
    }

    protected List<String> getAuthorsListPopup() {
        List<String> authors = new ArrayList<>();

        // 1. Aggiungi autore principale
        if (Author1TF.getText() != null && !Author1TF.getText().trim().isEmpty()) {
            authors.add(Author1TF.getText().trim());
        }

        // 2. Aggiungi autori dai campi dinamici
        for (TextField tf : dynamicAuthorFields) {
            String text = tf.getText();
            if (text != null && !text.trim().isEmpty()) {
                authors.add(text.trim());
            }
        }
        return authors;
    }

    @FXML
    private void addAuthors(ActionEvent event) {
        int nextAuthorNum = authorsListVBox.getChildren().size() + 1;
        authorsListVBox.getChildren().add(createAuthorRow(nextAuthorNum));
    }

    // FINE PARTE DI CODICE PER AUTORI

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
     * @details Chiude la finestra corrente senza salvare le modifiche.
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