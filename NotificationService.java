
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author SIZA M
 */
public class NotificationService {
    private List<String> notifications;

    public NotificationService() {
        notifications = new ArrayList<>();
    }

    public void notifyStudents(String message) {
        notifications.add(message);
        System.out.println("Notification sent to students: " + message);
    }

    public List<String> getNotifications() {
        return notifications;
    }
    
}
