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
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @class LibraryManager
 * @brief Classe principale che avvia l'applicazione JavaFX.
 * @details Estende la classe {@link Application}. Si occupa di inizializzare lo Stage primario,
 *          caricare la scena iniziale (MainView) e gestire il caricamento delle risorse FXML.
 */
public class LibraryManager extends Application {

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
        stage.setMinWidth(640);
        stage.setMinHeight(480);
        stage.setTitle("Library Manager");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("assets/logo.png")));
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManager.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
