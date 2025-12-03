/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 *
 * @author Jakub
 */
public class BookCatalog {
    private final ObservableMap<String, Book> books;
    
    public BookCatalog() {
        this.books = FXCollections.observableHashMap();
    }
    
    // Il libro deve essere giàcorretto e univoco 
    //public boolean addBook(Book book) {
        
    //}
    
    //public boolean removeBook(Book book) {
    
    //}
    
}
