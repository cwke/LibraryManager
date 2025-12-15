/**
 * @file Library.java
 * @brief Definizione della classe principale del sistema di gestione biblioteca.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */
package softeng.librarymanager.models;

import java.io.Serializable;

/**
 * @class Library
 * @brief Classe principale che funge da contenitore per i registri della biblioteca.
 */
public class Library implements Serializable {

    /**
     * @brief Registro che gestisce l'inventario dei libri.
     * @see Register
     * @see Book
     */
    private final Register<Book> bookRegister;

    /**
     * @brief Registro che gestisce l'elenco degli studenti.
     * @see Register
     * @see Student
     */
    private final Register<Student> studentRegister;

    /**
     * @brief Registro che gestisce l'elenco dei prestiti.
     * @see Register
     * @see Loan
     */
    private final Register<Loan> loanRegister;

    /**
     * @brief Costruttore predefinito della classe Library.
     * Inizializza i tre registri concreti.
     */
    public Library() {
        this.bookRegister = new BookRegister();
        this.studentRegister = new StudentRegister();
        this.loanRegister = new LoanRegister();
    }

    /**
     * @brief Restituisce il registro libri.
     * @return Register<Book> Gestore inventario.
     */
    public Register<Book> getBookRegister() {
        return this.bookRegister;
    }

    /**
     * @brief Restituisce il registro studenti.
     * @return Register<Student> Gestore studenti.
     */
    public Register<Student> getStudentRegister() {
        return this.studentRegister;
    }

    /**
     * @brief Restituisce il registro prestiti.
     * @return Register<Loan> Gestore prestiti.
     */
    public Register<Loan> getLoanRegister() {
        return this.loanRegister;
    }

}
