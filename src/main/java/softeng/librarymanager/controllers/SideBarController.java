package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SideBarController {

    @FXML
    private Button addBtn;
    private TextField searchBarTF;
    private Button removeBtn;
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

    public void setAddBtnOnAction(EventHandler<ActionEvent> event) {
        this.addBtn.setOnAction(event);
    }

    public void setRemoveBtnOnAction(EventHandler<ActionEvent> event) {
        this.removeBtn.setOnAction(event);
    }

    public void setModifyBtnOnAction(EventHandler<ActionEvent> event) {
        this.modifyBtn.setOnAction(event);
    }

}