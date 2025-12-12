/**
 * @file BookRegisterController.java
 * @brief Controller principale per la vista di gestione del registro dei libri.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.Register;

import java.io.IOException;

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

    /**
     * @brief Tabella per la visualizzazione dell'elenco dei libri.
     */
    @FXML
    private TableView<Book> bookTable;

    /**
     * @brief Colonna della tabella per il titolo del libro.
     */
    @FXML
    private TableColumn<Book, String> titleClm;

    /**
     * @brief Colonna della tabella per gli autori del libro.
     */
    @FXML
    private TableColumn<Book, String> authorsClm;

    /**
     * @brief Colonna della tabella per l'anno di pubblicazione.
     */
    @FXML
    private TableColumn<Book, Integer> publishmentYearClm;

    /**
     * @brief Colonna della tabella per l'ID (ISBN) del libro.
     */
    @FXML
    private TableColumn<Book, String> bookIdClm;

    /**
     * @brief Colonna della tabella per il numero di copie disponibili.
     */
    @FXML
    private TableColumn<Book, Integer> availableCopiesClm;

    /**
     * @brief Riferimento al modello del registro libri.
     * @see Register
     */
    private Register<Book> bookRegister;

    /**
     * @brief Riferimento al controller della barra laterale (incluso via
     *        <fx:include>).
     */
    @FXML
    private SideBarController sideBarController;

    /**
     * @brief Controller per il popup di inserimento libro.
     */
    private BookInsertPopupController bookInsertPopupController;

    /**
     * @brief Controller per il popup di modifica libro.
     */
    private BookModifyPopupController bookModifyPopupController;

    /**
     * @brief Costruttore del controller.
     * @param[in] bookRegister L'istanza del registro libri.
     */
    public BookRegisterController(Register<Book> bookRegister) {
        this.bookRegister = bookRegister;
    }

    /**
     * @brief Metodo di inizializzazione del controller JavaFX.
     * @details Configura le colonne della tabella (binding con le proprietà di
     *          Book)
     *          e associa i listener agli eventi dei bottoni della SideBar
     *          (Aggiungi, Modifica, Rimuovi).
     */
    @FXML
    public void initialize() {
        bookTable.setItems(FXCollections.observableArrayList(bookRegister.getRegister()));
        // Binding per la visualizzazione degli studenti nella tabella

        titleClm.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorsClm.setCellValueFactory(new PropertyValueFactory<>("authors"));
        publishmentYearClm.setCellValueFactory(new PropertyValueFactory<>("publishmentYear"));
        bookIdClm.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        availableCopiesClm.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

        // Sidebar
        sideBarController.getAddBtn().setOnAction(event -> openInsertPopup());
        sideBarController.getModifyBtn().setOnAction(event -> openModifyPopup());
        sideBarController.getRemoveBtn().setOnAction(event -> removeFromRegister());

        // Binding per la disabilitazione del tasto rimuovi se non è selezionato nessun
        // item
        sideBarController.getRemoveBtn().disableProperty()
                .bind(bookTable.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * @brief Apre il popup per l'inserimento di un nuovo libro.
     * @details Invocato alla pressione del tasto "Aggiungi" nella SideBar.
     */
    private void openInsertPopup() {
        try {
            // 1. Carica il file FXML
            // Usa percorso assoluto per caricare correttamente dalla cartella resources
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/softeng/librarymanager/fxml/BookPopupView.fxml"));

            // 2. Istanzia e imposta il controller manualmente per passare le dipendenze
            BookInsertPopupController controller = new BookInsertPopupController(
                    bookRegister::add,
                    bookRegister::isValid);
            loader.setController(controller);

            Parent root = loader.load();

            // 3. Crea il nuovo Stage (la finestra)
            Stage stage = new Stage();
            stage.setTitle("Inserimento studente");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());
            stage.setScene(scene);

            // 4. Configura il comportamento "Popup" (Modale)
            // WINDOW_MODAL o APPLICATION_MODAL blocca l'interazione con la finestra sotto
            stage.initModality(Modality.APPLICATION_MODAL);

            // (Opzionale) Imposta la finestra principale come proprietaria (così il popup
            // sta sempre sopra)
            // stage.initOwner(bottoneCheHaChiamato.getScene().getWindow());

            // 5. Mostra la finestra
            stage.showAndWait(); // showAndWait blocca l'esecuzione finché il popup non viene chiuso

        } catch (IOException e) {
            e.printStackTrace(); // Utile per vedere se sbagli il percorso del file
        }

    }

    /**
     * @brief Apre il popup per la modifica del libro selezionato.
     * @details Invocato alla pressione del tasto "Modifica" nella SideBar.
     *          Recupera l'elemento selezionato nella TableView e lo passa al popup.
     */
    private void openModifyPopup() {
    }

    /**
     * @brief Rimuove il libro selezionato dal registro.
     * @details Invocato alla pressione del tasto "Rimuovi" nella SideBar.
     */
    private void removeFromRegister() {
    }

}
