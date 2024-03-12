import java.util.ArrayList;
import java.util.HashMap;

public class Semester {
    private Season season;
    private int year;
    private int creditLimit;
    private HashMap<Course, String> courses;

    public Semester(String season, int year, int creditLimit, ArrayList<Course> courseList) {
        this.season = Season.valueOf(season.toUpperCase());
        this.year = year;
        this.creditLimit = creditLimit;
        this.courses = new HashMap<>();
        for (Course course : courseList) {
            // Initially, no grades are associated with the courses
            this.courses.put(course, "-");
        }
    }

    public HashMap<Course, String> getCourses() {
        return this.courses;
    }

    // Method to get the grade of a specific course
    public String getCourseGrade(Course course) {
        return courses.get(course);
    }


    @Override
    public String toString() {
        return "Semester{" +
               "season=" + season +
               ", year=" + year +
               ", creditLimit=" + creditLimit +
               ", courses=" + courses +
               '}';
    }

    public Season getSeason() {
        return this.season;
    }

    public int getYear() {
        return this.year;
    }

    public int getCreditLimit() {
        return this.creditLimit;
    }
}
