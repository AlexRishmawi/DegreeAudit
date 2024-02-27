import java.util.ArrayList;

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

    }

    public ArrayList<Course> getAllCourse() {
        
    }
    
}
