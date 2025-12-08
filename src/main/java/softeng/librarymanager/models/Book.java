/**
 * @file Book.java
 * @brief Definizione della classe modello che rappresenta un libro.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */
package softeng.librarymanager.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @class Book
 * @brief Classe modello che rappresenta un libro.
 * @details Questa classe modella un'entità libro all'interno del catalogo, contenente
 *          dettagli come titolo, anno di pubblicazione, autori, ID univoco
 *          e numero di copie disponibili.
 *          Implementa {@link Comparable} per l'ordinamento e {@link Externalizable}
 *          per l'IO su file.
 */
public class Book implements Comparable<Book>, Externalizable {

    /**
     * @brief Proprietà titolo.
     */
    private StringProperty title;

    /**
     * @brief Proprietà anno di pubblicazione.
     */
    private IntegerProperty publishmentYear;

    /**
     * @brief Proprietà autori.
     */
    private StringProperty authors;

    /**
     * @brief Proprietà identificativo libro (ISBN) {readOnly}.
     */
    private StringProperty bookId;

    /**
     * @brief Proprietà numero di copie disponibili.
     */
    private IntegerProperty availableCopies;

    /**
     * @brief Costruttore predefinito.
     * @details Inizializza le proprietà con valori di default.
     */
    public Book() {
        this.title = new SimpleStringProperty("");
        this.publishmentYear = new SimpleIntegerProperty(0);
        this.authors = new SimpleStringProperty("");
        this.bookId = new SimpleStringProperty("");
        this.availableCopies = new SimpleIntegerProperty(1); //Qui inizializzo con una copia perchè sto creando il libro
    }

    /**
     * @brief Restituisce la proprietà del titolo per il binding.
     * @return StringProperty Oggetto proprietà del titolo.
     */
    public StringProperty titleProperty() {
        return this.title;
    }

    /**
     * @brief Restituisce la proprietà dell'anno di pubblicazione per il binding.
     * @return IntegerProperty Oggetto proprietà dell'anno.
     */
    public IntegerProperty publishmentYearProperty() {
        return this.publishmentYear;
    }

    /**
     * @brief Restituisce la proprietà degli autori per il binding.
     * @return StringProperty Oggetto proprietà degli autori.
     */
    public StringProperty authorsProperty() {
        return this.authors;
    }

    /**
     * @brief Restituisce la proprietà dell'ID libro (ISBN).
     * @return StringProperty Oggetto proprietà dell'ID.
     */
    public StringProperty bookIdProperty() {
        return this.bookId;
    }

    /**
     * @brief Restituisce la proprietà delle copie disponibili.
     * @return IntegerProperty Oggetto proprietà delle copie.
     */
    public IntegerProperty availableCopiesProperty() {
        return this.availableCopies;
    }

    /**
     * @brief Confronta due libri per l'ordinamento.
     * @details L'ordinamento avviene prima per titolo (alfabetico), poi per autori, e infine per ID.
     * @param[in] other Il libro con cui confrontare.
     * @return int Un valore negativo, zero o positivo.
     */
    @Override
    public int compareTo(Book other) {
        return 0;
    }

    /**
     * @brief Metodo personalizzato per serializzare l'oggetto.
     * @details Estrae i valori "puri" dalle proprietà JavaFX e li scrive nello stream.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    /**
     * @brief Metodo personalizzato per deserializzare l'oggetto.
     * @details Legge i valori dallo stream e ricostruisce le proprietà JavaFX.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

}
