/**
 * @file LoanRegisterController.java
 * @brief Controller principale per la vista di gestione del registro dei prestiti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.Library;
import softeng.librarymanager.models.Loan;

import java.time.LocalDate;

/**
 * @class LoanRegisterController
 * @brief Classe controller per la gestione dell'interfaccia utente del registro prestiti.
 * @details Questa classe gestisce la visualizzazione tabellare dei prestiti, l'interazione
 *          con la barra laterale (SideBar) e coordina l'apertura dei popup per la creazione
 *          e la modifica dei prestiti.
 *          Riceve in iniezione l'oggetto principale {@link Library} per accedere ai dati necessari.
 */
public class LoanRegisterController {

    /**
     * @brief Tabella per la visualizzazione dell'elenco dei prestiti.
     */
    @FXML
    private TableView<Loan> loanTable;

    /**
     * @brief Colonna della tabella per il nome dello studente (o identificativo).
     */
    @FXML
    private TableColumn<Loan, String> studentClm;

    /**
     * @brief Colonna della tabella per il titolo del libro (o identificativo).
     */
    @FXML
    private TableColumn<Loan, String> bookClm;

    /**
     * @brief Colonna della tabella per la data di fine prestito (scadenza).
     */
    @FXML
    private TableColumn<Loan, LocalDate> loanEndClm;

    /**
     * @brief Colonna della tabella per lo stato di restituzione.
     */
    @FXML
    private TableColumn<Loan, String> returnedClm;

    /**
     * @brief Riferimento all'oggetto principale Library.
     * @details Necessario per accedere trasversalmente ai registri (Prestiti, ma anche Libri e Studenti).
     */
    private Library library;

    /**
     * @brief Riferimento al controller della barra laterale.
     */
    @FXML
    private SideBarController sideBarController;

    /**
     * @brief Controller per il popup di inserimento prestito.
     */
    private LoanInsertPopupController loanInsertPopupController;

    /**
     * @brief Controller per il popup di modifica prestito.
     */
    private LoanModifyPopupController loanModifyPopupController;
    
    /**
     * @brief Costruttore del controller.
     * @param[in] library L'istanza principale della biblioteca.
     */
    public LoanRegisterController(Library library) {
        this.library = library;
    }


    /**
     * @brief Metodo di inizializzazione del controller JavaFX.
     * @details Configura le colonne della tabella e associa i listener agli eventi
     *          dei bottoni della SideBar (Aggiungi, Modifica, Rimuovi).
     *
     */
    @FXML
    public void initialize() {
    }

    /**
     * @brief Apre il popup per la creazione di un nuovo prestito.
     * @details Invocato alla pressione del tasto "Aggiungi" nella SideBar.
     */
    private void openInsertPopup() {
    }

    /**
     * @brief Apre il popup per la modifica del prestito selezionato.
     * @details Invocato alla pressione del tasto "Modifica" nella SideBar.
     *          Recupera l'elemento selezionato nella TableView e lo passa al popup.
     */
    private void openModifyPopup() {
    }

    /**
     * @brief Rimuove il prestito selezionato dal registro.
     * @details Invocato alla pressione del tasto "Rimuovi" nella SideBar.
     */
    private void removeFromRegister() {
    }


}
