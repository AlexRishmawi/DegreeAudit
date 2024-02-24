import java.util.ArrayList;
import java.util.HashMap;

public class Semester {
    private String season;
    private int creditLimit;
    private HashMap<Course, String> courses;

    public Semester(String season, ArrayList<Course> courseList) {
        this.season = season;
        this.courses = new HashMap<>();
        for (Course course : courseList) {
            courses.put(course, "N/A");
        }
    }

    public ArrayList<Course> getCourses() {

    }

    public String getCourseGrade(Course course) {

    }

    public boolean setCourseGrade(Course course, String grade) {
    
    }

    @Override
    public String toString() {

    }
}