

/**
 *
 * @author SIZA M
 */
import java.util.ArrayList;
import java.util.List;

public class TimetableManagementTest {
    public static void main(String[] args) {
        // Create an admin
        Admin admin = new Admin("Admin", "User", "admin@example.com", "Computer Science", "admin123");
        
        // Create a list to hold students
        List<Student> students = new ArrayList<>();
        
        // Create a notification service
        NotificationService notificationService = new NotificationService();
        
        // Create a timetable
        Timetable timetable = new Timetable();
        
        // Add some initial classes to the timetable
        timetable.addOrUpdateClass("CS101", "Introduction to Computer Science, Venue: Room 101");
        timetable.addOrUpdateClass("MATH201", "Calculus II, Venue: Room 202");
        
        // Display initial class details
        System.out.println("Initial Timetable:");
        System.out.println("CS101 Details: " + timetable.getClassDetails("CS101"));
        System.out.println("MATH201 Details: " + timetable.getClassDetails("MATH201"));
        
        // Change venue for a class and notify students
        timetable.changeVenue("CS101", "Room 105", notificationService);
        
        // Display updated class details
        System.out.println("\nUpdated Timetable:");
        System.out.println("CS101 Details: " + timetable.getClassDetails("CS101"));
        
        // Display notifications
        System.out.println("\nNotifications:");
        for (String notification : notificationService.getNotifications()) {
            System.out.println(notification);
        }
        
        // Create a login page and display it
        LoginPage loginPage = new LoginPage(admin, students);
        loginPage.setVisible(true);
    }
}

