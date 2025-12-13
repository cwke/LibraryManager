/**
 * @file BookPopupController.java
 * @brief Controller astratto base per i popup di gestione libri.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.book;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
     * @details Metodo chiamato automaticamente da JavaFX dopo il caricamento
     *          dell'FXML.
     */
    @FXML
    public void initialize() {
        if (addAuthorsBtn != null) {
            addAuthorsBtn.disableProperty().bind(Bindings.size(authorsListVBox.getChildren()).greaterThanOrEqualTo(3));
        }
    }

    // PARTE DI CODICE PER AUTORI

    protected final Image removeImg = new Image(
            getClass().getResourceAsStream("/softeng/librarymanager/assets/remove.png"));

    @FXML
    protected HBox createAuthorRow(int authorNum) {
        HBox row = new HBox();
        row.setAlignment(javafx.geometry.Pos.CENTER);
        row.setSpacing(20.0);

        Label label = new Label("Autore " + authorNum + ":");
        TextField tf = new TextField();

        ImageView view = new ImageView(removeImg);
        view.setFitHeight(16);
        view.setPreserveRatio(true);

        Button btn = new Button();
        btn.setGraphic(view);
        btn.setOnAction(e -> {
            authorsListVBox.getChildren().remove(row);
        });

        btn.disableProperty().bind(Bindings.createBooleanBinding(() -> {
            return authorsListVBox.getChildren().size() >= 3 && authorsListVBox.getChildren().indexOf(row) < 2;
        }, authorsListVBox.getChildren()));

        row.getChildren().addAll(label, tf, btn);
        return row;
    }

    @FXML
    public void addAuthors(ActionEvent event) {
        int nextAuthorNum = authorsListVBox.getChildren().size() + 1;
        authorsListVBox.getChildren().add(createAuthorRow(nextAuthorNum));
    }

    // FINE PARTE DI CODICE PER AUTORI

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

        // 2. Dal pulsante, otteniamo la Scena, e dalla Scena otteniamo la Finestra (Stage)
        Stage stage = (Stage) source.getScene().getWindow();

        // 3. Chiudiamo la finestra
        stage.close();
    }
}