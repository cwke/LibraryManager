/**
 * @file StudentRegisterController.java
 * @brief Controller principale per la vista di gestione del registro degli studenti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.student;

import javafx.beans.binding.Binding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.io.IOException;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import softeng.librarymanager.models.Register;
import softeng.librarymanager.models.Student;
import softeng.librarymanager.controllers.SideBarController;

/**
 * @class StudentRegisterController
 * @brief Classe controller per la gestione dell'interfaccia utente del registro studenti.
 * @details Questa classe gestisce la visualizzazione tabellare degli studenti, l'interazione
 *          con la barra laterale (SideBar) e coordina l'apertura dei popup per l'inserimento
 *          e la modifica dei dati anagrafici.
 *          Si occupa inoltre di collegare la vista al modello dati {@link Register}<Student>.
 */
public class StudentRegisterController {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> nameClm;
    @FXML
    private TableColumn<Student, String> surnameClm;
    @FXML
    private TableColumn<Student, String> studentIdClm;
    @FXML
    private TableColumn<Student, String> emailClm;
    @FXML
    private TableColumn<Student, Integer> activeLoansCountClm;
    @FXML
    private SideBarController sideBarController;
    
    private final Register<Student> studentRegister;
    
    /**
     * @brief Costruttore del controller.
     * @param[in] studentRegister L'istanza del registro studenti.
     */
    public StudentRegisterController(Register<Student> studentRegister) {
        this.studentRegister = studentRegister;
    }

    /**
     * @brief Metodo di inizializzazione del controller JavaFX.
     * @details Viene chiamato automaticamente da JavaFX dopo il caricamento dell'FXML.
     *          Inizializza la tableview, configura i bindings e listeners.
     */
    @FXML
    public void initialize() {
        // Inizializza la tabella
        updateTableView();

        // Configurazione Colonne: Usa SimpleStringProperty per avvolgere i getter della classe POJO Book
        nameClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getName()));
        surnameClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getSurname()));
        studentIdClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getStudentId()));
        emailClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getEmail()));
        activeLoansCountClm.setCellValueFactory(row -> new SimpleIntegerProperty(row.getValue().getActiveLoans().size()).asObject());

        // Sidebar Event Listeners
        sideBarController.getAddBtn().setOnAction(event -> openInsertPopup());
        sideBarController.getModifyBtn().setOnAction(event -> openModifyPopup());
        sideBarController.getRemoveBtn().setOnAction(event -> removeFromRegister());

        // Listener per la barra di ricerca
        sideBarController.getSearchBarTF().textProperty().addListener((observable, oldValue, newValue) -> searchBook());

        // Binding: Disabilita i tasti Modifica e Rimuovi se non è selezionata una riga
        Binding<Boolean> noItemSelectedBinding = studentTable.getSelectionModel().selectedItemProperty().isNull();
        sideBarController.getRemoveBtn().disableProperty().bind(noItemSelectedBinding);
        sideBarController.getModifyBtn().disableProperty().bind(noItemSelectedBinding);
    }

    private void openInsertPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/StudentPopupView.fxml"));

            loader.setController(new StudentInsertPopupController(
                    (Student toAdd) -> studentRegister.add(toAdd),
                    (Student toVerify) -> studentRegister.isUnique(toVerify)));

            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);

            // CSS
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

            Stage popup = new Stage();
            popup.setMinWidth(640);
            popup.setMinHeight(480);
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

    private void openModifyPopup() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null)
            return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/StudentPopupView.fxml"));

            loader.setController(new StudentModifyPopupController(
                    (Student old, Student newObj) -> studentRegister.modify(old, newObj),
                    selectedStudent));

            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);

            // CSS
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

            Stage popup = new Stage();
            popup.setMinWidth(640);
            popup.setMinHeight(480);
            popup.setTitle("Modifica Studente");
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Aggiorna la tabella alla chiusura del popup
        updateTableView();
    }

    private void removeFromRegister() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma rimozione");
        alert.setHeaderText("Sei sicuro di voler rimuovere lo studente '" + selectedStudent.getName() + " " + selectedStudent.getSurname() + "'?");
        alert.setContentText("L'operazione è irreversibile.");
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets()
                .add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            studentRegister.remove(selectedStudent);
            updateTableView();
        }
    }

    /**
     * @brief Aggiorna la TableView con i dati attuali del registro.
     */
    public void updateTableView() {
        studentTable.setItems(FXCollections.observableArrayList(studentRegister.getRegisterList()));
        studentTable.refresh();
    }

    private void searchBook() {
        String searchText = sideBarController.getSearchBarTF().getText();

        // Se la ricerca è vuota, mostra tutto
        if (searchText == null || searchText.isEmpty()) {
            updateTableView();
            return;
        }

        ObservableList<Student> allStudents = FXCollections.observableArrayList(studentRegister.getRegisterList());
        String lowerCaseSearchText = searchText.toLowerCase();
        ObservableList<Student> filteredStudents = FXCollections.observableArrayList();

        for (Student student : allStudents) {
            String studentName = student.getName().toLowerCase();
            String studentSurname = student.getSurname().toLowerCase();
            String studentId = student.getStudentId();
            String fullName = studentName + " " + studentSurname;
            String fullNameReverse = studentSurname + " " + studentName;
                
           if (fullName.contains(lowerCaseSearchText) ||
                   fullNameReverse.contains(lowerCaseSearchText) ||
                   studentId.contains(lowerCaseSearchText)) {
               filteredStudents.add(student);
           }
        }
        studentTable.setItems(filteredStudents);
    }

}
