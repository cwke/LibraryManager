package softeng.librarymanager.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.Book;

/**
 * FXML Controller class for BookCatalogView.fxml
 */
public class BookCatalogController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<?, ?> titleBookClm;
    @FXML
    private TableColumn<?, ?> authorBookClm;
    @FXML
    private TableColumn<?, ?> publishYearBookClm;
    @FXML
    private TableColumn<?, ?> codeBookClm;
    @FXML
    private TableColumn<?, ?> availableCopiesBookClm;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Initialize the columns and table data
        //sideBarController.setAddBtnOnAction(event -> test());
    }
    

    // TODO (per la ricerca studenti)
    public void searchBook() {}
}
