
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import org.json.simple.JSONArray;

public class Student extends User {
    private ClassLevel classification;
    private Advisor advisor;
    private ArrayList<String> notes;
    private Degree degree;
    private double instituteGPA;
    private double programGPA;
    private String status;
    private Semester currentSemester;
    private ArrayList<Semester> allSemester; // 8 semester plan
    private HashMap<Course, String> completeCourses; // only for completed course with graded
    private String studentID;

    public Student(String firstName, String lastName, String email, String password, String studentID) 
    {
        super(firstName, lastName, email, password);
        super.setUserType(UserType.STUDENT);
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
        this.studentID = studentID;
    }

    public Student(String firstName, String lastName, String email, String password, String studentID,
            String level, Advisor advisor, ArrayList<String> notes, Degree degree,
            double instituteGPA, double programGPA, String status) 
        {
        super(firstName, lastName, email, password);
        super.setUserType(UserType.STUDENT);
        setLevel(level);
        setAdvisor(advisor);
        setNotes(notes);
        setDegree(degree);
        setInstituteGPA(instituteGPA);
        setProgramGPA(programGPA);
        setStatus(status);
        setAllSemester(new ArrayList<Semester>());
        setCurrentSemester(null);
        setCompleteCourses(completeCourses);
        this.studentID = studentID;
        initializeSemesterPlan();
    }

