import java.util.ArrayList;

/**
 * 
 * @author Aarsh Patel
 */
public class ElectiveCategory {
    public String type;
    private int creditsRequired;
    private ArrayList<Course> courseChoices;

    public ElectiveCategory(String type, int creditsRequired, ArrayList<Course> courseChoices) {
        this.type = type;
        this.creditsRequired = creditsRequired;
        this.courseChoices = courseChoices;
    }

    public ArrayList<Course> getCourseChoices() {
        return this.courseChoices;
    }

    public String toString() {
        return "";
    }
}
