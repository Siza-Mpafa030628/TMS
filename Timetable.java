import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SIZA M
 */
public class Timetable {


    private Map<String, String> schedule;
    

    public Timetable() {
        schedule = new HashMap<>();
        

    }

    public void addOrUpdateClass(String classId, String details) {
        schedule.put(classId, details);
    }

    public String getClassDetails(String classId) {
        return schedule.get(classId);
    }

    public void removeClass(String classId) {
        schedule.remove(classId);
    }
    public void changeVenue(String classId, String newVenue, NotificationService notificationService) {
        if (schedule.containsKey(classId)) {
            String details = schedule.get(classId);
            details = details.replaceFirst("Venue: \\w+", "Venue: " + newVenue);
            schedule.put(classId, details);
            notificationService.notifyStudents("Class " + classId + " venue changed to " + newVenue);
        }
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }
}

