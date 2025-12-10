/**
 * @file LibraryManager.java
 * @brief Classe principale (Entry Point) dell'applicazione Library Manager.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager
 */

package softeng.librarymanager;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @class LibraryManager
 * @brief Classe principale che avvia l'applicazione JavaFX.
 * @details Estende la classe {@link Application}. Si occupa di inizializzare lo Stage primario,
 *          caricare la scena iniziale (MainView) e gestire il caricamento delle risorse FXML.
 */
public class LibraryManager extends Application {

    /**
     * @brief Riferimento statico alla scena principale dell'applicazione.
     */
    private static Scene scene;

    /**
     * @brief Metodo di avvio dell'applicazione JavaFX.
     * @details Configura lo Stage primario, imposta le dimensioni iniziali e minime della finestra,
     *          e carica la vista principale ("MainView").
     * @param[in] stage Lo stage primario fornito dal runtime JavaFX.
     * @throws IOException Se il caricamento del file FXML fallisce.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainView"), 1280, 720);
        String cssPath = getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        stage.setScene(scene);
        stage.setMinWidth(620);
        stage.setMinHeight(480);
        stage.show();
    }

    /**
     * @brief Cambia il nodo radice della scena corrente.
     * @details Metodo di utilità per navigare tra diverse viste (View Switching)
     *          senza chiudere la finestra principale.
     * @param[in] fxml Il nome del file FXML (senza estensione) da caricare come nuova radice.
     * @throws IOException Se il file FXML specificato non viene trovato o non può essere caricato.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * @brief Carica un file FXML e restituisce il nodo genitore (Parent).
     * @details Cerca il file nella directory delle risorse "fxml/".
     * @param[in] fxml Il nome del file FXML da caricare.
     * @return Parent Il nodo radice della gerarchia di oggetti caricata dal file FXML.
     * @throws IOException Se si verifica un errore di I/O durante il caricamento.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManager.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * @brief Punto di ingresso principale (Main) per l'applicazione Java.
     * @details Lancia il runtime JavaFX chiamando il metodo launch().
     * @param[in] args Argomenti da riga di comando passati all'applicazione.
     */
    public static void main(String[] args) {
        launch();
    }

}
