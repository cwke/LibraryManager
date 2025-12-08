/**
 * @file StudentRegisterController.java
 * @brief Questo file contine il controller del file StudentRegisterView.fxml
 */

package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.Student;

public class StudentRegisterController implements Initializable {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> nameClm;
    @FXML
    private TableColumn<Student, String> surnameClm;
    @FXML
    private TableColumn<Student, String> studentIdClm;
    @FXML
    private TableColumn<Student, String> emailClm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
