/**
 * @file BookPopupController.java
 * @brief Controller astratto base per i popup di gestione libri.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import softeng.librarymanager.models.Book;

/**
 * @class BookPopupController
 * @brief Classe base astratta per i controller dei popup (Inserimento e Modifica).
 * @details Raccoglie i componenti grafici comuni (TextField, Button) definiti nel diagramma
 *          e gestisce la logica di base condivisa dalle finestre popup di dialogo dei libri.
 */
public abstract class BookPopupController {

    /**
     * @brief Campo di testo per il titolo del libro.
     */
    @FXML
    protected TextField titleTF;

    /**
     * @brief Campo di testo per gli autori del libro.
     */
    @FXML
    protected TextField authorsTF;

    /**
     * @brief Campo di testo per l'anno di pubblicazione.
     */
    @FXML
    protected TextField publishmentYearTF;

    /**
     * @brief Campo di testo per il numero di copie disponibili.
     */
    @FXML
    protected TextField availableCopiesTF;

    /**
     * @brief Campo di testo per l'ID (ISBN) del libro.
     */
    @FXML
    protected TextField bookIdTF;

    /**
     * @brief Bottone di conferma (Salva/Aggiungi).
     */
    @FXML
    protected Button confirmBtn;

    /**
     * @brief Bottone di annullamento.
     */
    @FXML
    protected Button cancelBtn;

    /**
     * @brief Inizializzazione base del controller.
     * @details Metodo chiamato automaticamente da JavaFX dopo il caricamento dell'FXML.
     */
    @FXML
    public void initialize() {
    }

    /**
     * @brief Gestisce l'evento di click sul bottone di conferma.
     * @details Metodo astratto o base da implementare nelle sottoclassi per definire
     *          la logica specifica (Inserimento o Modifica).
     * @param[in] event L'evento generato dal click.
     */
    @FXML
    public abstract void confirmBtnAction(ActionEvent event);

    /**
     * @brief Gestisce l'evento di click sul bottone di annullamento.
     * @details Chiude la finestra corrente senza salvare le modifiche.
     * @param[in] event L'evento generato dal click.
     */
    @FXML
    public void cancelBtnAction(ActionEvent event) {
    }
}