package softeng.librarymanager.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import softeng.librarymanager.interfaces.Register;

public class StudentRegister implements Register<Student> {
    private final ObservableList<Student> students;

    public StudentRegister() {
        this.students = FXCollections.observableArrayList();
    }

    @Override
    public void add(Student student) {
        students.add(student);
    }

    @Override
    public void remove(Student student) {
        students.remove(student);
    }

    @Override
    public ObservableList<Student> getObservableList() {
        return this.students;
    }

    @Override
    public boolean isValid(Student other) {
        //Todo: da implementare i controlli
        return true;
    }
}
