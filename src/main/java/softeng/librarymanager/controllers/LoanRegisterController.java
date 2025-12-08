/**
 * @file LoanRegisterController.java
 * @brief Questo file contine il controller del file LoanRegisterView.fxml
 */

package softeng.librarymanager.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.Loan;

public class LoanRegisterController implements Initializable {

    @FXML
    private TableView<Loan> loanTable;
    @FXML
    private TableColumn<Loan, String> studentClm;
    @FXML
    private TableColumn<Loan, String> bookClm;
    @FXML
    private TableColumn<Loan, LocalDate> loanEndClm;
    @FXML
    private TableColumn<Loan, String> returnedClm;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
