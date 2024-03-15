import java.util.UUID;
import java.util.ArrayList;

/**
 * Represents a course offered by an educational institution.
 */
public class Course {
    private UUID id;
    private String subject;
    private String code;
    private String courseName;
    private String description;
    private int creditHours;
    private ArrayList<Season> semesterOffer;
    private ArrayList<Course> prerequisites;

    /**
     * Constructs a Course object with specified attributes.
     * @param subject The subject of the course.
     * @param code The course code.
     * @param courseName The name of the course.
     * @param description The description of the course.
     * @param credit The number of credit hours for the course.
     * @param semester The seasons in which the course is offered.
     * @param prerequisites The prerequisite courses for the course.
     */
    public Course(String subject, String code, String name, String description, int credit,
                    ArrayList<Season> semester, ArrayList<Course> prerequisites)
    {
        this.id = UUID.randomUUID();
        setCourseName(courseName);
        setDepartment(subject);
        setCode(code);
        setCreditHours(creditHours);
        setSemesterOffer(semesterOffer);
        setPrerequisites(prerequisites);
        setDescription(description);
    }

    /**
     * Constructs a Course object with specified attributes.
     * @param id The unique identifier for the course.
     * @param subject The subject of the course.
     * @param code The course code.
     * @param courseName The name of the course.
     * @param description The description of the course.
     * @param credit The number of credit hours for the course.
     * @param semester The seasons in which the course is offered.
     * @param prerequisites The prerequisite courses for the course.
     */
    public Course(UUID id, String subject, String code, String name, String description, int credit,
                    ArrayList<Season> semester, ArrayList<Course> prerequisites)
    {
        setID(id);
        setCourseName(courseName);
        setDepartment(subject);
        setCode(code);
        setCreditHours(creditHours);
        setSemesterOffer(semesterOffer);
        setPrerequisites(prerequisites);
        setDescription(description);
    }

    // ----- Accessor -----
    public void setID(UUID id) { this.id = id; }
    public void setCourseName(String name) { this.courseName = name; }
    public void setDepartment(String subject) { this.subject = subject; }
    public void setCode(String code) { this.code = code; }
    public void setCreditHours(int credit) { this.creditHours = credit; }
    public void setSemesterOffer(ArrayList<Season> season) { this.semesterOffer = season;}
    public void setPrerequisites(ArrayList<Course> courses) { this.prerequisites = courses;}
    public void setDescription(String description) { this.description = description;}

    // ----- Mutator -----
    public UUID getID() { return this.id; }
    public String getCourseName() { return this.courseName; }
    public String getDepartment() { return this.subject; }
    public String getCode() { return this.code; }
    public int getCreditHours() { return this.creditHours; }
    public ArrayList<Season> getSemesterOffer() { return this.semesterOffer; }
    public ArrayList<Course> getPrerequisites() { return this.prerequisites; }
    public String getDescription() { return this.description; }

    /**
     * Returns a string representation of the course.
     * @return A string representation of the course.
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Course Information: ");
        string.append("\n-- ID: " + this.id);
        string.append("\n-- Name: " + this.courseName);
        string.append("\n-- Subject: " + this.subject);
        string.append("\n-- Code: " + this.code);
        string.append("\n-- Credit hours: " + this.creditHours);
        string.append("\n--Semester hours: " + this.semesterOffer.toString());
        string.append("\n--Course prerequisites: " + this.prerequisites.toString());
        string.append("\n--Description: " + this.description);
        return string.toString();
    }

    /**
     * Indicates whether some other course is "equal to" this one.
     * @param course The course to compare with.
     * @return true if this course is the same as the course argument; false otherwise.
     */
    public boolean equals(Course course) { return this.id.equals(course.getID()); }
}
