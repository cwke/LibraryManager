/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Jakub
 */
public class BookCatalogController {

    @FXML
    private TableView<?> bookTable;
    @FXML
    private TableColumn<?, ?> titleBookCln;
    @FXML
    private TableColumn<?, ?> authorBookCln;
    @FXML
    private TableColumn<?, ?> publishYearBookCln;
    @FXML
    private TableColumn<?, ?> codeBookCln;
    @FXML
    private TableColumn<?, ?> availableCopiesBookCln;
    @FXML
    private TextField searchBookTF;
    @FXML
    private Button addBookBtn;
    @FXML
    private Button removeBookBtn;
    @FXML
    private Button modifyBookBtn;

    @FXML
    private void addBook(ActionEvent event) {
    }

    @FXML
    private void removeBook(ActionEvent event) {
    }

    @FXML
    private void modifyBook(ActionEvent event) {
    }
    
}
