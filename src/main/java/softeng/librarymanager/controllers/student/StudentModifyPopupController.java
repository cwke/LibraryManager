/**
 * @file StudentModifyPopupController.java
 * @brief Controller per la finestra di dialogo di modifica di uno studente.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.student;

import javafx.fxml.FXML;
import softeng.librarymanager.models.RegisterModifier;
import softeng.librarymanager.models.RegisterValidator;
import softeng.librarymanager.models.Student;

/**
 * @class StudentModifyPopupController
 * @brief Classe controller per la gestione del popup di modifica studenti.
 * @details Estende {@link StudentPopupController}. Gestisce il popolamento dei campi
 *          con i dati dello studente esistente e l'aggiornamento nel registro tramite
 *          {@link RegisterModifier}.
 */
public class StudentModifyPopupController extends StudentPopupController {

    /**
     * @brief Interfaccia funzionale per l'operazione di modifica.
     */
    private RegisterModifier<Student> studentRegisterModifier;

    /**
     * @brief Interfaccia funzionale per la validazione dei dati.
     */
    private RegisterValidator<Student> studentRegisterValidator;

    /**
     * @brief Riferimento all'oggetto Student originale da modificare.
     */
    private Student studentToModify;

    public StudentModifyPopupController(RegisterModifier<Student> studentRegisterModifier, RegisterValidator<Student> studentRegisterValidator, Student studentToModify) {
        this.studentRegisterModifier = studentRegisterModifier;
        this.studentRegisterValidator = studentRegisterValidator;
        this.studentToModify = studentToModify;
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
     * @brief Imposta lo studente da modificare e popola i campi della GUI.
     * @param[in] studentToModify L'istanza dello studente da modificare.
     */
    public void setStudentToModify(Student studentToModify) {
        this.studentToModify = studentToModify;
    }

    /**
     * @brief Imposta il delegato per la modifica nel registro.
     * @param[in] studentRegisterModifier L'istanza che implementa RegisterModifier<Student>.
     */
    public void setStudentRegisterModifier(RegisterModifier<Student> studentRegisterModifier) {
        this.studentRegisterModifier = studentRegisterModifier;
    }

    /**
     * @brief Imposta il delegato per la validazione.
     * @param[in] studentRegisterValidator L'istanza che implementa RegisterValidator<Student>.
     */
    public void setStudentRegisterValidator(RegisterValidator<Student> studentRegisterValidator) {
        this.studentRegisterValidator = studentRegisterValidator;
    }

    /**
     * @brief Gestisce l'azione di conferma per la modifica.
     * @details Raccoglie i dati modificati, valida l'oggetto aggiornato e applica le modifiche.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(javafx.event.ActionEvent event) {
        // Implementazione specifica per la modifica dello studente
    }
}