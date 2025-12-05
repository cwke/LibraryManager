/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softeng.librarymanager.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Jakub
 */
public class Loan implements Comparable<Loan>, Serializable{
    private final Student student;
    private final Book book;
    private final LocalDate loanStart;
    private LocalDate loanEnd;
    private boolean returned;

    public Loan(Student student, Book book, LocalDate loanEnd) {
        this.student = student;
        this.book = book;
        this.loanEnd = loanEnd;
        this.loanStart = LocalDate.now();
    }
       
    public Loan(Student student, Book book) {
        this(student, book, LocalDate.now().plusMonths(1));
    }

    public Student getStudent() {
        return student;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanStart() {
        return loanStart;
    }

    public LocalDate getLoanEnd() {
        return loanEnd;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setLoanEnd(LocalDate loanEnd) {
        this.loanEnd = loanEnd;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
        // TODO: da implementare
            // Se il libro è stato restituito incrementare availableLoans di studente
            // Altrimenti decrementare
    }

    @Override
    public int compareTo(Loan o) {
        // TODO: da implementare
        return 0;
    }
    
}
