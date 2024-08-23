

/**
 *
 * @author SIZA M
 */

public class Student extends User {
    private String name;
    private String surname;
    private String email;
    private String id;
    private String password;

    public Student(String name, String surname, String email, String id, String password) {
        super(email, password);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    // Getter and setter methods for name, surname, email, id, and password
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
