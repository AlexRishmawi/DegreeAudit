import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 * @author Aarsh Patel
 */
public class ElectiveCategory {
    private String type;
    private int creditsRequired;
    private ArrayList<Course> courseChoices;

    public ElectiveCategory(String type, int creditsRequired, ArrayList<Course> courseChoices) {
        setType(type);
        setCreditRequired(creditsRequired);
        setCourseChoices(courseChoices);
    }

    // ----- Accessor -----
    public ArrayList<Course> getCourseChoices() {
        return this.courseChoices;
    }

    public String getType() {
        return this.type;
    }

    public int getCreditsRequired() {
        return this.creditsRequired;
    }

    // ----- Mutator -----
    public void setCourseChoices(ArrayList<Course> courses) {
        this.courseChoices = courses;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreditRequired(int credit) {
        this.creditsRequired = credit;
    }

    public String toString() {
        StringBuilder retString = new StringBuilder();
        for (Course choice : courseChoices) {
            retString.append(choice);
        }
        return retString.toString();
    }
}
