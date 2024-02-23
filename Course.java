import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
public class Course {
    private UUID id;
    private String courseName;
    private String department;
    private int code;
    private int creditHours;
    private ArrayList<Season> semesterOffer;
    private ArrayList<HashMap<Course,String>> prerequisites;
    private String description;
    private char gradeToPass;
    private String courseGrade;

    /**
     * Constructor for Course
     * @return 
     */
    public Course(UUID id, String courseName, int creditHours, Season semesterOffer, String
    department, int courseCode, ArrayList<Course> prerequisites, String description, String gradetoPass) {
=
    }

    /**
     * Gets the ID of the course
     * @return int
     */
    public int getID() {
        return 0;
    }

    /**
     * Gets the name of the course
     * @return int
     */
    public String getName() {
        return null;
    }

    /**
     * Sets the name of the course
     * @param name
     */
    public void setName(String name) {

    }

    /**
     * Gets the Abbreviation of the course
     * @return String
     */
    public String getAbbr() { //Depart + code
        return null;
    }

    /**
     * Sets the course code
     * @param code
     */
    public void setCode(int code) {

    }

    /**
     * Gets the course credit hours
     * @return int
     */
    public int getCredit() {
        return 0;
    }

    /**
     * gets the semester the course is offered
     * @return ArrayList<Season>
     */
    public ArrayList<Season> getSemesterOffer() {
        return null;
    }

    /**
     * Gets the prerequisites of the course
     * @return ArrayList<ArrayList<Object>>
     */
    public ArrayList<ArrayList<Object>> getPrerequisites() {
        return null;
    }

    /**
     * Checks the prerequisites of the course
     * @return boolean
     */
    public boolean checkPrerequisites() {
        return false;
    }

    /**
     * Prints the prerequisites of the course
     */
    public void printPrerequisites() {

    }

    /**
     * Gets the description of the course
     * @return String
     */
    public String getDescription() {
        return null;
    }

    /**
     * String representation of the course
     * @return String
     */
    public String toString() {
        return null;
    }

}
