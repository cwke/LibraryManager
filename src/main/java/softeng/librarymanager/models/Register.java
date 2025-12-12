/**
 * @file Register.java
 * @brief Definizione dell'interfaccia generica per la gestione dei registri di sistema.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;

import javafx.collections.ObservableList;

import java.util.List;

/**
 * @brief Interfaccia generica per la gestione di un registro di elementi.
 *
 * Questa interfaccia definisce il contratto per le operazioni di manipolazione
 * (aggiunta, modifica, rimozione) e validazione di oggetti di tipo T all'interno
 * del sistema. Espone una lista osservabile per l'integrazione con l'interfaccia grafica JavaFX.
 *
 * @tparam T Il tipo di oggetto gestito da questo registro (es. Libro, Utente, Prestito).
 */
public interface Register<T> {

    /**
     * @brief Aggiunge un nuovo elemento al registro.
     * @param[in] toAdd L'elemento di tipo T da aggiungere.
     * @return true se l'elemento è stato aggiunto con successo, false altrimenti.
     */
    public void add(T toAdd);

    /**
     * @brief Modifica un elemento esistente nel registro.
     * @param[in] old L'elemento attualmente presente nel registro da sostituire.
     * @param[in] newObj Il nuovo elemento che prenderà il posto del precedente.
     * @return true se la modifica è avvenuta con successo, false altrimenti.
     */
    public void modify(T old, T newObj);

    /**
     * @brief Rimuove un elemento dal registro.
     * @param[in] toRemove L'elemento di tipo T da rimuovere.
     * @return true se l'elemento è stato trovato e rimosso, false se non era presente.
     */
    public void remove(T toRemove);

    /**
     * @brief Verifica la validità di un elemento.
     * @param[in] toVerify L'elemento da verificare.
     * @return true se l'elemento è valido, false altrimenti.
     */
    public boolean isValid(T toVerify);

    /**
     * @brief Restituisce la lista osservabile degli elementi.
     * @return Una ObservableList contenente tutti gli elementi di tipo T presenti nel registro.
     */
    public List<T> getRegister();

}
