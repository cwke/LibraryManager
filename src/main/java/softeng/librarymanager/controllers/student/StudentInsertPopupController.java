/**
 * @file StudentInsertPopupController.java
 * @brief Controller per la finestra di dialogo di inserimento di un nuovo studente.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
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

    private final RegisterAdder<Student> studentRegisterAdder;
    private final RegisterValidator<Student> studentRegisterValidator;

    /**
     * @brief Costruttore.
     * @param[in] studentRegisterAdder L'istanza che implementa RegisterAdder<Student>.
     * @param[in] studentRegisterValidator L'istanza che implementa RegisterValidator<Student>.
     */
    public StudentInsertPopupController(RegisterAdder<Student> studentRegisterAdder, RegisterValidator<Student> studentRegisterValidator) {
        this.studentRegisterAdder = studentRegisterAdder;
        this.studentRegisterValidator = studentRegisterValidator;
    }

    /**
     * @brief Inizializza il controller.
     */
    @FXML
    public void initialize() {
        confirmBtn.disableProperty().bind(
                nameTF.textProperty().isEmpty()
                        .or(surnameTF.textProperty().isEmpty())
                        .or(studentIdTF.textProperty().isEmpty())
                        .or(emailTF.textProperty().isEmpty()));
    }

    /**
     * @brief Gestisce l'azione di conferma per l'inserimento.
     * @details Raccoglie i dati dai campi, valida il nuovo oggetto e lo aggiunge.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(ActionEvent event) {
        try {
            Student studentToAdd = new Student(nameTF.getText(), surnameTF.getText(), studentIdTF.getText(), emailTF.getText());

            if (studentRegisterValidator.isUnique(studentToAdd)) {
                studentRegisterAdder.add(studentToAdd);
                Stage stage = (Stage) confirmBtn.getScene().getWindow();
                stage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Errore", "Studente duplicato", "La matricola inserita è già presente.");
            }
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Errore", "Dati non validi", e.getMessage());
        }
    }

}
