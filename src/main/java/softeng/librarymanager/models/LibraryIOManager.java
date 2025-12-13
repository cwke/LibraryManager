/**
 * @file LibraryIOManager.java
 * @brief Gestore delle operazioni di Input/Output per il salvataggio e caricamento dati.
 * @author [Acerra Fabrizio, Affinita Natale, Cwiertka Jakub, Galluccio Hermann]
 * @date Dicembre 2025
 * @package softeng.librarymanager.models
 */

package softeng.librarymanager.models;


import softeng.librarymanager.controllers.student.ResultActions;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class LibraryIOManager
 * @brief Classe responsabile della persistenza dei dati dell'applicazione.
 * @details Questa classe fornisce i metodi per serializzare l'intero oggetto {@link Library}
 *          su un file (salvataggio) e per deserializzarlo ripristinando lo stato dell'applicazione
 *          (caricamento). Gestisce le eccezioni di I/O che possono verificarsi durante queste operazioni.
 */
public class LibraryIOManager {


    private ResultActions resultActions;
    /**
     * @brief Costruttore predefinito.
     */
    public LibraryIOManager(ResultActions actions) {
        this.resultActions = actions;
    } //da cambiare: spostiamo il setter del resultsAction nel costruttore

    /**
     * @brief Salva lo stato corrente della biblioteca su file.
     * @details Serializza l'oggetto Library passato come parametro e lo scrive nel percorso specificato.
     * @param[in] libraryToSave L'istanza della biblioteca contenente tutti i registri da salvare.
     * @param[in] filePath Il percorso del file (incluso nome ed estensione) su cui scrivere.
     */
    public void saveLibrary(Library libraryToSave, String filePath) {
        File fileObj = new File(filePath);
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(fileObj.toPath())))){
            oos.writeObject(libraryToSave);
            System.out.println("...");
            resultActions.success();
        } catch (IOException ex) {
            Logger.getLogger(LibraryIOManager.class.getName()).log(Level.SEVERE, null, ex);
            resultActions.failure();
        }
    }

    /**
     * @brief Carica lo stato della biblioteca da un file.
     * @details Legge il file dal percorso specificato e deserializza l'oggetto Library.
     * @param[in] filePath Il percorso del file da cui leggere i dati.
     * @return Library L'istanza della biblioteca ripristinata con tutti i suoi dati.
     */
    public Library loadLibrary(String filePath) {
        File fileObj = new File(filePath);
        Library loadedLibrary = null;

        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(fileObj)))){

            Object obj = ois.readObject();

            if(obj instanceof Library){
                loadedLibrary = (Library) obj;
//                resultActions.success();
            } else {
                resultActions.failure();
            }

        } catch (IOException ex){
            System.out.println("IOException");
            resultActions.failure();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
            resultActions.failure();
        }

        return loadedLibrary;
    }
}
