/**
 * @file StudentModifyPopupController.java
 * @brief Controller per la finestra di dialogo di modifica di uno studente.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.student;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import softeng.librarymanager.models.RegisterModifier;
import softeng.librarymanager.models.Student;

/**
 * @class StudentModifyPopupController
 * @brief Classe controller per la gestione del popup di modifica studenti.
 * @details Estende {@link StudentPopupController}. Gestisce il popolamento dei
 *          campi
 *          con i dati dello studente esistente e l'aggiornamento nel registro
 *          tramite
 *          {@link RegisterModifier}.
 */
public class StudentModifyPopupController extends StudentPopupController {

    private RegisterModifier<Student> studentRegisterModifier;
    private Student studentToModify;

    /**
     * @brief Costruttore.
     * @param[in] studentRegisterModifier L'istanza che implementa RegisterModifier<Student>.
     * @param[in] studentToModify Lo studente selezionato da modificare.
     */
    public StudentModifyPopupController(RegisterModifier<Student> studentRegisterModifier, Student studentToModify) {
        this.studentRegisterModifier = studentRegisterModifier;
        this.studentToModify = studentToModify;
    }

    /**
     * @brief Inizializza il controller.
     */
    @Override
    @FXML
    public void initialize() {
        super.initialize();

        // Placeholder campi
        if (studentToModify != null) {
            nameTF.setText(studentToModify.getName());
            surnameTF.setText(studentToModify.getSurname());
            studentIdTF.setText(studentToModify.getStudentId());
            emailTF.setText(studentToModify.getEmail());

            // Rendo non modificabile Matricola
            studentIdTF.setDisable(true);
        }
    }

    /**
     * @brief Gestisce l'azione di conferma per la modifica.
     * @details Raccoglie i dati modificati, valida l'oggetto aggiornato e applica
     *          le modifiche.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(javafx.event.ActionEvent event) {
        try {
            Student studentModified = new Student(nameTF.getText(), surnameTF.getText(), studentIdTF.getText(),
                    emailTF.getText());

            studentRegisterModifier.modify(studentToModify, studentModified);
            // Chiudi la finestra
            Stage stage = (Stage) confirmBtn.getScene().getWindow();
            stage.close();
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Errore", "Dati non validi", e.getMessage());
        }
    }
}