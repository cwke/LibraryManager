/**
 * @file BookInsertPopupController.java
 * @brief Controller per la finestra di dialogo di inserimento nuovo libro.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.RegisterAdder;
import softeng.librarymanager.models.RegisterValidator;

/**
 * @class BookInsertPopupController
 * @brief Classe controller per la gestione del popup di inserimento libri.
 * @details Estende {@link BookPopupController} ereditando l'interfaccia grafica comune.
 *          Implementa la logica specifica per creare un nuovo libro e aggiungerlo al registro
 *          utilizzando {@link RegisterAdder}.
 */
public class BookInsertPopupController extends BookPopupController {

    /**
     * @brief Interfaccia funzionale per l'operazione di aggiunta.
     */
    private RegisterAdder<Book> bookRegisterAdder;

    /**
     * @brief Interfaccia funzionale per la validazione dei dati.
     */
    private RegisterValidator<Book> bookRegisterValidator;

    public BookInsertPopupController(RegisterAdder<Book> bookRegisterAdder, RegisterValidator<Book> bookRegisterValidator) {
        this.bookRegisterAdder = bookRegisterAdder;
        this.bookRegisterValidator = bookRegisterValidator;
    }

    /**
     * @brief Inizializza il controller.
     * @details Richiama l'inizializzazione della superclasse.
     */
    @Override
    @FXML
    public void initialize() {
        super.initialize();
    }

    /**
     * @brief Gestisce l'azione di conferma per l'inserimento.
     * @details Raccoglie i dati dai campi, valida il nuovo oggetto e lo aggiunge.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(ActionEvent event) {
        // Implementazione specifica per l'aggiunta
    }
}