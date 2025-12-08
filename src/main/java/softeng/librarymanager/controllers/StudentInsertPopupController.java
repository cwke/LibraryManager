/**
 * @file StudentInsertPopupController.java
 * @brief Controller per la finestra di dialogo di inserimento di un nuovo studente.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.fxml.FXML;
import softeng.librarymanager.models.RegisterAdder;
import softeng.librarymanager.models.RegisterValidator;
import softeng.librarymanager.models.Student;

/**
 * @class StudentInsertPopupController
 * @brief Classe controller per la gestione del popup di inserimento studenti.
 * @details Estende {@link StudentPopupController} ereditando l'interfaccia grafica.
 *          Implementa la logica per creare un nuovo oggetto Student e aggiungerlo al registro
 *          tramite l'interfaccia {@link RegisterAdder}.
 */
public class StudentInsertPopupController extends StudentPopupController {

    /**
     * @brief Interfaccia funzionale per l'operazione di aggiunta al registro studenti.
     */
    private RegisterAdder<Student> studentRegisterAdder;

    /**
     * @brief Interfaccia funzionale per la validazione dei dati studente.
     */
    private RegisterValidator<Student> studentRegisterValidator;

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
     * @brief Imposta il delegato per l'aggiunta al registro.
     * @param[in] studentRegisterAdder L'istanza che implementa RegisterAdder<Student>.
     */
    public void setStudentRegisterAdder(RegisterAdder<Student> studentRegisterAdder) {
        this.studentRegisterAdder = studentRegisterAdder;
    }

    /**
     * @brief Imposta il delegato per la validazione.
     * @param[in] studentRegisterValidator L'istanza che implementa RegisterValidator<Student>.
     */
    public void setStudentRegisterValidator(RegisterValidator<Student> studentRegisterValidator) {
        this.studentRegisterValidator = studentRegisterValidator;
    }

    /**
     * @brief Gestisce l'azione di conferma per l'inserimento.
     * @details Raccoglie i dati dai campi, valida il nuovo oggetto e lo aggiunge.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(javafx.event.ActionEvent event) {
        // Implementazione specifica per l'aggiunta di uno studente
    }
}