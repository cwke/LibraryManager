/**
 * @file LoanRegisterController.java
 * @brief Controller principale per la vista di gestione del registro dei prestiti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.loan;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.Library;
import softeng.librarymanager.models.Loan;
import java.time.LocalDate;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableRow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import softeng.librarymanager.controllers.SideBarController;

/**
 * @class LoanRegisterController
 * @brief Classe controller per la gestione dell'interfaccia utente del registro prestiti.
 * @details Questa classe gestisce la visualizzazione tabellare dei prestiti, l'interazione
 *          con la barra laterale (SideBar) e coordina l'apertura dei popup per la creazione
 *          e la modifica dei prestiti.
 *          Riceve in iniezione l'oggetto principale {@link Library} per accedere ai dati necessari.
 */
public class LoanRegisterController {

    @FXML 
    private TableView<Loan> loanTable;
    @FXML 
    private TableColumn<Loan, String> studentNameClm;
    @FXML 
    private TableColumn<Loan, String> studentSurnameClm;
    @FXML 
    private TableColumn<Loan, String> studentIdClm;
    @FXML 
    private TableColumn<Loan, String> bookNameClm;
    @FXML 
    private TableColumn<Loan, String> bookIdClm;
    @FXML 
    private TableColumn<Loan, LocalDate> loanEndClm;
    @FXML 
    private TableColumn<Loan, String> returnedClm;
    
    @FXML 
    private SideBarController sideBarController;
    
    private ComboBox<String> filterCB;

    private final Library library;
    
    /**
     * @brief Costruttore del controller.
     * @param[in] library L'istanza principale della biblioteca.
     */
    public LoanRegisterController(Library library) {
        this.library = library;
    }

