/**
 * @file MenuBarController.java
 * @brief Controller per la gestione della barra dei menu (File menu).
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import softeng.librarymanager.models.Library;
import softeng.librarymanager.models.LibraryIOManager;

/**
 * @class MenuBarController
 * @brief Classe controller per la gestione delle operazioni globali da menu.
 * @details Gestisce le azioni della barra dei menu, come il salvataggio su file,
 *          il caricamento di un file e la chiusura di un file, delegando
 *          le operazioni di I/O alla classe {@link LibraryIOManager}.
 */
public class MenuBarController {

    /**
     * @brief Gestore delle operazioni di Input/Output.
     */
    private LibraryIOManager libraryIOManager;

    /**
     * @brief Riferimento al modello dati principale.
     * @details Necessario per passare i dati da salvare o per aggiornare il modello
     *          dopo un caricamento da file.
     */
    private Library library;

    /**
     * @brief Imposta l'istanza della Library su cui operare.
     * @param[in] library L'istanza della biblioteca.
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * @brief Gestisce l'apertura di un file di dati esistente.
     * @details Apre un FileChooser e delega il caricamento al LibraryIOManager.
     * @param[in] event L'evento scatenato dal click sulla voce di menu "Open" (o simile).
     */
    @FXML
    public void openFile(ActionEvent event) {
    }

    /**
     * @brief Gestisce la chiusura del file in lettura.
     * @details Includere controlli per salvare le modifiche non salvate prima di "scollegarsi".
     * @param[in] event L'evento scatenato dal click sulla voce di menu "Close".
     */
    @FXML
    public void closeFile(ActionEvent event) {
    }

    /**
     * @brief Gestisce il salvataggio dello stato corrente su file.
     * @details Delega la scrittura dei dati correnti della Library al LibraryIOManager.
     * @param[in] event L'evento scatenato dal click sulla voce di menu "Save".
     */
    @FXML
    public void saveFile(ActionEvent event) {
    }

}
