package softeng.librarymanager.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import softeng.librarymanager.interfaces.Register;

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
