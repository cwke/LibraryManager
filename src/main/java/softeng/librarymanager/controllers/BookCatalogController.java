package softeng.librarymanager.controllers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import softeng.librarymanager.models.Author;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.BookCatalog;

/**
 * FXML Controller class for BookCatalogView.fxml
 */
public class BookCatalogController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleBookClm;
    @FXML
    private TableColumn<Book, String> authorBookClm;
    @FXML
    private TableColumn<Book, Integer> publishYearBookClm;
    @FXML
    private TableColumn<Book, String> codeBookClm;
    @FXML
    private TableColumn<Book, Integer> availableCopiesBookClm;
    @FXML
    private SideBarController sideBarController;

    private BookCatalog bookCatalog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleBookClm.setCellValueFactory(new PropertyValueFactory("title"));
        authorBookClm.setCellValueFactory(new PropertyValueFactory("authors")); // Da cambiare magari con un stringconverter
        codeBookClm.setCellValueFactory(new PropertyValueFactory("bookCode"));

        sideBarController.setAddBtnOnAction(event -> openPopup());
        sideBarController.setModifyBtnOnAction(event -> openPopup());
        sideBarController.setRemoveBtnOnAction(event -> remove());
    }

    private void openPopup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/BookPopupView.fxml"));
        try {
            Parent root = loader.load();

            BookPopupController bookPopupController = loader.getController();
            bookPopupController.setRegistry(this.bookCatalog);

            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                bookPopupController.setItemToEdit(selectedBook);
            }

            Scene scene = new Scene(root, 480, 720);
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(BookCatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void remove() {
    }

    public void setRegistry(BookCatalog bookCatalog) {
        this.bookCatalog = bookCatalog;

        bookTable.setItems(bookCatalog.getObservableList());
    }

    // TODO (per la ricerca studenti)
    private void searchBook() {

    }

}
