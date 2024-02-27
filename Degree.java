import java.util.*;
/**
 * 
 * @author Aarsh Patel
 */
public class Degree {
    private UUID id;
    private String subjectName;
    private int totalCreditRequired;
    private ArrayList<Course> majorCourses;
    private HashMap<String, ElectiveCategory> electiveList;


    public Degree(String subjectName, int totalCreditRequired, 
        ArrayList<Course> majorCourses, 
        HashMap<String, ElectiveCategory> electiveList) 
    {
        this.id = UUID.randomUUID();
        this.subjectName = subjectName;
        this.totalCreditRequired = totalCreditRequired;
        this.majorCourses = majorCourses;
        this.electiveList = electiveList; 
    }
    
    public ArrayList<Course> getMajorCourses() {
        return this.majorCourses;
    }

    public int getTotalCreditRequired() {
        return 0;
    }

    public boolean addMajorCourse(Course course) {
        return false;
    }

    public boolean removeMajorCourse(Course course) {
        return false;
    }

    public boolean setCourseCompleted(Course course, String grade) {
        return false;
    }

    public boolean checkPrerequisites(Course course) {
        return false;
    }
    
    public boolean equals(Degree degree) {
        return false;
    }

    public String toString() {
        return "";
    }

    public String majorMapToString() {
        return "";
    }

    public UUID getID() {
        return this.id;
    }
}
