

/**
 *
 * @author SIZA M
 */

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private String name;
    private String surname;
    private String subject;
    private List<Student> students;

    public Admin(String name, String surname, String email, String subject, String password) {
        super(email, password);
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }
}
