import java.util.ArrayList;

public class Prerequisites {
    private int choices;
    private String minGrade;
    private ArrayList<Course> courseOptions;

    public Prerequisites(int choices, String minGrade, ArrayList<Course> courseOptions) {
        setChoices(choices);
        setMinGrade(minGrade);
        setCourseOptions(courseOptions);
    }

    // ----- Accessor -----
    public int getChoices() { return this.choices; }
    public String getMinGrade() { return this.minGrade; }
    public ArrayList<Course> getCourseOptions() { return this.courseOptions; }

    // ----- Mutator -----
    public void setChoices(int choices) {
        this.choices = choices;
    }

    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
    }

    public void setCourseOptions(ArrayList<Course> courseOptions) {
        this.courseOptions = courseOptions;
    }

    // ----- Method -----
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Prerequisites:\n");
        result.append("-- Choices: " + this.choices);
        result.append("-- Min Grade: " + this.minGrade);
        result.append("-- Course Options: ");
        for(int i = 0; i < this.courseOptions.size(); i++) {
            Course course = this.courseOptions.get(i);
            result.append("[ Student " + (i+1) + ": \n" + course.toString() + "\n]");
            result.append(i != this.courseOptions.size() - 1 ? " ,\n" : "\n");
        }
        return result.toString();
    }
}
