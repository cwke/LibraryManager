/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.StudentRegister;

/**
 *
 * @author Jakub
 */
public class StudentRegisterController implements Initializable{

    @FXML
    private TableView<?> studentTable;
    @FXML
    private TableColumn<?, ?> nameClm;
    @FXML
    private TableColumn<?, ?> surnameClm;
    @FXML
    private TableColumn<?, ?> studentCodeClm;
    @FXML
    private TableColumn<?, ?> emailClm;
    
    @FXML
    private SideBarController sideBarController;
    
    private StudentRegister studentRegister;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void setRegistry(StudentRegister studentRegister) {
        this.studentRegister = studentRegister;
    }
    
    private void searchStudent() {}
    
}
