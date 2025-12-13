/**
 * @file RegisterValidator.java
 * @brief Interfaccia funzionale per la validazione di elementi.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

/**
 * @interface RegisterValidator
 * @brief Interfaccia che definisce il contratto per la validazione dei dati.
 * @details Utilizzata dai controller per verificare la correttezza dei dati inseriti
 *          dall'utente prima di procedere con operazioni di scrittura.
 * @tparam T Il tipo di dato da validare.
 */
public interface RegisterValidator<T> {

    /**
     * @brief Verifica se l'oggetto soddisfa i criteri di validità.
     * @param[in] toVerify L'oggetto da verificare.
     * @return boolean True se l'oggetto è valido, False altrimenti.
     */
    boolean isUnique(T toVerify);

}
