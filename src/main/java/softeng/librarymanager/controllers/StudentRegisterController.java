/**
 * @file StudentRegisterController.java
 * @brief Controller principale per la vista di gestione del registro degli studenti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import javafx.beans.binding.Binding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.Register;
import softeng.librarymanager.models.Student;

import java.io.IOException;
import java.util.Optional;

/**
 * @class StudentRegisterController
 * @brief Classe controller per la gestione dell'interfaccia utente del registro studenti.
 * @details Questa classe gestisce la visualizzazione tabellare degli studenti, l'interazione
 *          con la barra laterale (SideBar) e coordina l'apertura dei popup per l'inserimento
 *          e la modifica dei dati anagrafici.
 *          Si occupa inoltre di collegare la vista al modello dati {@link Register}<Student>.
 */
public class StudentRegisterController {

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> nameClm;
    @FXML private TableColumn<Student, String> surnameClm;
    @FXML private TableColumn<Student, String> studentIdClm;
    @FXML private TableColumn<Student, String> emailClm;

    private Register<Student> studentRegister;

    @FXML private SideBarController sideBarController;

    private StudentInsertPopupController studentInsertPopupController;
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
        // Inizializza la tabella
        updateTableView();

        // Configurazione Colonne: Usa SimpleStringProperty per avvolgere i getter della classe POJO Book
        nameClm.setCellValueFactory( row -> new SimpleStringProperty(row.getValue().getName()));
        surnameClm.setCellValueFactory( row -> new SimpleStringProperty(row.getValue().getSurname()));
        studentIdClm.setCellValueFactory( row -> new SimpleStringProperty(row.getValue().getStudentId()));
        emailClm.setCellValueFactory( row -> new SimpleStringProperty(row.getValue().getEmail()));

        // Sidebar Event Listeners
        sideBarController.getAddBtn().setOnAction(event -> openInsertPopup());
        sideBarController.getModifyBtn().setOnAction(event -> openModifyPopup());
        sideBarController.getRemoveBtn().setOnAction(event -> removeFromRegister());

        // Binding: Disabilita i tasti Modifica e Rimuovi se non è selezionata una riga
        Binding<Boolean> noItemSelectedBinding = studentTable.getSelectionModel().selectedItemProperty().isNull();
        sideBarController.getRemoveBtn().disableProperty().bind(noItemSelectedBinding);
        sideBarController.getModifyBtn().disableProperty().bind(noItemSelectedBinding);
    }

    /**
     * @brief Apre il popup per l'inserimento di un nuovo studente.
     * @details Invocato alla pressione del tasto "Aggiungi" nella SideBar.
     */
    private void openInsertPopup() {
        try {
            // Usa il percorso assoluto che avevi nella branch per sicurezza
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/StudentPopupView.fxml"));

            // Passa le lambda expression per le callback (add e isValid)
            loader.setController(new StudentInsertPopupController(
                    (Student toAdd) -> studentRegister.add(toAdd),
                    (Student toVerify) -> studentRegister.isUnique(toVerify)
            ));

            Parent root = loader.load();
            Scene scene = new Scene(root, 480, 720);

            // CSS
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

            Stage popup = new Stage();
            popup.setTitle("Inserimento Studente");
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Aggiorna la tabella alla chiusura del popup
        updateTableView();
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
     */
    private void removeFromRegister() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma rimozione");
        alert.setHeaderText("Sei sicuro di voler rimuovere lo studente '" + selectedStudent.getName() + " " + selectedStudent.getSurname() + "'?");
        alert.setContentText("L'operazione è irreversibile.");
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            studentRegister.remove(selectedStudent);
            updateTableView();
        }
    }

    /**
     * @brief Aggiorna la TableView con i dati attuali del registro.
     */
    private void updateTableView() {
        studentTable.setItems(FXCollections.observableArrayList(studentRegister.getRegisterList()));
    }

}
