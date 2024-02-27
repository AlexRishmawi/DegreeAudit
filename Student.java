
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

    public Student(String firstName, String lastName, String email, String password){
        super(firstName, lastName, email, password);
        classification = null;
        advisor = null;
        this.notes = new ArrayList<>();
        this.degree = null;
        this.instituteGPA = 0;
        this.programGPA = 0;
        this.status = null;

    }

    public Student(UUID id, String firstName, String lastName, String email, String password,
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

    public void setLevel(String level) {
        if(level.equalsIgnoreCase("freshman")) {
            this.classification = ClassLevel.FRESHMAN;
        } else if(level.equalsIgnoreCase("sophomore")) {
            this.classification = ClassLevel.SOPHOMORE;
        } else if(level.equalsIgnoreCase("junior")) {
            this.classification = ClassLevel.JUNIOR;
        } else if(level.equalsIgnoreCase("senior")) {
            this.classification = ClassLevel.SENIOR;
        } else {
            System.err.println("ERROR --- Couldn't define student classification" + level);
        }
    }

    public void setAdvisor(Advisor advisor) { this.advisor = advisor; }
    public void setNotes(ArrayList<String> notes) { this.notes = notes; }
    public void setDegree(Degree degree) { this.degree = degree; }
    public void setInstituteGPA(double gpa) { this.instituteGPA = gpa; }
    public void setProgramGPA(double gpa) {this.programGPA = gpa; }
    public void setStatus(String status) {this.status = status; }


    public ClassLevel getLevel() { return this.classification; }
    public Advisor getAdvisor() { return this.advisor; }
    public ArrayList<String> getNotes() {return this.notes; }
    public Degree getDegree() { return this.degree; }
    public Double getInstituteGPA() { return this.instituteGPA; }
    public Double getProgramGPA() { return this.programGPA; }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Student Information:\n");
        result.append(super.toString());
        result.append("\n-- level: " + this.classification.toString());
        result.append("\n--Institute GPA: " + this.instituteGPA);
        result.append("\n--Program GPA: " + this.instituteGPA);
        result.append("\n-- Status: " + this.status);
        result.append("\n-- Advisor: " + advisor.toString());
        result.append("\n" + printNotes());
        result.append("\n" + this.toStringDegree());
        return result.toString();
    }

    public String toStringAccount() {
        return super.toString();
    }

    public String printNotes() {
        StringBuilder result = new StringBuilder();
        result.append("-- Notes: \n");
        for(String note: notes) {
            result.append("    [" + note + "]\n");
        }

        return result.toString();
    }

    public String toStringDegree() {
        return this.degree.toString();
    }
}
