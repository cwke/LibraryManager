/**
 * @file MenuBarController.java
 * @brief Controller Menu Semplificato.
 */
package softeng.librarymanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import softeng.librarymanager.controllers.student.Refresh;
import softeng.librarymanager.controllers.student.ResultActions;
import softeng.librarymanager.models.Library;
import softeng.librarymanager.models.LibraryIOManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.File;

public class MenuBarController implements ResultActions {

    @FXML
    private MenuBar menuBar;

    private LibraryIOManager libraryIOManager;
    private String defaultSavePath;
    private Refresh mainRefresher;
    private Library library;

    @FXML
    public void initialize() {
        this.libraryIOManager = new LibraryIOManager(this);
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setMainRefresher(Refresh mainRefresher){
        this.mainRefresher = mainRefresher;
    }

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

    @FXML
    public void closeFile(ActionEvent event) {
        this.defaultSavePath = null;
        this.library = new Library();
        if (mainRefresher != null) mainRefresher.refresh(this.library);
    }

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

    @Override
    public void failure() {
        showAlert(Alert.AlertType.INFORMATION,"Esito operazione", "Operazione fallita.", "Da decidere");
    }

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