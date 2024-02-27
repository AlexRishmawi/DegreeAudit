import java.util.ArrayList;
import java.util.UUID;

public class Program {
    private UUID id;
    private ProgramType type;
    private String subject;
    private ArrayList<Course> courses;
    private int creditRequire;

    public Program(String type) {
        setType(type);
    }

    public Program(String type, String subject) {
        setType(type);
        setSubject(subject);
    }

    public void setType(String type) {
        if (type.equalsIgnoreCase("major")) {
            this.type = ProgramType.MAJOR;
        } else if (type.equalsIgnoreCase("minor")) {
            this.type = ProgramType.MINOR;
        } else if (type.equalsIgnoreCase("core")) {
            this.type = ProgramType.CORE;
        } else {
            this.type = ProgramType.ELECTIVE;
        }
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void setCredit(int totalCredits) {
        this.creditRequire = totalCredits;
    }

    public UUID getID() { return this.id; }
    public ProgramType getType() { return this.type; }
    public String getSubject() { return this.subject; }
    public ArrayList<Course> getCourse() { return this.courses; }
    public int getCredit() { return this.creditRequire; }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Program information:\n");
        string.append("Type: " + this.type);
        string.append("Subject: " + this.subject);
        string.append("Credit: " + this.creditRequire);
        for(Course course: this.courses) {
            string.append(course.toString());
        }
        string.append("\n");
        return string.toString();
    }
}