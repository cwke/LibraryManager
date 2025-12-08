/**
 * @file SideBarController.java
 * @brief Questo file contine il controller del file SideBarView.fxml
 */

package softeng.librarymanager.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SideBarController {

    @FXML
    private Button addBtn;
    @FXML
    private TextField searchBarTF;
    @FXML
    private Button removeBtn;
    @FXML
    private Button modifyBtn;

    public Button getAddBtn() {
        return addBtn;
    }

    public TextField getSearchBarTF() {
        return searchBarTF;
    }

    public Button getRemoveBtn() {
        return removeBtn;
    }

    public Button getModifyBtn() {
        return modifyBtn;
    }
}
