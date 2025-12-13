package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRegisterTest {

    private StudentRegister studentRegister;
    private Student newStudent;
    private Student oldStudent;

    @BeforeEach
    void setUp(){
        studentRegister = new StudentRegister();
        newStudent = new Student("Fabrizio", "Acerra", "0612709404", "f.acerra4@studenti.unisa.it");
        oldStudent = new Student("Natale", "Affinita", "0612709745", "n.affinita@studenti.unisa.it");
    }

    /*
     * L'ho messo in alto perché lo utilizzo in quasi tutti gli altri casi di test. Una volta verificata
     * la corrispondenza tra la Struttura Dati gestita nello StudentRegister e la lista restituita da questo
     * metodo (ovviamente solo se non si aggiungono ulteriori elementi dopo la getValuesList()) posso direttamente
     * eseguire i test a partire da suddetto metodo.
     * */
    @Test
    void testGetValuesList() {
        studentRegister.add(newStudent);
        studentRegister.add(oldStudent);
        List<Student> list = studentRegister.getRegisterList();
        assertEquals(2, list.size());
        assertTrue(list.contains(newStudent));
        assertTrue(list.contains(oldStudent));
    }

    @Test
    void testStudentRegister(){
        assertNotNull(studentRegister);
        List<Student> list = studentRegister.getRegisterList();
        assertTrue(list.isEmpty());
    }

    @Test
    void testAdd() {
        studentRegister.add(newStudent);
        List<Student> list = studentRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newStudent));
    }

    @Test
    void testModify() {
        studentRegister.add(oldStudent);
        String oldStudId = oldStudent.getStudentId();
        studentRegister.modify(oldStudent, newStudent);
        List<Student> list = studentRegister.getRegisterList();

        assertEquals(1, list.size());

        Student modifiedStudent = list.get(0);

        assertEquals(newStudent.getEmail(), modifiedStudent.getEmail());
        assertEquals(newStudent.getName(), modifiedStudent.getName());
        assertEquals(newStudent.getSurname(), modifiedStudent.getSurname());
        assertEquals(oldStudId, modifiedStudent.getStudentId());
        assertTrue(modifiedStudent == oldStudent);
    }

    @Test
    void testRemove1() {
        studentRegister.add(oldStudent);
        studentRegister.add(newStudent);
        studentRegister.remove(oldStudent);
        List<Student> list = studentRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newStudent));
    }

    @Test
    void testIsUnique1() {
        studentRegister.add(newStudent);
        assertFalse(studentRegister.isUnique(newStudent));
    }

    @Test
    void testIsUnique2() {
        assertTrue(studentRegister.isUnique(newStudent));
    }

}