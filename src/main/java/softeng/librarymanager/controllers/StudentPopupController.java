/**
 * @file StudentPopupController.java
 * @brief Controller astratto base per i popup di gestione degli studenti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**s
 * @class StudentPopupController
 * @brief Classe base astratta per i controller dei popup studenti (Inserimento e Modifica).
 * @details Raccoglie i componenti grafici comuni (TextField per l'anagrafica) e gestisce
 *          la logica di base condivisa dalle finestre popup di dialogo degli studenti.
 */
public abstract class StudentPopupController {

    /**
     * @brief Campo di testo per il nome dello studente.
     */
    @FXML
    protected TextField nameTF;

    /**
     * @brief Campo di testo per il cognome dello studente.
     */
    @FXML
    protected TextField surnameTF;

    /**
     * @brief Campo di testo per la matricola (ID) dello studente.
     */
    @FXML
    protected TextField studentIdTF;

    /**
     * @brief Campo di testo per l'email dello studente.
     */
    @FXML
    protected TextField emailTF;

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
     * @details Metodo chiamato automaticamente da JavaFX.
     */
    @FXML
    public void initialize() {
    }

    /**
     * @brief Gestisce l'evento di click sul bottone di conferma.
     * @details Metodo astratto da implementare nelle sottoclassi per definire
     *          la logica specifica (Inserimento o Modifica).
     * @param[in] event L'evento generato dal click.
     */
    @FXML
    public abstract void confirmBtnAction(ActionEvent event);

    /**
     * @brief Gestisce l'evento di click sul bottone di annullamento.
     * @details Chiude la finestra corrente.
     * @param[in] event L'evento generato dal click.
     */
    @FXML
    public void cancelBtnAction(ActionEvent event) {
    }

}
