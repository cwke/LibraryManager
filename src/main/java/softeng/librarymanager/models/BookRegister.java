/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jakub
 */
public class BookRegister {
    private final ObservableList<Book> books;

    public BookRegister() {
        this.books = FXCollections.observableArrayList();
    }

    // Il libro deve essere giàcorretto e univoco
    // public boolean addBook(Book book) {

    // }

    // public boolean removeBook(Book book) {

    // }

    public void add(Book book) {
        books.add(book);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public ObservableList<Book> getObservableList() {
        return this.books;
    }

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
