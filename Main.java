

/**
 *
 * @author SIZA M
 */


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin("Admin", "User", "admin@example.com", "Computer Science", "admin123");
        List<Student> students = new ArrayList<>();
        NotificationService notificationService = new NotificationService();
        Timetable timetable = new Timetable();

        LoginPage loginPage = new LoginPage(admin, students);
        loginPage.setVisible(true);
    }
}


