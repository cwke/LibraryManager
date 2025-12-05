/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import softeng.librarymanager.interfaces.ItemAcceptor;
import softeng.librarymanager.interfaces.Register;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.Loan;
import softeng.librarymanager.models.Student;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class LoanPopupController implements Initializable {

    @FXML
    private ComboBox<Student> studentCB;
    @FXML
    private ComboBox<Book> bookCB;
    @FXML
    private DatePicker dateDP;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

    // Si potrebbe creare un interfaccia (es. Raccolta?) che contiene solo il metodo getObservableList (che è solo quello che ci serve per le combobox) 
    private Register<Student> studentRegister;
    private Register<Book> bookRegister;
    
    
    private ItemAcceptor<Loan> itemAcceptor;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmAction(ActionEvent event) {
        // TODO: Logica per la verifica e conferma di un prestito
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        // TODO: chiusura della finestra
    }
    
  
    public void setRegisters(Register<Student> studentRegister, Register<Book> bookRegister) {
        // Da cambiare in base all'interfaccia che vogliamo adottare
        this.bookRegister = bookRegister;
        this.studentRegister = studentRegister;
    }
    
    public void setItemAcceptor(ItemAcceptor itemAcceptor) {
        this.itemAcceptor = itemAcceptor;
    }
    
    public void setItemToEdit(Loan editItem) {
        //TODO: logica per la modifica di un prestito
    }
    
}
