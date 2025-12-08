/**
 * @file MainController.java
 * @brief Controller principale dell'applicazione (Root Controller).
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.fxml.FXML;
import softeng.librarymanager.models.Library;

/**
 * @class MainController
 * @brief Classe controller principale che coordina l'intera interfaccia utente.
 * @details Questa classe funge da punto di ingresso per la logica della GUI.
 *          È responsabile dell'inizializzazione dell'istanza principale di {@link Library}
 *          e della propagazione di tale istanza ai sotto-controller (Studenti, Libri, Prestiti, MenuBar)
 *          per garantire che tutti lavorino sugli stessi dati condivisi.
 */
public class MainController {

    /**
     * @brief Istanza del modello dati principale (Facade).
     */
    private Library library;

    /**
     * @brief Controller innestato per la gestione del registro studenti.
     * @details Iniettato automaticamente tramite tag <fx:include> nell'FXML.
     */
    @FXML
    private StudentRegisterController studentRegisterController;

    /**
     * @brief Controller innestato per la gestione del registro libri.
     * @details Iniettato automaticamente tramite tag <fx:include> nell'FXML.
     */
    @FXML
    private BookRegisterController bookRegisterController;

    /**
     * @brief Controller innestato per la gestione del registro prestiti.
     * @details Iniettato automaticamente tramite tag <fx:include> nell'FXML.
     */
    @FXML
    private LoanRegisterController loanRegisterController;

    /**
     * @brief Controller innestato per la barra dei menu superiore.
     * @details Iniettato automaticamente tramite tag <fx:include> nell'FXML.
     */
    @FXML
    private MenuBarController menuBarController;

    /**
     * @brief Metodo di inizializzazione del controller principale.
     * @details Viene chiamato automaticamente da JavaFX dopo il caricamento dell'FXML.
     *          Si occupa di istanziare la classe {@link Library} e di passarne il riferimento
     *          ai sotto-controller tramite i loro metodi setter specifici.
     */
    @FXML
    public void initialize() {
        // Logica di inizializzazione e propagazione dell'istanza Library
    }

}
