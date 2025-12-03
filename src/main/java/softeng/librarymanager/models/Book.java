/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Jakub
 */
public class Book implements Comparable<Book>, Serializable{
    private String title;
    private int publishYear;
    private final String bookCode;
    private int availableCopies;
    private Set<Author> authors; // Credo renderlo stringa semplificherebbe molto la progettazione

    public Book(String title, int publishYear, String bookCode, int availableCopies, Set<Author> authors) {
        this.title = title;
        this.publishYear = publishYear;
        this.bookCode = bookCode;
        this.availableCopies = availableCopies;
        this.authors = authors;
    }
    
    public String getTitle() {
        return title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public String getBookCode() {
        return bookCode;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.bookCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return Objects.equals(this.bookCode, other.bookCode);
    }

    @Override
    public int compareTo(Book o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
