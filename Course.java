import java.util.UUID;
import java.util.ArrayList;

public class Course {
    private UUID id;
    private String courseName;
    private String department;
    private String code;
    private int creditHours;
    private ArrayList<Season> semesterOffer;
    private ArrayList<Course> prerequisites;
    private String description;
    private String gradeToPass;

    public Course(String courseName, String department, String courseCode,  int creditHours, ArrayList<Season> semesterOffer, 
                    ArrayList<Course> prerequisites, String description, String gradetoPass)
    {
        this.id = UUID.randomUUID();
        setCourseName(courseName);
        setDepartment(department);
        setCode(courseCode);
        setCreditHours(creditHours);
        setSemesterOffer(semesterOffer);
        setPrerequisites(prerequisites);
        setDescription(description);
        setGradeToPass(gradetoPass);
    }

    public Course(UUID id, String coursName, String department, String courseCode,  int creditHours, ArrayList<Season> semesterOffer, 
                    ArrayList<Course> prerequisites, String description, String gradetoPass) 
    {
        setID(id);
        setCourseName(courseName);
        setDepartment(department);
        setCode(courseCode);
        setCreditHours(creditHours);
        setSemesterOffer(semesterOffer);
        setPrerequisites(prerequisites);
        setDescription(description);
        setGradeToPass(gradetoPass);
    }

    public void setID(UUID id) { this.id = id; }
    public void setCourseName(String name) { this.courseName = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setCode(String code) { this.code = code; }
    public void setCreditHours(int credit) { this.creditHours = credit; }
    public void setSemesterOffer(ArrayList<Season> season) { this.semesterOffer = season;}
    public void setPrerequisites(ArrayList<Course> courses) { this.prerequisites = courses;}
    public void setDescription(String description) { this.description = description;}
    public void setGradeToPass(String grade) { this.gradeToPass = grade;}

    public UUID getID() { return this.id; }
    public String getCourseName() { return this.courseName; }
    public String getDepartment() { return this.department; }
    public String getCode() { return this.code; }
    public int getCreditHours() { return this.creditHours; }
    public ArrayList<Season> getSemesterOffer() { return this.semesterOffer; }
    public ArrayList<Course> getPrerequisites() { return this.prerequisites; }
    public String getDescription() { return this.description; }
    public String getGradeToPass() { return this.gradeToPass; }

    public boolean equals(Course course) { return this.id.equals(course.getID()); }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Course Information: ");
        string.append("\n-- ID: " + this.id);
        string.append("\n-- Name: " + this.courseName);
        string.append("\n-- Department: " + this.department);
        string.append("\n-- Code: " + this.code);
        string.append("\n-- Credit hours: " + this.creditHours);
        string.append("\n--Semester hours: " + this.semesterOffer.toString());
        string.append("\n--Course prerequisites: " + this.prerequisites.toString());
        string.append("\n--Description: " + this.description);
        string.append("\n--Grade need to pass: " + this.gradeToPass);
        string.append("\n");
        return string.toString();
    }
}
