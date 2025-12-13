/**
 * @file LoanRegisterController.java
 * @brief Controller principale per la vista di gestione del registro dei prestiti.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import softeng.librarymanager.models.Library;
import softeng.librarymanager.models.Loan;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.beans.binding.Binding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @class LoanRegisterController
 * @brief Classe controller per la gestione dell'interfaccia utente del registro prestiti.
 * @details Questa classe gestisce la visualizzazione tabellare dei prestiti, l'interazione
 *          con la barra laterale (SideBar) e coordina l'apertura dei popup per la creazione
 *          e la modifica dei prestiti.
 *          Riceve in iniezione l'oggetto principale {@link Library} per accedere ai dati necessari.
 */
public class LoanRegisterController {

    @FXML private TableView<Loan> loanTable;
    @FXML private TableColumn<Loan, String> studentNameClm;
    @FXML private TableColumn<Loan, String> studentSurnameClm;
    @FXML private TableColumn<Loan, String> studentIdClm;
    @FXML private TableColumn<Loan, String> bookNameClm;
    @FXML private TableColumn<Loan, String> bookIdClm;
    @FXML private TableColumn<Loan, LocalDate> loanEndClm;
    @FXML private TableColumn<Loan, String> returnedClm;

    /**
     * @brief Riferimento all'oggetto principale Library.
     * @details Necessario per accedere trasversalmente ai registri (Prestiti, ma anche Libri e Studenti).
     */
    private Library library;

    @FXML private SideBarController sideBarController;

    private LoanInsertPopupController loanInsertPopupController;
    private LoanModifyPopupController loanModifyPopupController;
    
    /**
     * @brief Costruttore del controller.
     * @param[in] library L'istanza principale della biblioteca.
     */
    public LoanRegisterController(Library library) {
        this.library = library;
    }


    /**
     * @brief Metodo di inizializzazione del controller JavaFX.
     * @details Configura le colonne della tabella e associa i listener agli eventi
     *          dei bottoni della SideBar (Aggiungi, Modifica, Rimuovi).
     *
     */
    @FXML
    public void initialize() {
        updateTableView();

        /*
        studentClm.setCellValueFactory(row -> {
            Student s = row.getValue().getStudent();
            return new SimpleStringProperty(s.getSurname() + " " + s.getName() + " | " + s.getStudentId());
        });
        
        bookClm.setCellValueFactory(row -> {
            Book b = row.getValue().getBook();
            return new SimpleStringProperty(b.getTitle() + " | " + b.getBookId());
        });
        */
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
        
        // Listener per la barra di ricerca
        sideBarController.getSearchBarTF().textProperty().addListener( (observable, oldValue, newValue) -> searchLoan());
        
        // Binding: disabilita i tasti Modifica e Rimuovi se non è selezionata una riga
        Binding<Boolean> noItemSelectedBinding = loanTable.getSelectionModel().selectedItemProperty().isNull();
        sideBarController.getRemoveBtn().disableProperty().bind(noItemSelectedBinding);
        sideBarController.getModifyBtn().disableProperty().bind(noItemSelectedBinding);
    }

    /**
     * @brief Apre il popup per la creazione di un nuovo prestito.
     * @details Invocato alla pressione del tasto "Aggiungi" nella SideBar.
     */
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
            Scene scene = new Scene(root, 480, 720);
            
            // CSS
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());
            
            Stage popup = new Stage();
            popup.setTitle("Inserimento Prestito");
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        updateTableView();
    }

    /**
     * @brief Apre il popup per la modifica del prestito selezionato.
     * @details Invocato alla pressione del tasto "Modifica" nella SideBar.
     *          Recupera l'elemento selezionato nella TableView e lo passa al popup.
     */
    private void openModifyPopup() {
        Loan selectedLoan = loanTable.getSelectionModel().getSelectedItem();
        if (selectedLoan == null) return;
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/softeng/librarymanager/fxml/LoanPopupView.fxml"));
            
            loader.setController(new LoanModifyPopupController(
                    (old, newObj) -> library.getLoanRegister().modify(old, newObj),
                    (toVerify) -> library.getLoanRegister().isUnique(toVerify),
                    selectedLoan
            ));
            
            Parent root = loader.load();
            Scene scene = new Scene(root, 480, 720);
            
            // CSS
            scene.getStylesheets().add(getClass().getResource("/softeng/librarymanager/style.css").toExternalForm());
            
            Stage popup = new Stage();
            popup.setTitle("Modifica Prestito");
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setScene(scene);
            popup.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        updateTableView();
    }

    /**
     * @brief Rimuove il prestito selezionato dal registro.
     * @details Invocato alla pressione del tasto "Rimuovi" nella SideBar.
     */
    private void removeFromRegister() {
        Loan selectedLoan = loanTable.getSelectionModel().getSelectedItem();
        if (selectedLoan == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma rimozione");
        alert.setHeaderText(
                "Sei sicuro di voler rimuovere il prestito di " + selectedLoan.getStudent().getSurname()
                + " " + selectedLoan.getStudent().getName()
        );
        alert.setContentText("L'operazione è irreversibile.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            library.getLoanRegister().remove(selectedLoan);
            updateTableView();
        }
    }


    private void updateTableView() {
        loanTable.setItems(FXCollections.observableArrayList(library.getLoanRegister().getRegisterList()));
    }
    
    private void searchLoan() {
        String searchText = sideBarController.getSearchBarTF().getText();
        
        if (searchText == null || searchText.isEmpty()) {
            updateTableView();
            return;
        }
        
        ObservableList<Loan> allLoans = FXCollections.observableArrayList(library.getLoanRegister().getRegisterList());
        String lowerCaseSearchText = searchText.toLowerCase();
        ObservableList<Loan> filteredLoans = FXCollections.observableArrayList();
        
        for (Loan loan : allLoans) {
            if (loan.getStudent().getName().toLowerCase().contains(lowerCaseSearchText) ||
                loan.getStudent().getSurname().toLowerCase().contains(lowerCaseSearchText) ||
                loan.getStudent().getStudentId().toLowerCase().contains(lowerCaseSearchText) ||
                loan.getBook().getTitle().toLowerCase().contains(lowerCaseSearchText) ||
                loan.getBook().getBookId().toLowerCase().contains(lowerCaseSearchText)) {
                filteredLoans.add(loan);
            }
        }
        
        Collections.sort(filteredLoans);
        loanTable.setItems(filteredLoans);
    }
}
