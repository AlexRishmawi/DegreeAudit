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

    // -------- User Method --------
    public boolean login(String email, String password) {
        return (this.currentUser = this.userList.getUser(email, password)) != null;
    }

    public boolean login(String firstName, String lastName, String password) {
        return (this.currentUser = this.userList.getUser(firstName, lastName, password)) != null;
    }

    public boolean logout() {
        return (this.currentUser = null) == null;
    }

    public boolean createUser(String type, String firstName, String lastName, String password) {
        return (this.userList.createUser(type, firstName, lastName, password));
        
    }

    public boolean removeUser(String UUID) {
        return true;
    }

    public boolean removeUser(String firstName, String lastName) {
        return true;
    }

    // -------- Student and Advisor --------
    public boolean addNotes(String note) {
        return true;
    }

    public ArrayList<Course> getCurrentCourse() {
        return new ArrayList<>();
    }

    public boolean displayDegreeProgress() {
        return true;
    }

    public boolean displayMajorMap() {
        return true;
    }

    public String compareDegree(String degreeType, String subjectName) {
        return "";
    }

    public ArrayList<Course> getCourseRecommend() {
        return new ArrayList<>();
    }

    // -------- Advisor and Admin method --------
    public boolean editUserFirstName(UUID id, String name) {
        return true;
    }

    public boolean editUserLastName(UUID id, String name) {
        return true;
    }

    public boolean editUserEmail(UUID id, String name) {
        return true;
    }

    public boolean editUserID(UUID id, String name) {
        return true;
    }

    public boolean editUserPassword(UUID id, String name) {
        return true;
    }

    public boolean deleteUser(UUID id) {
        return true;
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

    // -------- Admin --------
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