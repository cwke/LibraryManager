/**
 * @file MainController.java
 * @brief Questo file contine il controller del file MainView.fxml
 */
package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainController implements Initializable {
    
    @FXML
    BookRegisterController bookRegisterController;
    @FXML
    StudentRegisterController studentRegisterController;
    @FXML
    LoanRegisterController loanRegisterController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * @brief openFileAction è la funzione che apre il file
     */
    @FXML
    private void openFileAction(ActionEvent event) {
    }

    /**
     * @brief closeFileAction è la funzione che chiude il file
     */
    @FXML
    private void closeFileAction(ActionEvent event) {
    }

    /**
     * @brief saveFileAction è la funzione che salva sul file
     */
    @FXML
    private void saveFileAction(ActionEvent event) {
    }

    /**
     * @brief saveFileWithNameAction è la funzione che salva su un file
     */
    @FXML
    private void saveFileWithNameAction(ActionEvent event) {
    }
}
