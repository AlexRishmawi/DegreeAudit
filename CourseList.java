import java.util.ArrayList;
import java.util.UUID;
public class CourseList {
    private static CourseList courseList;
    private ArrayList<Course> courses;
    
    private CourseList() {
        this.courses = new ArrayList<>();
        // Read a database
    }

    public static CourseList getInstance() {
        return courseList != null ? courseList : new CourseList();
    }

    public Course getCourse(UUID id) {
        for (Course course : courses) {
            if (course.getID() == id)
                return course;
        }
        return null;
    }

    public ArrayList<Course> getAllCourse() {
        return this.courses;
    }
    
}
