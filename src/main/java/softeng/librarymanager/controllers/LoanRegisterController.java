/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.interfaces.Register;
import softeng.librarymanager.models.Loan;
import softeng.librarymanager.models.LoanRegister;

/**
 *
 * @author Jakub
 */
public class LoanRegisterController implements Initializable {

    @FXML
    private TableColumn<Loan, String> studentClm;
    @FXML
    private TableColumn<Loan, String> bookClm;
    @FXML
    private TableColumn<Loan, LocalDate> startLoanDateClm;
    @FXML
    private TableColumn<Loan, LocalDate> endLoanDateClm;
    @FXML
    private TableColumn<Loan, String> returnedClm;
    @FXML
    private TableView<Loan> loanTable;
    @FXML
    private SideBarController sideBarController;
    
    private Register<Loan> register;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: impostare i setCellValueFactory...
        
    
        // TODO: eventuali binding per attivazione o disattivazione dei bottoni
        
        
        sideBarController.setAddBtnOnAction(event -> openPopup());
        sideBarController.setModifyBtnOnAction(event -> openPopup());
        sideBarController.setRemoveBtnOnAction(event -> remove());
    }
    
    public void setRegistry(LoanRegister loanRegister) {
        this.register = loanRegister;
    }
    
    private void openPopup() {
        // TODO: 
    }
    
    private void remove() {
        // TODO: logica per l'eliminamento di un Prestito
    }
    
    
    public void searchLoan() {
        // TODO: logica per la ricerca di un prestito
    }
    
}
