package softeng.librarymanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRegisterTest {

    private StudentRegister studentRegister;
    private Student newStudent;
    private Student oldStudent;

    /*
     * Inizializzo due studenti che utilizzerò per verificare le diverse
     * operazioni messe a disposizione dallo StudentRegister.
     * */
    @BeforeEach
    void setUp(){
        studentRegister = new StudentRegister();
        newStudent = new Student("Fabrizio", "Acerra", "0612709404", "f.acerra4@studenti.unisa.it");
        oldStudent = new Student("Natale", "Affinita", "0612709745", "n.affinita@studenti.unisa.it");
    }

    /*
     * Questo metodo deve garantire che la List restituita contenga tutti gli elementi presenti
     * nel catalogo, ordinati prima per nome e poi per cognome.
     * */
    @Test
    void testGetValuesList() {
        studentRegister.add(oldStudent);
        studentRegister.add(newStudent);
        List<Student> list = studentRegister.getRegisterList();
        assertEquals(2, list.size());
        assertTrue(list.contains(newStudent));
        assertTrue(list.contains(oldStudent));

        /*
         * Se il metodo funziona correttamente, per il rispetto dell'ordinamento alfabetico l'ordine
         * di oldStudent e newStudent nella List dev'essere invertito.
         * */
        assertEquals(list.get(0), newStudent);
        assertEquals(list.get(1), oldStudent);
    }

    /*
     * Il costruttore deve garantire 1) Le invarianti alla fine dell'operazione -> studentRegisteR != null
     * 2) Che il catalogo studenti sia vuoto.
     * */
    @Test
    void testStudentRegister(){
        assertNotNull(studentRegister);
        List<Student> list = studentRegister.getRegisterList();
        assertTrue(list.isEmpty());
    }

    /*
     * Dato un ingresso != null valido secondo RegisterValidator (dunque unico, non ancora inserito
     * nel catalogo) il metodo add() deve garantire che, al termine dell'operazione, lo studente sia stato
     * effettivamente inserito nel catalogo. Si noti che utilizzo il metodo getRegisterList() per quest'operazione e le seguenti
     * poiché il relativo caso di test mi garantisce la corrispondenza tra la List restituita da suddetto metodo ed il catalogo studenti
     * (salvo ulteriori operazioni sul catalogo successive rispetto all'invocazione del metodo).
     * */
    @Test
    void testAdd() {
        studentRegister.add(newStudent);
        List<Student> list = studentRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newStudent));
    }

    /*
     * Dato un ingresso "newStudent" != null ed un libro "oldStudent" già presente nel catalogo, modify() deve
     * garantire che al termine dell'operazione il riferimento allo studente "oldStudent" già presente resti invariato e che
     * i suoi dati (tranne i non modificabili, quindi tutti tranne il studentId) risultino pari a quelli di newBook.
     * Anche in questo metodo utilizzo il metodo getRegisterList() per i motivi citati nel commento del metodo di test precedente.
     * */
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

    /*
     * Dato un ingresso "toRemove" (in questo caso oldStudent) già presente nel catalogo, il metodo
     * remove() deve garantire che al termine dell'operazione lo studente non sia più presente nel catalogo.
     * */
    @Test
    void testRemove1() {
        studentRegister.add(oldStudent);
        studentRegister.add(newStudent);
        studentRegister.remove(oldStudent);
        List<Student> list = studentRegister.getRegisterList();
        assertEquals(1, list.size());
        assertTrue(list.contains(newStudent));
    }

    /*
     * Dato un ingresso !null con attributi !null (garantito dal costrutture di Student)
     * questo metodo deve restituire "false" se uno studente identico (quindi con stesso studentId) è
     * già presente nel catalogo, true altrimenti.
     * */
    @Test
    void testIsUnique1() {
        studentRegister.add(newStudent);
        assertFalse(studentRegister.isUnique(new Student("a", "b", newStudent.getStudentId(), "a@studenti.unisa.it")));
    }

    @Test
    void testIsUnique2() {
        assertTrue(studentRegister.isUnique(newStudent));
    }

}