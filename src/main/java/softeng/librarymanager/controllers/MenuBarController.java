/**
 * @file MenuBarController.java
 * @brief Controller per la barra dei menu dell'applicazione.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */
package softeng.librarymanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import softeng.librarymanager.models.Library;
import softeng.librarymanager.models.LibraryIOManager;
import javafx.scene.control.Alert;
import java.io.File;

/**
 * @class MenuBarController
 * @brief Classe controller che gestisce le operazioni della barra dei menu (File I/O).
 * @details Questa classe è responsabile della gestione del ciclo di vita dei file della libreria.
 * Gestisce l'apertura, il salvataggio e la chiusura dei file di libreria (.obj) interagendo
 * con il {@link LibraryIOManager}. Inoltre, notifica il controller principale tramite
 * l'interfaccia {@link Refresh} quando viene caricata una nuova libreria.
 */
public class MenuBarController implements ResultActions {

    @FXML
    private MenuBar menuBar;

    private LibraryIOManager libraryIOManager;
    private String defaultSavePath;
    private Refresh mainRefresher;
    private Library library;

    /**
     * @brief Metodo di inizializzazione del controller.
     * @details Viene chiamato automaticamente da JavaFX. Inizializza il manager
     * per l'Input/Output della libreria passando il controller stesso come listener per i risultati.
     * @post Inizializza l'istanza del MenuBarController.
     */
    @FXML
    public void initialize() {
        this.libraryIOManager = new LibraryIOManager(this);
    }

    /**
     * @brief Imposta l'istanza corrente della libreria.
     * @param[in] library L'oggetto Library su cui operare.
     * @post Imposta la libreria del MenuBarController.
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * @brief Imposta il riferimento per l'aggiornamento della vista principale.
     * @param[in] mainRefresher Oggetto che implementa l'interfaccia {@link Refresh} (solitamente MainController).
     * @post Imposta il mainRefresh del MenuBarController.
     */
    public void setMainRefresher(Refresh mainRefresher){
        this.mainRefresher = mainRefresher;
    }

    /**
     * @brief Gestisce l'azione di apertura di un file di libreria.
     * @details Apre un FileChooser per selezionare un file .obj. Se il caricamento ha successo,
     * aggiorna l'istanza interna di Library e invoca il refresh sul MainController.
     * @param[in] event L'evento generato dal click sulla voce di menu.
     * @post Carica una libreria contenuta in un file e invoca il refresh.
     */
    @FXML
    public void openFile(ActionEvent event) {
        File selectedFile = fileSelection(true);
        if (selectedFile != null) {
            this.defaultSavePath = selectedFile.getAbsolutePath();
            Library loaded = libraryIOManager.loadLibrary(this.defaultSavePath);
            if (loaded != null) {
                this.library = loaded;
                if (mainRefresher != null) mainRefresher.refresh(loaded);
            }
        }
    }

    /**
     * @brief Gestisce l'azione di chiusura della libreria corrente.
     * @details Resetta il percorso di salvataggio e crea una nuova istanza vuota di Library.
     * Aggiorna conseguentemente l'interfaccia principale tramite l'invocazione del refresh.
     * @param[in] event L'evento generato dal click sulla voce di menu.
     * @post carica una nuova libreria e invoca il refresh.
     */
    @FXML
    public void closeFile(ActionEvent event) {
        this.defaultSavePath = null;
        this.library = new Library();
        if (mainRefresher != null) mainRefresher.refresh(this.library);
    }

    /**
     * @brief Gestisce l'azione di salvataggio rapido.
     * @details Se esiste già un percorso di salvataggio predefinito, sovrascrive il file.
     * Altrimenti, invoca {@link #saveFileWithName(ActionEvent)} per specificare un percorso.
     * @param[in] event L'evento generato dal click sulla voce di menu.
     * @post Salva la libreria sovrascrivendo il file puntato dal path.
     */
    @FXML
    public void saveFile(ActionEvent event) {
        if (defaultSavePath == null) {
            saveFileWithName(event);
        } else {
            if (library != null)
                libraryIOManager.saveLibrary(library, defaultSavePath);
            else
                failure();
        }
    }

    /**
     * @brief Gestisce l'azione "Salva con nome".
     * @details Apre un FileChooser per permettere all'utente di specificare dove salvare
     * il file della libreria corrente. Infine salva il file.
     * @param[in] event L'evento generato dal click sulla voce di menu.
     * @post Salva la libreria sul file puntato dal path.
     */
    @FXML
    public void saveFileWithName(ActionEvent event) {
        File selectedFile = fileSelection(false);
        if (selectedFile != null) {
            this.defaultSavePath = selectedFile.getAbsolutePath();
            if (library != null) libraryIOManager.saveLibrary(library, this.defaultSavePath);
        }
        else failure();
    }

    private File fileSelection(boolean openFile) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selezione file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File Libreria (*.obj)", "*.obj"));

        Window window = (menuBar.getScene() != null) ? menuBar.getScene().getWindow() : null;

        return openFile ? fileChooser.showOpenDialog(window) : fileChooser.showSaveDialog(window);
    }

    /**
     * @brief Callback invocata in caso di fallimento di un'operazione I/O.
     * @details Implementazione dell'interfaccia {@link ResultActions}. Mostra un alert di errore.
     * @post Viene mostrato un allert di fallimento.
     */
    @Override
    public void failure() {
        showAlert(Alert.AlertType.INFORMATION,"Esito operazione", "Operazione fallita.", "Da decidere");
    }

    /**
     * @brief Callback invocata in caso di successo di un'operazione I/O.
     * @details Implementazione dell'interfaccia {@link ResultActions}. Mostra un alert di conferma.
     * @post Viene mostrato un allert di successo.
     */
    @Override
    public void success() {
        showAlert(Alert.AlertType.INFORMATION,"Esito operazione", "Operazione completata con successo!", "La Libreria è stata correttamente salvata.");
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());
        alert.showAndWait();
    }

}
