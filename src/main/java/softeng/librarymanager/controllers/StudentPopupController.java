package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StudentPopupController implements Initializable {

    @FXML
    private TextField nameTF;
    @FXML
    private TextField surnameTF;
    @FXML
    private TextField codeTF;
    @FXML
    private TextField mailTF;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmAction(ActionEvent event) {
    }

    @FXML
    private void cancelAction(ActionEvent event) {
    }
    
}
