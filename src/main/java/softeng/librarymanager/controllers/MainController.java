/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.BookRegister;
import softeng.librarymanager.models.LoanRegister;
import softeng.librarymanager.models.StudentRegister;

/**
 *
 * @author Jakub
 */
public class MainController implements Initializable {
    
    //@FXML
    //BookCatalogController bookCatalogController;
    
    BookRegister bookRegister;
    StudentRegister studentRegister;
    LoanRegister loanRegister;
    
    @FXML
    BookRegisterController bookRegisterController;
    @FXML
    StudentRegisterController studentRegisterController;
    @FXML
    LoanRegisterController loanRegisterController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookRegister = new BookRegister();

        // codice di prova : va rimosso!
        bookRegister.add(new Book("titolo", 2025, "1234567890000", 3, "nome1 cognome1, nome2 cognome2"));
        bookRegister.add(new Book("superlibro", 2025, "1234567890111", 1, "nome1 cognome1, nome2 cognome2"));
        // Fine codice di prova
        
        bookRegisterController.setRegistry(bookRegister);
        studentRegisterController.setRegistry(studentRegister);
        loanRegisterController.setRegistry(loanRegister);
    }

    @FXML
    private void openAction(ActionEvent event) {
    }

    @FXML
    private void closeAction(ActionEvent event) {
    }

    @FXML
    private void saveAction(ActionEvent event) {
    }

    @FXML
    private void saveWithNameAction(ActionEvent event) {
    }

}
