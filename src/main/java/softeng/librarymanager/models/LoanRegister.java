/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import softeng.librarymanager.interfaces.Register;

/**
 *
 * @author Jakub
 */
public class LoanRegister implements Register<Loan> {
    
    private final ObservableList<Loan> loans;
    
    public LoanRegister() {
        this.loans = FXCollections.observableArrayList();
    }

    @Override
    public void remove(Loan item) {
        loans.remove(item);
    }

    @Override
    public ObservableList<Loan> getObservableList() {
        return this.loans;
    }

    @Override
    public void add(Loan item) {
        //TODO: gestire un eventuale incremento di availableLoans
        loans.add(item);
    }

    @Override
    public boolean isValid(Loan item) {
        
        // TODO: da implementare
            // Controllo se ci sono copie disponbili del libro
            // Controllo se lo studente ha availableLoans >= 1
            // Controllo se lo studente non ha già in prestito lo stesso libro
        return true;
    }    
}
