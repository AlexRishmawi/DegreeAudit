import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.DelayQueue;

public class DegreeWork {
    private UserList userList;
    private CourseList courseList;
    private DegreeList degreeList;
    private User currentUser;

    public DegreeWork() {
        this.userList = UserList.getInstance();
        this.courseList = CourseList.getInstance();
        this.degreeList = DegreeList.getInstance();
    }

    // -------- User method --------
    public boolean login(String email, String password) {
        return (this.currentUser = this.userList.getUser(email, password)) != null;
    }

    public boolean login(String firstName, String lastName, String password) {
        return (this.currentUser = this.userList.getUser(firstName, lastName, password)) != null;
    }

    public boolean logout() {
        return (this.currentUser = null) == null;
    }

    public boolean createUser(String type, String firstName, String lastName, String password, String email) {
        return this.userList.createUser(type, firstName, lastName, password, email);
    }

    public boolean createStudent(String firstName, String lastName, String email, String password,
            String level, Advisor advisor, ArrayList<String> notes, Degree degree,
            double instituteGPA, double programGPA, String status) {
        
        Student tempStudent = new Student(firstName, lastName, email, password, level, advisor, notes, degree, instituteGPA, programGPA, status);
        return this.userList.addUser(tempStudent);
    }

    public boolean createAdvisor(String firstName, String lastName, String email, String password, ArrayList<Student> studentList, Boolean isAdmin) {
        Advisor tempAdvisor = new Advisor(firstName, firstName, email, password, studentList, isAdmin);
        return this.userList.addUser(tempAdvisor);
    }

    public boolean removeUser(String id) {
        return this.userList.removeUser(UUID.fromString(id));
    }

    // -------- Student and Advisor --------
    public boolean addNotes(String note) {
        if (this.currentUser.getUserType() == UserType.STUDENT) {
            ((Student) this.currentUser).addNotes(note);
        } else if (this.currentUser.getUserType() == UserType.ADVISOR) {
            ((Advisor) this.currentUser).getCurrentStudent().addNotes(note);
        } else {
            return false;
        }
        return true;
    }

    public ArrayList<Course> getCurrentCourse() {
        if (this.currentUser.getUserType() == UserType.STUDENT) {
            return ((Student) this.currentUser).getCurrentSemester().getCourse();
        } else if (this.currentUser.getUserType() == UserType.ADVISOR) {
            return ((Advisor) this.currentUser).getCurrentStudent().getCurrentSemester().getCourse();
        }
        return null;
    }

    public String displayDegreeProgress() {
        if (this.currentUser.getUserType() == UserType.STUDENT) {
            return ((Student) this.currentUser).getDegree().toString();
        } else if (this.currentUser.getUserType() == UserType.ADVISOR) {
            return ((Advisor) this.currentUser).getCurrentStudent().getDegree().toString();
        }
        return null;
    }

    public boolean displayMajorMap() {
        if (this.currentUser.getUserType() == UserType.STUDENT) {
            return ((Student) this.currentUser).getDegree().majorMapToString();
        } else if (this.currentUser.getUserType() == UserType.ADVISOR) {
            return ((Advisor) this.currentUser).getCurrentStudent().getCurrentCourse().majorMapToString();
        }
    }

    // -------- Advisor and Admin method --------
    public boolean editUserFirstName(String name) {
        if (this.currentUser.getUserType() == UserType.ADVISOR && this.currentUser.isAdmin()) {
            User tempUser = this.userList.getUser(id);
            tempUser.setFirstName(name);
            return true;
        }
        return false;
    }

    public boolean editUserLastName(UUID id, String name) {
        if (this.currentUser.getUserType() == UserType.ADVISOR && this.currentUser.isAdmin()) {
            User tempUser = this.userList.getUser(id);
            tempUser.setLastName(name);
            return true;
        }
        return false;
    }

    public boolean editUserEmail(UUID id, String name) {
        if (this.currentUser.getUserType() == UserType.ADVISOR && this.currentUser.isAdmin()) {
            User tempUser = this.userList.getUser(id);
            tempUser.setEmail(name);
            return true;
        }
        return false;
    }

    public boolean editUserPassword(UUID id, String name) {
        if (this.currentUser.getUserType() == UserType.ADVISOR && this.currentUser.isAdmin()) {
            User tempUser = this.userList.getUser(id);
            tempUser.setPassword(name);
            return true;
        }
        return false;
    }

    public boolean deleteUser(UUID id) {
        if (this.currentUser.getUserType() == UserType.ADVISOR && this.currentUser.isAdmin()) {
            User tempUser = this.userList.getUser(id);
            String[] name_split = name.split(" ");
            tempUser.setFirstName(name_split[0]);
            tempUser.setLastName(name_split[1]);
            return true;
        }
        return false;
    }

    // -------- Advisor --------
    public void setCurrentStudent(UUID id) {

    }

    public Student getCurrentStudent() {
        return new Student();
    }

    public void showStudentInfo() {

    }

    public boolean addProgram(UUID id, String type, String subject) {
        return true;
    }

    public boolean removeProgram(UUID id, String type, String subject) {
        return true;
    }

    public boolean addCourse(String courseName, int creditHours, ArrayList<Season> semesterOffer,
            String department, int courseCode, ArrayList<Course> prerequisites,
            String description, String gradeToPass) {
        return true;
    }

    public boolean editCourseName(UUID id, String name) {
        return true;
    }

    public boolean editCourseDepartment(UUID id, String department) {
        return true;
    }

    public boolean editCourseCode(UUID id, int code) {
        return true;
    }

    public boolean editCourseCredit(UUID id, int credit) {
        return true;
    }

    public boolean editCourseSemesterOffer(UUID id, ArrayList<Season> seasons) {
        return true;
    }

    public boolean editCoursePrerequisites(UUID id, ArrayList<Course> courses) {
        return true;
    }

    public boolean editCourseDescription(UUID id, String description) {
        return true;
    }

    private Course findCourse(UUID id) {
        return new Course();
    }

}