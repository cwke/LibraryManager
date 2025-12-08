/**
 * @file RegisterObtainer.java
 * @brief Interfaccia funzionale per l'ottenimento della lista completa di un registro.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import javafx.collections.ObservableList;

/**
 * @interface RegisterObtainer
 * @brief Interfaccia che definisce il contratto per ottenere i dati di un registro.
 * @details Utilizzata principalmente dai controller di inserimento (es. Prestiti)
 *          per popolare le caselle di scelta (ComboBox) recuperando le liste di
 *          entità correlate (es. lista Studenti e lista Libri disponibili).
 * @tparam T Il tipo di dato contenuto nel registro.
 */
public interface RegisterObtainer<T> {

    /**
     * @brief Restituisce la lista osservabile degli elementi del registro.
     * @return ObservableList<T> La lista degli elementi.
     */
    ObservableList<T> getRegister();
}