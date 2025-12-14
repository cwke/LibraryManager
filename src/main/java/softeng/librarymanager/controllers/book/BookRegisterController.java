/**
 * @file BookRegisterController.java
 * @brief Controller principale per la vista di gestione del registro dei libri.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.book;

import java.io.IOException;
import java.util.Optional;
import javafx.beans.binding.Binding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import softeng.librarymanager.controllers.SideBarController;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.Register;

/**
 * @class BookRegisterController
 * @brief Classe controller per la gestione dell'interfaccia utente del catalogo
 *        libri.
 * @details Questa classe gestisce la visualizzazione tabellare dei libri,
 *          l'interazione
 *          con la barra laterale (SideBar) e coordina l'apertura dei popup per
 *          l'inserimento
 *          e la modifica dei libri.
 *          Si occupa inoltre di collegare la vista al modello dati
 *          {@link Register}<Book>.
 */
public class BookRegisterController {

    @FXML 
    private TableView<Book> bookTable;
    @FXML 
    private TableColumn<Book, String> titleClm;
    @FXML 
    private TableColumn<Book, String> authorsClm;
    @FXML 
    private TableColumn<Book, Integer> publishmentYearClm;
    @FXML 
    private TableColumn<Book, String> bookIdClm;
    @FXML 
    private TableColumn<Book, Integer> availableCopiesClm;
    @FXML 
    private SideBarController sideBarController;

    private final Register<Book> bookRegister;

    /**
     * @brief Costruttore del controller.
     * @param[in] bookRegister L'istanza del registro libri.
     */
    public BookRegisterController(Register<Book> bookRegister) {
        this.bookRegister = bookRegister;
    }

    /**
     * @brief Metodo di inizializzazione del controller.
     * @details Viene chiamato automaticamente da JavaFX dopo il caricamenteo dell'FXML.
     *          Inizializza la tabella e le colonne, imposta i Binding per i tasti di modifica e rimozione,
     *          aggiunge il listener per la barra di ricerca.
     */
    @FXML
    public void initialize() {
        updateTableView();

        // Configurazione Colonne:
        titleClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getTitle()));
        authorsClm.setCellValueFactory(row -> new SimpleStringProperty(String.join(", ", row.getValue().getAuthors())));
        publishmentYearClm
                .setCellValueFactory(row -> new SimpleIntegerProperty(row.getValue().getPublishmentYear()).asObject());
        bookIdClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getBookId()));
        availableCopiesClm
                .setCellValueFactory(row -> new SimpleIntegerProperty(row.getValue().getAvailableCopies()).asObject());

        // Sidebar
        sideBarController.getAddBtn().setOnAction(event -> openInsertPopup());
        sideBarController.getModifyBtn().setOnAction(event -> openModifyPopup());
        sideBarController.getRemoveBtn().setOnAction(event -> removeFromRegister());

        // Listener per la barra di ricerca
        sideBarController.getSearchBarTF().textProperty().addListener((observable, oldValue, newValue) -> searchBook());

        // Binding: Disabilita i tasti Modifica e Rimuovi se non è selezionata una riga
        Binding<Boolean> noItemSelectedBinding = bookTable.getSelectionModel().selectedItemProperty().isNull();
        sideBarController.getRemoveBtn().disableProperty().bind(noItemSelectedBinding);
        sideBarController.getModifyBtn().disableProperty().bind(noItemSelectedBinding);
    }

    private void openInsertPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/softeng/librarymanager/fxml/BookPopupView.fxml"));

            loader.setController(new BookInsertPopupController(
                    (Book toAdd) -> bookRegister.add(toAdd),
                    (Book toVerify) -> bookRegister.isUnique(toVerify)));

            Parent root = loader.load();
            ScrollPane scrollPane = new ScrollPane(root);
            scrollPane.setFitToWidth(true);
            Scene scene = new Scene(scrollPane, 620, 480);

            // CSS
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

            Stage popup = new Stage();
            popup.setTitle("Inserimento Libro");
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Aggiorna la tabella alla chiusura del popup
        updateTableView();
    }

    private void openModifyPopup() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null)
            return;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/softeng/librarymanager/fxml/BookPopupView.fxml"));

            loader.setController(new BookModifyPopupController(
                    (Book old, Book newObj) -> bookRegister.modify(old, newObj),
                    selectedBook));

            Parent root = loader.load();
            ScrollPane scrollPane = new ScrollPane(root);
            scrollPane.setFitToWidth(true);
            Scene scene = new Scene(scrollPane, 620, 480);
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

            Stage popup = new Stage();
            popup.setTitle("Modifica Libro");
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

        updateTableView();
    }

    private void removeFromRegister() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma rimozione");
        alert.setHeaderText("Sei sicuro di voler rimuovere il libro '" + selectedBook.getTitle() + "'?");
        alert.setContentText("L'operazione è irreversibile.");
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets()
                .add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            bookRegister.remove(selectedBook);
            updateTableView();
        }
    }

    private void searchBook() {
        String searchText = sideBarController.getSearchBarTF().getText();

        // Se la ricerca è vuota, mostra tutto
        if (searchText == null || searchText.isEmpty()) {
            updateTableView();
            return;
        }

        ObservableList<Book> allBooks = FXCollections.observableArrayList(bookRegister.getRegisterList());
        String lowerCaseSearchText = searchText.toLowerCase();
        ObservableList<Book> filteredBooks = FXCollections.observableArrayList();

        for (Book book : allBooks) {
            // Cerca per Titolo, Autore o ISBN
            if (book.getTitle().toLowerCase().contains(lowerCaseSearchText) ||
                    String.join(", ", book.getAuthors()).toLowerCase().contains(lowerCaseSearchText) ||
                    book.getBookId().toLowerCase().contains(lowerCaseSearchText)) {
                filteredBooks.add(book);
            }
        }
        bookTable.setItems(filteredBooks);
    }
    
    /**
     * @brief Aggiorna la TableView con i dati attuali del registro.
     */
    public void updateTableView() {
        bookTable.setItems(FXCollections.observableArrayList(bookRegister.getRegisterList()));
        bookTable.refresh();
    }
    
}