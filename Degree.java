import java.util.ArrayList;
import java.util.UUID;
/**
 * 
 * @author Aarsh Patel
 */
public class Degree {
    // Program variables
    private ArrayList<Program> programs;
    private int totalCreditRequired;
    private ArrayList<Course> totalCoursesRequired;
    private String subjectName;
    private String degreeType;

    // Course Variables
    private ArrayList<Course> completeCourses;
    private int completeCredit;

    // Course Generator
    private Semester currentSemester;
    private int currentYear;
    private ArrayList<Semester> allSemesters;

    public Degree(String degreeType, String subjectName, ArrayList<Program> programs, 
    int totalCreditRequired, ArrayList<Course> totalCoursesRequired, ArrayList<Course> completeCourses, 
    int completeCredit, Semester currentSemester, int currentYear,
    ArrayList<Semester> allSemesters) {
        
    }
    
    public ArrayList<Program> getPrograms() {
        return new ArrayList<Program>();
    }

    public int getTotalCreditRequired() {
        return 0;
    }

    public ArrayList<Course> getTotalCoursesRequired() {
        return new ArrayList<Course>();
    }

    public boolean addProgram(Program program) {
        return false;
    }

    public boolean removeProgram(UUID programID) {
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
}
