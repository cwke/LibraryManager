package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRegisterTest {

    private StudentRegister studentRegister;
    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    void setUp(){
        studentRegister = new StudentRegister();
        student1 = new Student("Fabrizio", "Acerra", "0000000004", "f.acerra4@studenti.unisa.it");
        student2 = new Student("Fabrizio", "Arreca", "0000000001", "n.affinita@studenti.unisa.it");
        student3 = new Student("Fabrizio", "Acerra", "0000000003", "j.cwiertka@studenti.unisa.it");
    }

    /*
     * Il metodo getRegisterList() deve garantire che la List<Student> restituita contenga tutti gli studenti aggiunti al catalogo
     * e che questi siano ordinati secondo Nome, Cognome e Matricola.
     * */
    @Test
    void testGetValuesList() {
        studentRegister.add(student2);
        studentRegister.add(student3);
        studentRegister.add(student1);

        List<Student> list = studentRegister.getRegisterList();
        assertEquals(3, list.size());
        assertTrue(list.contains(student1));
        assertTrue(list.contains(student2));
        assertTrue(list.contains(student3));

        assertEquals(list.get(0), student3);
        assertEquals(list.get(1), student1);
        assertEquals(list.get(2), student2);
    }

    /*
     * Il costruttore deve garantire 1) Le invarianti alla fine dell'operazione -> studentRegisteR != null
     * 2) Che il catalogo libri sia vuoto.
     * */
    @Test
    void testStudentRegister(){
        StudentRegister studentRegisterConstructorTest = new StudentRegister();
        assertNotNull(studentRegisterConstructorTest);
        List<Student> list = studentRegisterConstructorTest.getRegisterList();
        assertTrue(list.isEmpty());
    }

    /*
     * Il metodo "add()" deve garantire che, dato un ingresso "student1" != null che risulti valido secondo
     * isUnique(), quest'ultimo sia presente nel catalogo al termine dell'operazione.
     * */
    @Test
    void testAdd() {
        assertTrue(studentRegister.isUnique(student1));
        assertNotNull(student1);
        studentRegister.add(student1);
        List<Student> list = studentRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(student1));
    }

    /*
     * Il metodo "modify()" deve garantire che, dato un parametro 'old' già presente nel catalogo e un
     * parametro 'newObj' non nullo, gli attributi modificabili (quindi nome, cognome, email) di 'old'
     * risultino pari a quelli di 'newObj' al termine dell'operazione. Si noti che il riferimento di 'old' presente
     * nel catalogo al termine dell'operazione deve rimanere invariato (non è una sostituzione, bensì una copia valore per valore
     * degli attributi modificabili).
     * */
    @Test
    void testModify() {
        studentRegister.add(student2);
        List<Student> list = studentRegister.getRegisterList();
        assertTrue(list.contains(student2));
        String oldStudId = student2.getStudentId();
        assertNotNull(student3);
        studentRegister.modify(student2, student3);
        list = studentRegister.getRegisterList();

        assertEquals(1, list.size());

        Student modifiedStudent = list.get(0);

        assertEquals(student3.getEmail(), modifiedStudent.getEmail());
        assertEquals(student3.getName(), modifiedStudent.getName());
        assertEquals(student3.getSurname(), modifiedStudent.getSurname());
        assertEquals(oldStudId, modifiedStudent.getStudentId());
        assertSame(modifiedStudent, student2);
    }

    /*
     * Il metodo remove() deve garantire che, dato un parametro 'toRemove' presente nel catalogo, quest'ultimo
     * non risulti più presente nel catalogo al termine dell'operazione.
     * */
    @Test
    void testRemove() {
        studentRegister.add(student2);
        studentRegister.add(student1);

        List<Student> list = studentRegister.getRegisterList();
        assertTrue(list.contains(student2));
        studentRegister.remove(student2);

        list = studentRegister.getRegisterList();
        assertEquals(1, list.size());
        assertFalse(list.contains(student2));
    }


    /*
     * Il metodo isUnique() deve garantire che, dato un ingresso toVerify != null, sia restituito "false" se
     * 'toVerify' è già presente nel catalogo, altrimenti "true".
     * */
    @Test
    void testIsUnique() {
        assertNotNull(student2);
        assertNotNull(student3);

        studentRegister.add(student2);

        assertFalse(studentRegister.isUnique(new Student("a", "b", student2.getStudentId(), "a@studenti.unisa.it")));
        assertTrue(studentRegister.isUnique(student3));
    }


}