    public Student(UUID id, String firstName, String lastName, String email, String password, String studentID,
            String level, Advisor advisor, ArrayList<String> notes, Degree degree,
            double instituteGPA, double programGPA, String status, HashMap<Course, String> completeCourses,
            Semester currentSemester, ArrayList<Semester> allSemester) 
    {
        super(id, firstName, lastName, email, password);
        super.setUserType(UserType.STUDENT);
        setLevel(level);
        setAdvisor(advisor);
        setNotes(notes);
        setDegree(degree);
        setInstituteGPA(instituteGPA);
        setProgramGPA(programGPA);
        setStatus(status);
        setCurrentSemester(currentSemester);
        setAllSemester(allSemester);
        setCompleteCourses(completeCourses);
        this.studentID = studentID;
        initializeSemesterPlan();
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

    public void setCurrentSemester(Semester semester) {
        this.currentSemester = semester;
        // this.allSemester.add(semester);
    }

    public void setAllSemester(ArrayList<Semester> allSemester) {
        this.allSemester = allSemester;
    }

    public void setCourseCompleted(Course course, String grade) {
        if (this.completeCourses == null) {
            this.completeCourses = new HashMap<>();
        }
        this.completeCourses.put(course, grade);
    }

    public void setCompleteCourses(HashMap<Course, String> completeCourses) {
        this.completeCourses = completeCourses;
    }

    // ----- Accessor -----

    // Alex Mesa Additions
    public String getStatus() {
        return this.status;
    }

    // Alex Mesa Additions
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

    public HashMap<Course, String> getCompletedCourse() {
        return this.completeCourses;
    }

    // Changed by Alex Mesa
    public Semester getCurrentSemester() {
        return this.currentSemester;
    }

    // Changed by Alex Mesa
    public ArrayList<Semester> getAllSemester() {
        return this.allSemester;
    }

    public String getStudentID() {
        return this.studentID;
    }

    // ----- Others method -----
    public void addSemester(Semester semester) {
        this.allSemester.add(semester);
    }

    public void addNotes(String note) {
        this.notes.add(note);
    }

    public void initializeSemesterPlan() {
        HashMap<UUID, Integer> allCourseNotTaken = new HashMap<>();
        ArrayList<Course> queueCourse = new ArrayList<>();

        // Get all the course need to take
        HashMap<Course, Integer> majorCourses = this.degree.getMajorCourses();
        for (Course course : majorCourses.keySet()) {
            if (this.completeCourses.keySet().stream().anyMatch(c -> !c.equals(course))) {
                allCourseNotTaken.put(course.getID(), majorCourses.get(course));
                queueCourse.add(course);
            }
        }
        ArrayList<ElectiveCategory> electiveList = this.degree.getElectiveList();
        for (ElectiveCategory category : electiveList) {
            for (Course course : category.getCourseChoices().keySet()) {
                if (this.completeCourses.keySet().stream().anyMatch(c -> !c.equals(course))) {
                    allCourseNotTaken.put(course.getID(), category.getCourseChoices().get(course));
                    queueCourse.add(course);
                }
            }
        }

        // Generate the course need to take
        // String currSeason = this.currentSemester.getSeason().toString();
        // int currYear = this.currentSemester.getYear();
        ArrayList<Course> semesterCourse = new ArrayList<>();
        String currSeason = "Fall";
        int currYear = 2024;

        int creditLimitLeft = 18;
        for (int level = 1; level <= 8; level++) {
            for (int i = queueCourse.size() - 1; i >= 0; i--) {
                Course course = queueCourse.get(i);
                int semesterPrefer = allCourseNotTaken.get(course.getID());
                if (semesterPrefer != level) {
                    continue;
                }

                int courseLimit = course.getCreditHours();
                if (courseLimit <= creditLimitLeft) {
                    creditLimitLeft -= courseLimit;
                    semesterCourse.add(course);
                    queueCourse.remove(course);
                } else {
                    Semester semester = new Semester(currSeason, currYear, 18 - creditLimitLeft, semesterCourse);
                    System.out.println(semester.toString());
                    if (currSeason.equalsIgnoreCase("fall")) {
                        currSeason = "spring";
                        currYear += 1;
                    } else {
                        currSeason = "fall";
                    }
                    this.allSemester.add(semester);
                    semesterCourse.clear();
                    creditLimitLeft = 18;
                    i++;
                }
            }
        }

        // for (Semester semester: this.allSemester) {
        // System.out.println(semester);
        // }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("---------------------------------Student---------------------------------\n");
        result.append(super.toString());
        result.append("\n-- Classification: " + this.classification.toString());
        result.append("\n-- Student ID: " + this.studentID);
        result.append("\n-- Institute GPA: " + this.instituteGPA);
        result.append("\n-- Program GPA: " + this.programGPA);
        result.append("\n-- Status: " + this.status);
        if (this.advisor != null) {
            result.append("\n-- Advisor: " + advisor.getFirstName() + " " + advisor.getLastName());
        } else {
            result.append("\n-- Advisor: None");
        }
        result.append("\n" + printNotes() + "\n");
        result.append("\n-------------------------------------------------------------------\n");
        result.append("\n-- Degree: Bachelors in " + this.degree.getSubject() + "\n");
        if (this.degree != null) {
            // result.append(toStringDegree());
            for (Course course : completeCourses.keySet()) {
                String graded = completeCourses.get(course);
                if (graded.equals("NT")) {
                    result.append(course.toStringCourseAbbr() + "\n --Graded: Not Taken" + "\n");
                } else {
                    result.append(course.toStringCourseAbbr() + "\n --Graded: " + graded + "\n");
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

    public String allSemesterPlan() {
        StringBuilder result = new StringBuilder();
        for (Semester semester : this.allSemester) {
            result.append(semester.toString());
        }
        return result.toString();
    }

    public static void main(String[] args) {
            Degree degree = new Degree("Bachelor", "Computer Science", 120, new HashMap<Course, Integer>(), new ArrayList<ElectiveCategory>());
            Student student = new Student("Brax", "West", "Bwest@email.sc.edu", "password", "X63942619",
            "junior", new Advisor("null", "null", "null", "null", false), new ArrayList<String>(), degree, 4.0, 4.0, "Good Standing");
            Course newCourse = new Course("CSCE", "101", "Introduction to Computer Science", "An introduction to the field of computer science.",3, new ArrayList<Season>(), new ArrayList<Prerequisites>());
            ArrayList<Course> courses = new ArrayList<>();
            courses.add(newCourse);
            Semester semester = new Semester("spring", 2021, 18, courses);
            ArrayList<Semester> allSemesters = new ArrayList<>();
            allSemesters.add(semester);
            student.setAllSemester(allSemesters);
            student.setCourseCompleted(new Course("CSCE", "101", "Introduction to Computer Science", "An introduction to the field of computer science.",3, new ArrayList<Season>(), new ArrayList<Prerequisites>()), "T");
            student.initializeSemesterPlan();
            System.out.println(student.allSemesterPlan());
        }
}