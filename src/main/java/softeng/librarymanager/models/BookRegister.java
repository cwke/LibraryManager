/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.util.Callback;
import softeng.librarymanager.interfaces.Register;

/**
 *
 * @author Jakub
 */
public class BookRegister implements Register<Book>{
    private final ObservableList<Book> books;

    public BookRegister() {
        this.books = FXCollections.observableArrayList();
    }

    @Override
    public void add(Book book) {
        books.add(book);
    }
    
    @Override
    public void remove(Book book) {
        books.remove(book);
    }

    @Override
    public ObservableList<Book> getObservableList() {
        return this.books;
    }

    @Override
    public boolean isValid(Book other) {
        if (other.getBookCode().length() != 13) return false;
        for (Book b : books) {
            if (b != other) { // Per la verifica in caso di modifica (non vogliamo confrontare il libro da modificare con se stesso)
                if (b.getBookCode().equals(other.getBookCode())) return false;
            }
            
        }
        return true;
    }
}
