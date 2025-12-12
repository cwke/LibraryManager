/**
 * @file RegisterAdder.java
 * @brief Interfaccia funzionale per l'aggiunta di elementi a un registro.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

/**
 * @interface RegisterAdder
 * @brief Interfaccia che definisce il contratto per l'operazione di aggiunta.
 * @details Utilizzata dai controller per delegare l'operazione di inserimento
 *          senza dipendere direttamente dall'implementazione concreta del registro.
 * @tparam T Il tipo di dato da aggiungere.
 */
public interface RegisterAdder<T> {

    /**
     * @brief Esegue l'aggiunta di un elemento.
     * @param[in] toAdd L'oggetto da aggiungere.
     */
    void add(T toAdd);

}
