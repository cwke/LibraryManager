/**
 * @file RegisterModifier.java
 * @brief Interfaccia funzionale per la modifica di elementi in un registro.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

/**
 * @interface RegisterModifier
 * @brief Interfaccia che definisce il contratto per l'operazione di modifica.
 * @details Utilizzata dai controller per delegare l'aggiornamento dei dati.
 * @tparam T Il tipo di dato da modificare.
 */
public interface RegisterModifier<T> {

    /**
     * @brief Esegue la modifica di un elemento esistente.
     * @param[in] old L'oggetto originale da sostituire.
     * @param[in] newObj Il nuovo oggetto che sostituisce il precedente.
     * @return boolean True se la modifica ha successo, False altrimenti.
     */
    boolean modify(T old, T newObj);

}