    /**
     * @brief Metodo di inizializzazione del controller JavaFX.
     * @details Configura le colonne della tabella, e aggiunge i colori alle righe, 
     *          binding per disabilitare i pulsanti modifica e rimuovi e inizializza la ricerca.
     */
    @FXML
    public void initialize() {        
        // Configurazione dei colori per le righe delle tabelle se il prestito è restituito o in ritardo
        loanTable.setRowFactory(tableView -> new TableRow<Loan>() {
            @Override
            protected void updateItem(Loan loan, boolean empty){
                super.updateItem(loan, empty);
                if (loan == null || empty) {setStyle(""); }
                else if (loan.isReturned())
                    setStyle("-fx-background-color: #A1A1A1");
                else if (loan.isDelay())
                    setStyle("-fx-background-color: #FAA0A0");
                else
                    setStyle("");
            }
        });

        // Configurazione colonne;
        studentNameClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getStudent().getName()));
        studentSurnameClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getStudent().getSurname()));
        studentIdClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getStudent().getStudentId()));
        bookNameClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getBook().getTitle()));
        bookIdClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getBook().getBookId()));
        loanEndClm.setCellValueFactory(row -> new SimpleObjectProperty<LocalDate>(row.getValue().getLoanEnd()));
        returnedClm.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().isReturned() ? "Sì" : "No"));
    
        // Sidebar Event Listeners
        sideBarController.getAddBtn().setOnAction(event -> openInsertPopup());
        sideBarController.getModifyBtn().setOnAction(event -> openModifyPopup());
        sideBarController.getRemoveBtn().setOnAction(event -> removeFromRegister());
        
        // Listener per la ricerca
        sideBarController.getSearchBarTF().textProperty().addListener( (observable, oldValue, newValue) -> updateTableView());
        
        // Aggiunta del tasto restituisci
        Button returnBtn = sideBarController.createReturnBtn();
        returnBtn.setOnAction(event -> returnLoan());

        // Aggiunta combobox per filtrare
        this.filterCB = sideBarController.createReturnCB();
        this.filterCB.setOnAction(event -> updateTableView());
        
        // Binding: Disabilita il tasto returnBtn se la riga selezionata è restituita o non è selezionata
        BooleanBinding returnLoanBinding = Bindings.createBooleanBinding(() -> {
            Loan selected = loanTable.getSelectionModel().getSelectedItem();
            return selected == null || selected.isReturned();
        }, loanTable.getSelectionModel().selectedItemProperty());
        
        returnBtn.disableProperty().bind(returnLoanBinding);        
        
        // Binding: disabilita i tasti Modifica e Rimuovi se non è selezionata una riga
        Binding<Boolean> noItemSelectedBinding = loanTable.getSelectionModel().selectedItemProperty().isNull();
        sideBarController.getRemoveBtn().disableProperty().bind(noItemSelectedBinding);
        sideBarController.getModifyBtn().disableProperty().bind(noItemSelectedBinding);
        
        updateTableView();
    }

    private void openInsertPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/LoanPopupView.fxml"));
            
            loader.setController(new LoanInsertPopupController(
                (toAdd) -> library.getLoanRegister().add(toAdd),
                () -> library.getBookRegister().getRegisterList(),
                () -> library.getStudentRegister().getRegisterList(),
                (toVerify) -> library.getLoanRegister().isUnique(toVerify)
            ));
            
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);

            // CSS
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());
            
            Stage popup = new Stage();
            popup.setMinWidth(640);
            popup.setMinHeight(480);
            popup.setTitle("Inserimento Prestito");
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        updateTableView();
    }

    private void openModifyPopup() {
        Loan selectedLoan = loanTable.getSelectionModel().getSelectedItem();
        if (selectedLoan == null) return;
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/LoanPopupView.fxml"));
            
            loader.setController(new LoanModifyPopupController(
                    (old, newObj) -> library.getLoanRegister().modify(old, newObj),
                    selectedLoan
            ));
            
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);

            // CSS
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());
            
            Stage popup = new Stage();
            popup.setMinWidth(640);
            popup.setMinHeight(480);
            popup.setTitle("Modifica Prestito");
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        updateTableView();
    }

    private void removeFromRegister() {
        Loan selectedLoan = loanTable.getSelectionModel().getSelectedItem();
        if (selectedLoan == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma rimozione");
        alert.setHeaderText(
                "Sei sicuro di voler rimuovere il prestito di " + selectedLoan.getStudent().getSurname()
                + " " + selectedLoan.getStudent().getName()
        );
        alert.setContentText("L'operazione restituirà il prestito del libro " + selectedLoan.getBook().getTitle());
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            library.getLoanRegister().remove(selectedLoan);
            updateTableView();
        }
    }
    
    private void updateTableView() {
        // Recupero testo ricerca
        String searchText = sideBarController.getSearchBarTF().getText();
        String lowerCaseSearchText = searchText.toLowerCase();
        
        // Recupero selezione combobox
        String filterType = filterCB.getValue();
        
        ObservableList<Loan> allLoans = FXCollections.observableArrayList(library.getLoanRegister().getRegisterList());
        ObservableList<Loan> filteredLoans = FXCollections.observableArrayList();
        
        for (Loan loan : allLoans) {
            // Filtro per stato (Combobox)
            if (filterType.equals("Prestiti estinti") && !loan.isReturned()) continue;
            if (filterType.equals("Prestiti attivi") && loan.isReturned()) continue;
            
            // Filtro per ricerca (Search bar)
            if (lowerCaseSearchText.isEmpty()) {
                filteredLoans.add(loan);
                continue;
            }
            
            String studentName = loan.getStudent().getName().toLowerCase();
            String studentSurname = loan.getStudent().getSurname().toLowerCase();
            String studentId = loan.getStudent().getStudentId();
            String fullName = studentName + " " + studentSurname;
            String fullNameReverse = studentSurname + " " + studentName;
            
            if (fullName.contains(lowerCaseSearchText) ||
                fullNameReverse.contains(lowerCaseSearchText) ||
                studentId.contains(lowerCaseSearchText) ||
                loan.getBook().getTitle().toLowerCase().contains(lowerCaseSearchText) ||
                String.join(", ", loan.getBook().getAuthors()).toLowerCase().contains(lowerCaseSearchText) ||
                loan.getBook().getBookId().contains(lowerCaseSearchText)) {

                filteredLoans.add(loan);
            }
        }
        
        loanTable.setItems(filteredLoans);
        loanTable.refresh();
    }
    
    private void returnLoan() {
        Loan selectedLoan = loanTable.getSelectionModel().getSelectedItem();
        if (selectedLoan == null) return;
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Restituzione prestito");
        alert.setHeaderText("Sei sicuro di voler restituire il prestito?");
        alert.setContentText("L'operazione è irreversibile.");
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());
        Optional<ButtonType> result =  alert.showAndWait();
        
        if (result != null && result.get() == ButtonType.OK) {
            selectedLoan.returnLoan();
            updateTableView();
        }
    }
}
