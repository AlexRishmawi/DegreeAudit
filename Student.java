
import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
    private ClassLevel classification;
    private Advisor advisor;
    private ArrayList<String> notes;
    private Degree degree;
    private double instituteGPA;
    private double programGPA;
    private String status;
    private Semester currentSemester;
    private ArrayList<Semester> allSemester;

    public Student(String firstName, String lastName, String email, String password) 
    {
        super(firstName, lastName, email, password);
        classification = ClassLevel.FRESHMAN;
        advisor = null;
        this.notes = new ArrayList<>();
        this.degree = null;
        this.instituteGPA = 0;
        this.programGPA = 0;
        this.status = "Pending";
    }

    public Student(String firstName, String lastName, String email, String password,
            String level, Advisor advisor, ArrayList<String> notes, Degree degree,
            double instituteGPA, double programGPA, String status) 
    {
        super(firstName, lastName, email, password);
        setLevel(level);
        setAdvisor(advisor);
        setNotes(notes);
        setDegree(degree);
        setInstituteGPA(programGPA);
        setProgramGPA(programGPA);
        setStatus(status);
    }

    public Student(UUID id, String firstName, String lastName, String email, String password,
            String level, Advisor advisor, ArrayList<String> notes, Degree degree,
            double instituteGPA, double programGPA, String status,
            Semester currentSemester, ArrayList<Semester> allSemester) 
    {
        super(id, firstName, lastName, email, password);
        setLevel(level);
        setAdvisor(advisor);
        setNotes(notes);
        setDegree(degree);
        setInstituteGPA(programGPA);
        setProgramGPA(programGPA);
        setStatus(status);
        setCurrentSemester(currentSemester);
        setAllSemester(allSemester);
    }

    // ----- Mutator -----
    public void setLevel(String level) {
        if (level.equalsIgnoreCase("sophomore")) {
            this.classification = ClassLevel.SOPHOMORE;
        } else if (level.equalsIgnoreCase("junior")) {
            this.classification = ClassLevel.JUNIOR;
        } else if (level.equalsIgnoreCase("senior")) {
            this.classification = ClassLevel.SENIOR;
        } else {
            this.classification = ClassLevel.FRESHMAN;
        }
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void setInstituteGPA(double gpa) {
        this.instituteGPA = gpa;
    }

    public void setProgramGPA(double gpa) {
        this.programGPA = gpa;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Modified by Alex Mesa
    public void setCurrentSemester(Semester courses) {
        this.currentSemester = courses;
    }

    public void setAllSemester(ArrayList<Semester> allSemester) {
        this.allSemester = allSemester;
    }

    // ----- Accessor -----

    //Alex Mesa Additions
    public String getStatus() {
        return this.status;
    }

    //Alex Mesa Additions
    public ClassLevel getLevel() {
        return this.classification;
    }

    public Advisor getAdvisor() {
        return this.advisor;
    }

    public ArrayList<String> getNotes() {
        return this.notes;
    }

    public Degree getDegree() {
        return this.degree;
    }

    public Double getInstituteGPA() {
        return this.instituteGPA;
    }

    public Double getProgramGPA() {
        return this.programGPA;
    }

    //Changed by Alex Mesa
    public Semester getCurrentSemester() {
        return this.currentSemester;
    }

    //Changed by Alex Mesa
    public ArrayList<Semester> getAllSemester() {
        return this.allSemester;
    }

    // ----- Others method -----
    public void addNotes(String note) {
        this.notes.add(note);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(super.toString());
        result.append("\n-- level: " + this.classification.toString());
        result.append("\n-- Institute GPA: " + this.instituteGPA);
        result.append("\n-- Program GPA: " + this.instituteGPA);
        result.append("\n-- Status: " + this.status);
        if (this.advisor != null) {
            result.append("\n-- Advisor: " + advisor.toStringAccount());
        }
        result.append("\n" + printNotes() + "\n");
        if (this.degree != null) {
            result.append(toStringDegree());
        }
        return result.toString();
    }

    public String toStringAccount() {
        return super.toString();
    }

    public String printNotes() {
        return "-- Notes: " + notes.toString();
    }

    public String toStringDegree() {
        return this.degree.toString();
    }
}
