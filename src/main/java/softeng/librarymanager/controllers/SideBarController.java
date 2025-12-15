/**
 * @file SideBarController.java
 * @brief Controller per il componente grafico della barra laterale (Sidebar).
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * @class SideBarController
 * @brief Classe controller che gestisce la vista della barra laterale.
 * @details Questa classe si occupa di mantenere i riferimenti ai componenti grafici
 *          comuni (pulsanti di aggiunta, rimozione, modifica e barra di ricerca).
 *          Questi componenti vengono esposti tramite metodi getter per essere
 *          utilizzati e gestiti dai controller principali (es. BookRegisterController).
 */
public class SideBarController {

    @FXML
    private Button addBtn;
    @FXML
    private TextField searchBarTF;
    @FXML
    private Button removeBtn;
    @FXML
    private Button modifyBtn;
    @FXML
    private VBox sideBarVBox;

    /**
     * @brief Restituisce il riferimento al bottone di aggiunta.
     * @return Button Il bottone "Aggiungi".
     */
    public Button getAddBtn() {
        return this.addBtn;
    }

    /**
     * @brief Restituisce il riferimento al campo di testo per la ricerca.
     * @return TextField La barra di ricerca.
     */
    public TextField getSearchBarTF() {
        return this.searchBarTF;
    }

    /**
     * @brief Restituisce il riferimento al bottone di rimozione.
     * @return Button Il bottone "Rimuovi".
     */
    public Button getRemoveBtn() {
        return this.removeBtn;
    }

    /**
     * @brief Restituisce il riferimento al bottone di modifica.
     * @return Button Il bottone "Modifica".
     */
    public Button getModifyBtn() {
        return this.modifyBtn;
    }

    /**
     * @brief Restituisce il riferimento al bottone di restituzione.
     * @return Button Il bottone "Restituisci".
     */
    public Button createReturnBtn() {
        Button returnBtn = new Button("Restituisci");
        returnBtn.setId("returnBtn");

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/softeng/librarymanager/assets/check.png")));
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        imageView.setPreserveRatio(true);

        returnBtn.setGraphic(imageView);
        sideBarVBox.getChildren().add(returnBtn);
        return returnBtn;
    }

    public ComboBox<String> createReturnCB() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Prestiti attivi",
                "Prestiti estinti",
                "Tutti"
        );
        comboBox.getSelectionModel().selectFirst();
        sideBarVBox.getChildren().add(comboBox);
        return comboBox;
    }
}
