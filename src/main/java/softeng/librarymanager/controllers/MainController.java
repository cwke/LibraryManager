/**
 * @file MainController.java
 * @brief Controller principale dell'applicazione (Root Controller).
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    /**
     * @brief Istanza del modello dati principale (Facade).
     */
    private Library library;

    /**
     * @brief Tab per il catalogo libri.
     */
    @FXML
    private javafx.scene.control.Tab bookTab;

    /**
     * @brief Tab per il registro studenti.
     */
    @FXML
    private javafx.scene.control.Tab studentTab;

    /**
     * @brief Tab per il registro prestiti.
     */
    @FXML
    private javafx.scene.control.Tab loanTab;

    /**
     * @brief Controller innestato per la barra dei menu superiore.
     * @details Iniettato automaticamente tramite tag <fx:include> nell'FXML.
     */
    @FXML
    private MenuBarController menuBarController;

    /**
     * @brief Metodo di inizializzazione del controller principale.
     * @details Viene chiamato automaticamente da JavaFX dopo il caricamento dell'FXML.
     *          Si occupa di caricare le viste nei rispettivi tab iniettando le dipendenze
     *          necessarie tramite ControllerFactory.
     */
    @FXML
    public void initialize() {
        library = new Library();
        
        // CODICE DI TEST, RIMUOVERE PRIMA DEL COMMIT!! >>
            for (int i = 0; i< 10; i++) {
                softeng.librarymanager.models.Book b = new softeng.librarymanager.models.Book("Titolo " + i, "Autore " + i, String.format("%013d", i), 2000, 1);
                softeng.librarymanager.models.Student s = new softeng.librarymanager.models.Student("Nome " + i, "Cognome " + i, String.format("%010d", i), i+"@studenti.unisa.it");
                softeng.librarymanager.models.Loan l = new softeng.librarymanager.models.Loan(s, b, LocalDate.now().plusMonths(i));
                library.getBookRegister().add(b);
                library.getStudentRegister().add(s);
                library.getLoanRegister().add(l);
            }
        // <<
        
        try {
            // Carica la Book Tab
            FXMLLoader bookLoader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/BookRegisterView.fxml"));
            bookLoader.setController(new BookRegisterController(library.getBookRegister()));
            bookTab.setContent(bookLoader.load());

            // Carica la Student Tab
            FXMLLoader studentLoader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/StudentRegisterView.fxml"));
            studentLoader.setController(new StudentRegisterController(library.getStudentRegister()));
            studentTab.setContent(studentLoader.load());

            // Carica la Loan tab
            FXMLLoader loanLoader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/LoanRegisterView.fxml"));
            loanLoader.setController(new LoanRegisterController(library));
            loanTab.setContent(loanLoader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
