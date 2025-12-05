package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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