/**
 * @file BookModifyPopupController.java
 * @brief Controller per la finestra di dialogo di modifica libro.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.fxml.FXML;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.RegisterModifier;
import softeng.librarymanager.models.RegisterValidator;

/**
 * @class BookModifyPopupController
 * @brief Classe controller per la gestione del popup di modifica libri.
 * @details Estende {@link BookPopupController}. Gestisce il popolamento dei campi
 *          con i dati esistenti e l'aggiornamento del libro nel registro tramite
 *          {@link RegisterModifier}.
 */
public class BookModifyPopupController extends BookPopupController {

    /**
     * @brief Interfaccia funzionale per l'operazione di modifica.
     */
    private RegisterModifier<Book> bookRegisterModifier;

    /**
     * @brief Interfaccia funzionale per la validazione dei dati.
     */
    private RegisterValidator<Book> bookRegisterValidator;

    /**
     * @brief Riferimento all'oggetto Book originale da modificare.
     */
    private Book bookToModify;

    /**
     * @brief Inizializza il controller.
     * @details Richiama l'inizializzazione della superclasse.
     */
    
    
    public BookModifyPopupController(RegisterModifier<Book> bookRegisterModifier, RegisterValidator<Book> bookRegisterValidator, Book bookToModify) {
        this.bookRegisterModifier = bookRegisterModifier;
        this.bookRegisterValidator = bookRegisterValidator;
        this.bookToModify = bookToModify;
    }

    @Override
    @FXML
    public void initialize() {
        super.initialize();
    }

    /**
     * @brief Imposta il libro da modificare e popola i campi della GUI.
     * @param[in] bookToModify L'istanza del libro da modificare.
     */
    public void setBookToModify(Book bookToModify) {
        this.bookToModify = bookToModify;
    }

    /**
     * @brief Imposta il delegato per la modifica nel registro.
     * @param[in] bookRegisterModifier L'istanza che implementa RegisterModifier.
     */
    public void setBookRegisterModifier(RegisterModifier<Book> bookRegisterModifier) {
        this.bookRegisterModifier = bookRegisterModifier;
    }

    /**
     * @brief Imposta il delegato per la validazione.
     * @param[in] bookRegisterValidator L'istanza che implementa RegisterValidator.
     */
    public void setBookRegisterValidator(RegisterValidator<Book> bookRegisterValidator) {
        this.bookRegisterValidator = bookRegisterValidator;
    }

    /**
     * @brief Gestisce l'azione di conferma per la modifica.
     * @details Raccoglie i dati modificati, valida l'oggetto e applica le modifiche.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(javafx.event.ActionEvent event) {
        // Implementazione specifica per la modifica
    }
}