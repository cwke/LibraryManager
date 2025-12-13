/**
 * @file LoanInsertPopupController.java
 * @brief Controller per la finestra di dialogo di creazione di un nuovo prestito.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.controllers
 */

package softeng.librarymanager.controllers.loan;

import java.time.LocalDate;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import softeng.librarymanager.models.Book;
import softeng.librarymanager.models.Loan;
import softeng.librarymanager.models.RegisterAdder;
import softeng.librarymanager.models.RegisterObtainer;
import softeng.librarymanager.models.RegisterValidator;
import softeng.librarymanager.models.Student;

/**
 * @class LoanInsertPopupController
 * @brief Classe controller per la gestione del popup di inserimento prestiti.
 * @details Estende {@link LoanPopupController}. Si occupa di popolare le ComboBox
 *          recuperando le liste di studenti e libri tramite {@link RegisterObtainer} e
 *          di aggiungere il nuovo prestito tramite {@link RegisterAdder}.
 */
public class LoanInsertPopupController extends LoanPopupController {

    private RegisterAdder<Loan> loanRegisterAdder;
    private RegisterObtainer<Book> bookRegisterObtainer;
    private RegisterObtainer<Student> studentRegisterObtainer;
    private RegisterValidator<Loan> loanRegisterValidator;

    public LoanInsertPopupController(RegisterAdder<Loan> loanRegisterAdder, RegisterObtainer<Book> bookRegisterObtainer, RegisterObtainer<Student> studentRegisterObtainer, RegisterValidator<Loan> loanRegisterValidator) {
        this.loanRegisterAdder = loanRegisterAdder;
        this.bookRegisterObtainer = bookRegisterObtainer;
        this.studentRegisterObtainer = studentRegisterObtainer;
        this.loanRegisterValidator = loanRegisterValidator;
    }

    /**
     * @brief Inizializza il controller.
     * @details Richiama l'inizializzazione della superclasse.
     */
    @FXML
    public void initialize() {
        super.initialize();
        setupStudentInsert();
        setupBookInsert();
        
        // Inizializza la data di prestito come la data attuale + 1 mese.
        dateDP.setValue(LocalDate.now().plusMonths(1));
        
        // Binding per la disabilitazione del pulsante di conferma se non sono selezionati un libro e uno studente.
        BooleanBinding studentNotSelectedBinding = studentListView.getSelectionModel().selectedItemProperty().isNull();
        BooleanBinding bookNotSelectedBinding = bookListView.getSelectionModel().selectedItemProperty().isNull();
        
        confirmBtn.disableProperty().bind(studentNotSelectedBinding.or(bookNotSelectedBinding));
    }

    private void setupStudentInsert() {
        FilteredList<Student> filteredStudents = new FilteredList<>(FXCollections.observableArrayList(studentRegisterObtainer.getRegister()), Student::isAvailableForLoan);
        
        studentSearchTF.textProperty().addListener( (observable, oldValue, newValue) -> {
            filteredStudents.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty()) return true;
                
                // Escludo gli studenti che non sono disponibili per un prestito.
                if (!student.isAvailableForLoan()) return false;
                
                // Ricerca per nome, cognome e matricola
                String lowerCaseSearchText = newValue.toLowerCase();
                return student.getName().toLowerCase().contains(lowerCaseSearchText) ||
                        student.getSurname().toLowerCase().contains(lowerCaseSearchText) ||
                        student.getStudentId().toLowerCase().contains(lowerCaseSearchText);
            });
        } );
        
        SortedList<Student> sortedStudents = new SortedList<>(filteredStudents);
        studentListView.setItems(sortedStudents);
        
        studentListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Student>() {
            @Override
            public String toString(Student student) {
                return student.getName() + " " + student.getSurname() + " (" + student.getStudentId() + ")";
            }

            @Override
            public Student fromString(String string) {return null;}
        
        }));
    }

    private void setupBookInsert() {
        FilteredList<Book> filteredBook = new FilteredList<>(FXCollections.observableArrayList(bookRegisterObtainer.getRegister()), Book::isAvailableForLoan);
        
        bookSearchTF.textProperty().addListener( (observable, oldValue, newValue) -> {
            filteredBook.setPredicate(book -> {
                
                
                // Escludo i libri che non sono disponibili per un prestito.
                if (! book.isAvailableForLoan() ) return false;
                
                //if (newValue == null || newValue.isEmpty()) return true;
                
                // Ricerca per nome, cognome e matricola
                String lowerCaseSearchText = newValue.toLowerCase();
                return book.getTitle().toLowerCase().contains(lowerCaseSearchText) ||
                        book.getBookId().toLowerCase().contains(lowerCaseSearchText);
            });
        } );
        
        SortedList<Book> sortedBook = new SortedList<>(filteredBook);
        bookListView.setItems(sortedBook);
        
        bookListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                return book.getTitle() + " (" + book.getBookId() + ")";
            }

            @Override
            public Book fromString(String string) {return null;}
        
        }));
    }
    
    /**
     * @brief Gestisce l'azione di conferma per l'inserimento.
     * @details Crea un nuovo oggetto Loan associando Studente e Libro selezionati,
     *          lo valida e lo aggiunge al registro.
     * @param[in] event L'evento click.
     */
    @Override
    public void confirmBtnAction(javafx.event.ActionEvent event) {
        Student selectedStudent = studentListView.getSelectionModel().getSelectedItem();
        Book selectedBook = bookListView.getSelectionModel().getSelectedItem();
        LocalDate selectedDate = dateDP.getValue();
        
        try {
            Loan loanToADd = new Loan(selectedStudent, selectedBook, selectedDate);
            if (loanRegisterValidator.isUnique(loanToADd)) {
                loanToADd.activateLoan();
                loanRegisterAdder.add(loanToADd);
                Stage stage = (Stage) confirmBtn.getScene().getWindow();
                stage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Errore", "Dati non validi", "Lo studente ha già lo stesso libro in prestito.");
            }
            
        } catch (IllegalArgumentException | IllegalStateException e) {
            showAlert(Alert.AlertType.ERROR, "Errore", "Dati non validi", e.getMessage());
        }
        
    }

}
