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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.BookRegister;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class BookPopupController implements Initializable {

    @FXML
    private TextField titleTF;
    @FXML
    private TextField authorsTF;
    @FXML
    private TextField publishYearTF;
    @FXML
    private TextField bookCodeTF;
    @FXML
    private TextField copiesTF;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

    // Si potrebbe utilizzare un interfaccia che abbia come metodi add e isValid
    private BookRegister bookRegister;
   
    
    private boolean editing = false;
    private Book editItem;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // Tutti i campi devono essere riempiti per poter abilitare il pulsante Conferma
        // Andrebbe reso più leggibile
        confirmBtn.disableProperty().bind(
                titleTF.textProperty().isEmpty().or(authorsTF.textProperty().isEmpty().or(publishYearTF.textProperty()
                        .isEmpty().or(bookCodeTF.textProperty().isEmpty().or(copiesTF.textProperty().isEmpty())))));
    
        // Si potrebbe fare tramite binding il controllo se annopubblicazione e numerocopie sono numeri (credo)
       
    }


    @FXML
    private void confirmAction(ActionEvent event) {
        if (this.editing == false) {
            Book newBook = new Book(titleTF.getText(), Integer.parseInt(publishYearTF.getText()), bookCodeTF.getText(), Integer.parseInt(publishYearTF.getText()), authorsTF.getText());
            // ----- Parte da cambiare in base alla ui e la modifica degli autori

            if (!bookRegister.isValid(newBook)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore inserimento dati");
                alert.setHeaderText("Il codice identificativo del libro è già presente nel catalogo o non è valido");
                alert.showAndWait();
            } else {
                bookRegister.add(newBook);
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            }
        } else {   
            // Le modifiche non si riflettono sulla table
            // Credo perché bisogna usare SimpleStringProperty e SimpleIntegerProperty al posto delle classiche String e int in Book
            editItem.setTitle(titleTF.getText());
            editItem.setPublishYear(Integer.parseInt(publishYearTF.getText()));
            editItem.setAvailableCopies(Integer.parseInt(copiesTF.getText()));
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
        
        
    }
    
    /*
    private void edit(ActionEvent event) {
        

        
        // Manca la parte per la modifica degli autori che va prima implementata nell'ui
        
        
        if (!bookCatalog.isValid(newBook)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore inserimento dati");
            alert.setHeaderText("Il codice identificativo del libro è già presente nel catalogo o non è valido");
            alert.showAndWait();
                    
        } else {
            bookCatalog.add(newBook);
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
        
    }
    */

    @FXML
    private void cancelAction(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
    
    
    // Qui si utilizzerebbe public void setRegistry(Registry registry)
    public void setRegistry(BookRegister bookRegister) {
        this.bookRegister = bookRegister;
    }
    
    public void setItemToEdit(Book editItem) {
        this.editing = true;
        this.editItem = editItem;
        
        bookCodeTF.setDisable(true);
        
        titleTF.setText(this.editItem.getTitle());
        publishYearTF.setText("" + this.editItem.getPublishYear()); // Molto lame
        bookCodeTF.setText(this.editItem.getBookCode());
        authorsTF.setText(this.editItem.getAuthors()); // TODO: autori
        copiesTF.setText("" + this.editItem.getAvailableCopies()); // di nuovo: lame
    }

}
