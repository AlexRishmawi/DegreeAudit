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

    public Course(String courseName, int creditHours, ArrayList<Season> semesterOffer, 
                    String department, String courseCode, ArrayList<Course> prerequisites, 
                    String description, String gradetoPass)
    {
        setCourseName(courseName);
        setDepartment(department);
        setCode(courseCode);
        setCreditHours(creditHours);
        setSemesterOffer(semesterOffer);
        setPrerequisites(prerequisites);
        setDescription(description);
        setGradeToPass(gradetoPass);
    }

    public Course(UUID id, String courseName, int creditHours, ArrayList<Season> semesterOffer, 
                    String department, String courseCode, ArrayList<Course> prerequisites, 
                    String description, String gradetoPass) 
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

}
