/**
 * @file StudentRegisterController.java
 * @brief Controller principale per la vista di gestione del registro degli studenti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.Register;
import softeng.librarymanager.models.Student;

/**
 * @class StudentRegisterController
 * @brief Classe controller per la gestione dell'interfaccia utente del registro studenti.
 * @details Questa classe gestisce la visualizzazione tabellare degli studenti, l'interazione
 *          con la barra laterale (SideBar) e coordina l'apertura dei popup per l'inserimento
 *          e la modifica dei dati anagrafici.
 *          Si occupa inoltre di collegare la vista al modello dati {@link Register}<Student>.
 */
public class StudentRegisterController {

    /**
     * @brief Tabella per la visualizzazione dell'elenco degli studenti.
     */
    @FXML
    private TableView<Student> studentTable;

    /**
     * @brief Colonna della tabella per il nome dello studente.
     */
    @FXML
    private TableColumn<Student, String> nameClm;

    /**
     * @brief Colonna della tabella per il cognome dello studente.
     */
    @FXML
    private TableColumn<Student, String> surnameClm;

    /**
     * @brief Colonna della tabella per la matricola (ID) dello studente.
     */
    @FXML
    private TableColumn<Student, String> studentIdClm;

    /**
     * @brief Colonna della tabella per l'email dello studente.
     */
    @FXML
    private TableColumn<Student, String> emailClm;

    /**
     * @brief Riferimento al modello del registro studenti.
     */
    private Register<Student> studentRegister;

    /**
     * @brief Riferimento al controller della barra laterale (incluso via <fx:include>).
     */
    @FXML
    private SideBarController sideBarController;

    /**
     * @brief Controller per il popup di inserimento studente.
     */
    private StudentInsertPopupController studentInsertPopupController;

    /**
     * @brief Controller per il popup di modifica studente.
     */
    private StudentModifyPopupController studentModifyPopupController;
    
    /**
     * @brief Costruttore del controller.
     * @param[in] studentRegister L'istanza del registro studenti.
     */
    public StudentRegisterController(Register<Student> studentRegister) {
        this.studentRegister = studentRegister;
    }

    /**
     * @brief Metodo di inizializzazione del controller JavaFX.
     * @details Configura le colonne della tabella (binding con le proprietà di Student)
     *          e associa i listener agli eventi dei bottoni della SideBar (Aggiungi, Modifica, Rimuovi).
     */
    @FXML
    public void initialize() {
    }

    /**
     * @brief Apre il popup per l'inserimento di un nuovo studente.
     * @details Invocato alla pressione del tasto "Aggiungi" nella SideBar.
     */
    private void openInsertPopup() {
    }

    /**
     * @brief Apre il popup per la modifica dello studente selezionato.
     * @details Invocato alla pressione del tasto "Modifica" nella SideBar.
     *          Recupera l'elemento selezionato nella TableView e lo passa al popup.
     */
    private void openModifyPopup() {
    }

    /**
     * @brief Rimuove lo studente selezionato dal registro.
     * @details Invocato alla pressione del tasto "Rimuovi" nella SideBar.
     */
    private void removeFromRegister() {
    }



}
