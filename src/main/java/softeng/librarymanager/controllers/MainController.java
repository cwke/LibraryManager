/**
 * @file MainController.java
 * @brief Controller principale dell'applicazione (Root Controller).
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import softeng.librarymanager.controllers.student.StudentRegisterController;
import softeng.librarymanager.controllers.loan.LoanRegisterController;
import softeng.librarymanager.controllers.book.BookRegisterController;
import java.io.IOException;
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
public class MainController implements Refresh {

    @FXML
    private javafx.scene.control.Tab bookTab;
    @FXML
    private javafx.scene.control.Tab studentTab;
    @FXML
    private javafx.scene.control.Tab loanTab;
    @FXML
    private MenuBarController menuBarController;

    private Library library;

    /**
     * @brief Metodo di inizializzazione del controller principale.
     * @details Viene chiamato automaticamente da JavaFX dopo il caricamento dell'FXML.
     *          Si occupa di caricare le viste nei rispettivi tab iniettando le dipendenze
     *          necessarie tramite ControllerFactory.
     */
    @FXML
    public void initialize() {
        menuBarController.setMainRefresher(this);

        library = new Library();
        menuBarController.setLibrary(library);

        initializeRegisterControllers(library);
    }

    private void initializeRegisterControllers(Library library){
        try {
            // Carica la Book Tab
            FXMLLoader bookLoader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/BookRegisterView.fxml"));
            BookRegisterController bookRegisterController = new BookRegisterController(library.getBookRegister());
            bookLoader.setController(bookRegisterController);
            bookTab.setContent(bookLoader.load());

            bookTab.setOnSelectionChanged(event -> {
                if (bookTab.isSelected()) {
                    bookRegisterController.updateTableView();
                }
            });

            // Carica la Student Tab
            FXMLLoader studentLoader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/StudentRegisterView.fxml"));
            StudentRegisterController studentRegisterController = new StudentRegisterController(library.getStudentRegister());
            studentLoader.setController(studentRegisterController);
            studentTab.setContent(studentLoader.load());

            studentTab.setOnSelectionChanged(event -> {
                if (studentTab.isSelected()) {
                    studentRegisterController.updateTableView();
                }
            });

            // Carica la Loan tab
            FXMLLoader loanLoader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/LoanRegisterView.fxml"));
            loanLoader.setController(new LoanRegisterController(library));
            loanTab.setContent(loanLoader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Aggiorna l'istanza della libreria gestita dal controller.
     * @details Implementazione dell'interfaccia {@link Refresh}. Questo metodo viene chiamato
     *          quando l'oggetto {@link Library} principale viene sostituito (es. dopo il
     *          caricamento da file), propagando la nuova istanza a tutti i sotto-controller delle tab.
     * @param[i] newLibrary La nuova istanza di Library da utilizzare.
     */
    @Override
    public void refresh(Library newLibrary) {
        initializeRegisterControllers(newLibrary);
    }

}
