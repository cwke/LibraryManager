/**
 * @file BookRegisterController.java
 * @brief Questo file contine il controller del file BookRegisterView.fxml
 */

package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.Book;

public class BookRegisterController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleClm;
    @FXML
    private TableColumn<Book, String> authorClm;
    @FXML
    private TableColumn<Book, Integer> publishYearClm;
    @FXML
    private TableColumn<Book, String> bookIdClm;
    @FXML
    private TableColumn<Book, Integer> availableCopiesClm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
