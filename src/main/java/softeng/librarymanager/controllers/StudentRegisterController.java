package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.interfaces.Register;
import softeng.librarymanager.models.Student;
import softeng.librarymanager.models.StudentRegister;

public class StudentRegisterController implements Initializable {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> nameClm;
    @FXML
    private TableColumn<Student, String> surnameClm;
    @FXML
    private TableColumn<Student, String> studentCodeClm;
    @FXML
    private TableColumn<Student, String> emailClm;
    
    @FXML
    private SideBarController sideBarController;
    
    private Register<Student> register;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO fai implementazione: setta il valore che le colonne devono visualizzare
    }
    
    public void setRegister(StudentRegister studentRegister) {
        this.register = studentRegister;

        //studentTable.setItems(register.getObservableList());
    }
    
    private void searchStudent() {
        //TODO fai implementazione
    }
    
}
