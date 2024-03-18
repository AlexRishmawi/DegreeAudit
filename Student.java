
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Course, String> completeCourses;

    public Student(String firstName, String lastName, String email, String password) 
    {
        super(firstName, lastName, email, password);
        classification = ClassLevel.FRESHMAN;
        advisor = null;
        this.notes = new ArrayList<>();
        this.degree = null;
        this.instituteGPA = 4.0;
        this.programGPA = 4.0;
        this.status = "Pending";
        this.currentSemester = null;
        this.allSemester = new ArrayList<>();
        this.completeCourses = new HashMap<>();
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
        setInstituteGPA(instituteGPA);
        setProgramGPA(programGPA);
        setStatus(status);
        setAllSemester(new ArrayList<Semester>());
        setCurrentSemester(null);
        initializeCompleteCourses();
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
        initializeCompleteCourses();
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

    public void initializeCompleteCourses() {
        HashMap<Course, Integer> majorCourses = this.degree.getMajorCourses();
        this.completeCourses = new HashMap<>();
        for (Course course : majorCourses.keySet()) {
            this.completeCourses.put(course, "NT");
        }
        ArrayList<ElectiveCategory> electiveList = this.degree.getElectiveList();
        for (ElectiveCategory category : electiveList) {
            for (Course course : category.getCourseChoices().keySet()) {
                this.completeCourses.put(course, "NT");
            }
        }
    }

    public void setCourseCompleted(Course course, String grade) {
        this.completeCourses.put(course, grade);
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
            result.append("\n-- Advisor: " + advisor.getFirstName() + " " + advisor.getLastName());
        }
        result.append("\n" + printNotes() + "\n");
        if (this.degree != null) {
            //result.append(toStringDegree());
            for(Course course : completeCourses.keySet()) {
                if(completeCourses.get(course).equals("NT")) {
                    result.append(course.toString() + "\n --Status: Not Taken" + "\n");
                }
                else {
                    result.append(course.toString() + "\n --Status: Taken" + "\n");
                }
            }
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


    public static void main(String[] args) {
        Degree degree = new Degree("Bachelor", "Computer Science", 120, new HashMap<Course, Integer>(), new ArrayList<ElectiveCategory>());
        Student student = new Student("Brax", "West", "Bwest@email.sc.edu", "password",
        "junior", new Advisor("null", "null", "null", "null", false), new ArrayList<String>(), degree, 4.0, 4.0, "Good Standing");
        ArrayList<Semester> semesters = new ArrayList<>();
        Semester semester = new Semester("spring", 2021, 18, new ArrayList<Course>());
        semesters.add(semester);
        student.setAllSemester(semesters);
        student.initializeCompleteCourses();
        student.setCourseCompleted(new Course("CSCE", "101", "Introduction to Computer Science", "An introduction to the field of computer science.",3, new ArrayList<Season>(), new ArrayList<Prerequisites>()), "T");
        System.out.println(student.toString());
    }

}
