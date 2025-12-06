/**
 * @file Student.java
 * @brief Implementa la classe Student.
 */

package softeng.librarymanager.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * @brief Rappresenta uno studente.
 * @details Questa classe modella un'entità studente, contenente dettagli come
 *          il nome, cognome, matricola, email, e numero di prestiti disponibili.
 *          Implementa {@link Comparable} per l'ordinamento e {@link Serializable}
 *          per l'IO su file.
 *
 */
public class Student implements Comparable<Student>, Serializable {
    private String name; ///< Il nome dello studente.

    private String surname; ///< Il cognome dello studente.

    private final String studentCode; ///< La matricola dello studente.

    private String email; ///< L'email dello studente.

    private int availableLoans; ///< Il numero di prestiti disponibili dello studente.

    /**
     * @brief Costruisce un nuovo Student. Un nuovo studente di default ha 3
     *        prestiti disponibili.
     * 
     * @param name        Il nome dello studente.
     * @param surname     Il cognome dello studente.
     * @param studentCode La matricola dello studente.
     * @param email       L'email dello studente.
     */
    public Student(String name, String surname, String studentCode, String email) {
        this.name = name;
        this.surname = surname;
        this.studentCode = studentCode;
        this.email = email;
        this.availableLoans = 3;
    }

    /**
     * @return Il numero di prestiti disponibili dello studente.
     */
    public int getAvailableLoans() {
        return availableLoans;
    }

    /**
     * @param availableLoans Il nuovo numero di prestiti disponibili dello studente.
     */
    public void setAvailableLoans(int availableLoans) {
        this.availableLoans = availableLoans;
    }

    /**
     * @return Il nome dello studente.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Il cognome dello studente.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return La matricola dello studente.
     */
    public String getStudentCode() {
        return studentCode;
    }

    /**
     * @return L'email dello studente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param name Il nuovo nome dello studente.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param surname Il nuovo cognome dello studente.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @param email La nuova email dello studente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Un booleano che indica se due studenti hanno lo stesso riferimento o
     *         stessa matricola.
     */
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
        final Student other = (Student) obj;
        return Objects.equals(this.studentCode, other.studentCode);
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }
}
