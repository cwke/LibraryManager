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

/**
 *
 * @author Jakub
 */
public class LoanRegisterController implements Initializable {

    @FXML
    private TableColumn<?, ?> studentClm;
    @FXML
    private TableColumn<?, ?> bookClm;
    @FXML
    private TableColumn<?, ?> startLoanDateClm;
    @FXML
    private TableColumn<?, ?> endLoanDateClm;
    @FXML
    private TableColumn<?, ?> returnedClm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    // TODO (per la ricerca di un prestito)
    public void searchLoan() {}
    
}
